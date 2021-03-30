package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.ActionWithJDBC;

import java.sql.*;
import java.util.*;

public class SqlStorage implements Storage {
    private final ActionWithJDBC withJDBC;

    public SqlStorage(String url, String name, String password) {
        withJDBC = new ActionWithJDBC(() -> DriverManager.getConnection(url, name, password));
    }

    @Override
    public void clear() {
        withJDBC.execute("DELETE FROM resume");
    }

    @Override
    public Resume get(String uuid) {
        return withJDBC.execute("    SELECT * FROM resume r " +
                " LEFT JOIN contact c " +
                "        ON r.uuid = c.resume_uuid " +
                "     WHERE r.uuid =? ", ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            Resume resume = new Resume(uuid, rs.getString("full_name").trim());
            do {
                String value = rs.getString("value");
                ContactType type = ContactType.valueOf(rs.getString("type"));
                resume.addContact(type, value);
            } while (rs.next());
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
                if (ps.executeUpdate() == 0) throw new NotExistStorageException(uuid);
                try (PreparedStatement pss = conn.prepareStatement("UPDATE contact set value=?,type=? WHERE resume_uuid=?")) {
                    for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                        executeContact(pss, e.getValue(), e.getKey().name(), uuid);
                    }
                    ps.executeBatch();
                }
            }
            return null;
        });
    }

    @Override
    public void save(Resume r) {
        String uuid = r.getUuid();
        withJDBC.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                ps.setString(1, r.getUuid());
                ps.setString(2, r.getFullName());
                ps.execute();
            }
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
                for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                    executeContact(ps, uuid, e.getKey().name(), e.getValue());
                }
                ps.executeBatch();
            }
            return null;
        });
    }

    @Override
    public void delete(String uuid) {
        withJDBC.execute("DELETE FROM resume WHERE uuid=?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) throw new NotExistStorageException(uuid);
            return null;
        });
    }

    @Override
    public List<Resume> getAllSortedList() {
        List<Resume> resumes = new LinkedList<>();
        withJDBC.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("SELECT uuid FROM resume ORDER BY full_name,uuid")) {
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    resumes.add(get(resultSet.getString("uuid")));
                }
            }
            return conn;
        });
        return resumes;
    }

    @Override
    public int size() {
        return withJDBC.execute("SELECT count(*) FROM resume", ps -> {
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        });
    }

    private void executeContact(PreparedStatement ps, String uuid, String type, String value) throws SQLException {
        ps.setString(1, uuid);
        ps.setString(2, type);
        ps.setString(3, value);
        ps.addBatch();
    }
}
