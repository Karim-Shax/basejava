package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.util.ActionWithJDBC;

import java.sql.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

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
        try (PreparedStatement ps = withJDBC.initPrepareStatement("SELECT * FROM resume WHERE uuid =?")) {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name").trim());
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
            ps.execute();
        } catch (SQLException e) {
            if (e.getSQLState().trim().equals("23505")) {
                throw new ExistStorageException(uuid);
            } else {
                throw new StorageException(e.getSQLState(), uuid);
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
             ResultSet resultSet = ps.executeQuery("SELECT * FROM resume")) {
            while (resultSet.next()) {
                resumes.add(
                        new Resume(resultSet.getString("uuid").trim(),
                                resultSet.getString("full_name").trim())
                );
            }
        } catch (SQLException e) {
            throw new StorageException(e.getMessage());
        }
        Comparator<Resume> comparator = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);
        resumes.sort(comparator);
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