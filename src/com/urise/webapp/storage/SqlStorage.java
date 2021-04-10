package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.*;
import com.urise.webapp.sql.ActionWithJDBC;

import java.sql.*;
import java.util.*;

public class SqlStorage implements Storage {
    private final ActionWithJDBC withJDBC;

    public SqlStorage(String url, String name, String password) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        withJDBC = new ActionWithJDBC(() -> DriverManager.getConnection(url, name, password));
    }

    @Override
    public void clear() {
        withJDBC.execute("DELETE FROM resume");
    }

    @Override
    public Resume get(String uuid) {
        return withJDBC.transactionalExecute(connection -> {
            Resume resume;
            try (PreparedStatement ps = connection.prepareStatement("    " +
                    "SELECT * FROM resume r " +
                    " LEFT JOIN contact c " +
                    "        ON r.uuid = c.resume_uuid " +
                    "     WHERE r.uuid =? ")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                resume = new Resume(uuid, rs.getString("full_name").trim());
                do {
                    String value = rs.getString("value");
                    if (value != null) {
                        addContactInResume(resume, rs.getString("type"), value);
                    }
                } while (rs.next());
            }
            resume.setInfo(getSections(connection, uuid));
            return resume;
        });
    }

    @Override
    public void update(Resume r) {
        String uuid = r.getUuid();
        withJDBC.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name=? WHERE uuid=?")) {
                ps.setString(2, uuid);
                ps.setString(1, r.getFullName());
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(uuid);
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM contact WHERE resume_uuid=?")) {
                ps.setString(1, uuid);
                ps.executeUpdate();
            }
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM section WHERE resume_uuid=?")) {
                ps.setString(1, uuid);
                ps.executeUpdate();
            }
            saveContacts(r, conn);
            saveSections(r, conn);
            return null;
        });
    }

    @Override
    public void save(Resume r) {
        withJDBC.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                ps.setString(1, r.getUuid());
                ps.setString(2, r.getFullName());
                ps.executeUpdate();
            }
            saveContacts(r, conn);
            saveSections(r, conn);
            return null;
        });
    }


    @Override
    public void delete(String uuid) {
        withJDBC.execute("DELETE FROM resume WHERE uuid=?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSortedList() {
        Map<String, Resume> resumes = new LinkedHashMap<>();
        withJDBC.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume ORDER BY full_name,uuid")) {
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    String uuid = resultSet.getString("uuid");
                    Resume resume = new Resume(uuid, resultSet.getString("full_name"));
                    resumes.put(uuid, resume);
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String resume_uuid = rs.getString("resume_uuid");
                    Resume resume = resumes.get(resume_uuid);
                    addContactInResume(resume, rs.getString("type"), rs.getString("value"));
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM section")) {
                ResultSet rs = ps.executeQuery();
                EnumMap<SectionType, Section> sectionEnumMap = new EnumMap<>(SectionType.class);
                while (rs.next()) {
                    String resume_uuid = rs.getString("resume_uuid");
                    Resume resume = resumes.get(resume_uuid);
                    SectionType type = SectionType.valueOf(rs.getString("type"));
                    switchSections(rs, sectionEnumMap, type);
                    resume.setInfo(sectionEnumMap);
                }
            }
            return null;
        });
        return new ArrayList<>(resumes.values());
    }

    private void switchSections(ResultSet rs, EnumMap<SectionType, Section> sectionEnumMap, SectionType type) throws SQLException {
        switch (type) {
            case PERSONAL:
            case OBJECTIVE:
                sectionEnumMap.put(type,
                        new TextSection(rs.getString("value")));
                break;
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                String str = rs.getString("value");
                sectionEnumMap.put(type, new ListSection(Arrays.asList(str.split("\n"))));
                break;
        }
    }

    @Override
    public int size() {
        return withJDBC.execute("SELECT count(*) FROM resume", ps -> {
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        });
    }

    private void saveContacts(Resume r, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void addContactInResume(Resume resume, String type, String value) {
        resume.addContact(ContactType.valueOf(type), value);
    }

    private EnumMap<SectionType, Section> getSections(Connection connection, String uuid) throws SQLException {
        EnumMap<SectionType, Section> sectionEnumMap = new EnumMap<>(SectionType.class);
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM section WHERE resume_uuid=?")) {
            ps.setString(1, uuid);
            ResultSet resultSet = ps.executeQuery();
            SectionType type;
            while (resultSet.next()) {
                type = SectionType.valueOf(resultSet.getString("type"));
                switchSections(resultSet, sectionEnumMap, type);
            }
        }
        return sectionEnumMap;
    }

    private void saveSections(Resume r, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO section (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<SectionType, Section> e : r.getInfo().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                String value = null;
                switch (e.getKey()) {
                    case PERSONAL:
                    case OBJECTIVE:
                        TextSection textSection = (TextSection) e.getValue();
                        value = textSection.getText();
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        ListSection listSection = (ListSection) e.getValue();
                        value = String.join("\n", listSection.getItems());
                }
                ps.setString(3, value);
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }
}
