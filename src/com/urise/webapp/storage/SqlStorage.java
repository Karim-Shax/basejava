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
        withJDBC.execute("update resume set full_name=? WHERE uuid=?", ps -> {
            ps.setString(2, uuid);
            ps.setString(1, r.getFullName());
            if (ps.executeUpdate() == 0) throw new NotExistStorageException(uuid);
            for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                withJDBC.execute("UPDATE contact set value=?,type=? WHERE resume_uuid=?", pss -> {
                    pss.setString(3, uuid);
                    pss.setString(2, e.getKey().name());
                    pss.setString(1, e.getValue());
                    if (ps.executeUpdate() == 0) throw new NotExistStorageException(uuid);
                    return null;
                });
            }
            return null;
        });
    }

    @Override
    public void save(Resume r) {
        String uuid = r.getUuid();
        withJDBC.execute("INSERT INTO resume (uuid, full_name) VALUES (?,?)", ps -> {
            ps.setString(1, uuid);
            ps.setString(2, r.getFullName());
            ps.executeUpdate();
            return null;
        });
        for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
            withJDBC.execute("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)", ps -> {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.executeUpdate();
                return null;
            });
        }
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
        withJDBC.execute("SELECT * FROM resume ORDER BY full_name,uuid", ps -> {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Resume resume = new Resume(resultSet.getString("uuid"),
                        resultSet.getString("full_name"));
                resume.setContacts((EnumMap<ContactType, String>) get(resume.getUuid()).getContacts());
                resumes.add(resume);
            }
            return null;
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
}