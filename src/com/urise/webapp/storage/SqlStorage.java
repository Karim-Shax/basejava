package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.ActionWithJDBC;

import java.sql.*;
import java.util.*;

public class SqlStorage implements Storage {
    private final ActionWithJDBC withJDBC;

    public SqlStorage(String url, String name, String password) {
        withJDBC = new ActionWithJDBC(url, name, password);
    }

    @Override
    public void clear() {
        try (PreparedStatement ps = withJDBC.initPrepareStatement("DELETE FROM resume")) {
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e.getMessage());
        }
    }

    @Override
    public Resume get(String uuid) {
        try (PreparedStatement ps = withJDBC.initPrepareStatement(
                "" +
                        "    SELECT * FROM resume r " +
                        " LEFT JOIN contact c " +
                        "        ON r.uuid = c.resume_uuid " +
                        "     WHERE r.uuid =? "
        )) {
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
        } catch (SQLException e) {
            throw new StorageException(e.getMessage(), uuid);
        }
    }

    @Override
    public void update(Resume r) {
        String uuid = r.getUuid();
        try (PreparedStatement ps = withJDBC.initPrepareStatement("update resume set full_name=? WHERE uuid=?")) {
            ps.setString(2, uuid);
            ps.setString(1, r.getFullName());
            if (ps.executeUpdate() == 0) throw new NotExistStorageException(uuid);
            for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                try (PreparedStatement pss = withJDBC.initPrepareStatement("UPDATE contact set value=?,type=? WHERE resume_uuid=?")) {
                    pss.setString(3, r.getUuid());
                    pss.setString(2, e.getKey().name());
                    pss.setString(1, e.getValue());
                    if (ps.executeUpdate() == 0) throw new NotExistStorageException(uuid);
                }
            }
        } catch (SQLException e) {
            throw new StorageException(e.getMessage(), uuid);
        }
    }

    @Override
    public void save(Resume r) {
        String uuid = r.getUuid();
        try (PreparedStatement ps = withJDBC.initPrepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
            ps.setString(1, uuid);
            ps.setString(2, r.getFullName());
            ps.executeUpdate();
            for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                try (PreparedStatement preparedStatement = withJDBC.initPrepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)");) {
                    preparedStatement.setString(1, r.getUuid());
                    preparedStatement.setString(2, e.getKey().name());
                    preparedStatement.setString(3, e.getValue());
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            if (e.getSQLState().trim().equals("23505")) {
                throw new ExistStorageException(uuid);
            } else {
                throw new StorageException(e.getMessage(), uuid);
            }
        }
    }

    @Override
    public void delete(String uuid) {
        try (PreparedStatement ps = withJDBC.initPrepareStatement("DELETE FROM resume WHERE uuid=?")) {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) throw new NotExistStorageException(uuid);
        } catch (SQLException e) {
            throw new StorageException(e.getMessage(), uuid);
        }
    }

    @Override
    public List<Resume> getAllSortedList() {
        List<Resume> resumes = new LinkedList<>();
        try (Statement ps = withJDBC.initStatement();
             ResultSet resultSet = ps.executeQuery("SELECT * FROM resume ORDER BY full_name,uuid")) {
            while (resultSet.next()) {
                Resume resume = new Resume(resultSet.getString("uuid").trim(),
                        resultSet.getString("full_name").trim());
                resume.setContacts((EnumMap<ContactType, String>) get(resume.getUuid()).getContacts());
                resumes.add(resume);
            }
        } catch (SQLException e) {
            throw new StorageException(e.getMessage());
        }
        return resumes;
    }

    @Override
    public int size() {
        int size;
        try (Statement ps = withJDBC.initStatement();
             ResultSet resultSet = ps.executeQuery("SELECT count(*) FROM resume")) {
            resultSet.next();
            size = resultSet.getInt(1);
        } catch (SQLException e) {
            throw new StorageException(e.getMessage());
        }
        return size;
    }
}