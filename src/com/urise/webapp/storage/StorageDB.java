package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.util.ActionWithJDBC;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class StorageDB implements Storage {
    private final ActionWithJDBC withJDBC;

    public StorageDB(String dbUrl, String dbUser, String dbPassword) {
        withJDBC = new ActionWithJDBC(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        try {
            PreparedStatement ps = withJDBC.initPrepareStatement("DELETE FROM resume");
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e.getMessage());
        }
    }

    @Override
    public Resume get(String uuid) {
        try {
            PreparedStatement ps = withJDBC.initPrepareStatement("SELECT * FROM resume WHERE uuid =?");
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
        if (!isFoundException(r.getUuid())) throw new NotExistStorageException(r.getUuid());
        try {
            PreparedStatement ps = withJDBC.initPrepareStatement("update resume set uuid=?,full_name=? WHERE uuid=?");
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            ps.setString(3, r.getUuid());
        } catch (SQLException e) {
            throw new StorageException(e.getMessage(), r.getUuid());
        }
    }

    @Override
    public void save(Resume r) {
        if (isFoundException(r.getUuid())) throw new ExistStorageException(r.getUuid());
        try {
            PreparedStatement ps = withJDBC.initPrepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)");
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e.getMessage(), r.getUuid());
        }
    }

    @Override
    public void delete(String uuid) {
        if (!isFoundException(uuid)) throw new NotExistStorageException(uuid);
        try {
            PreparedStatement ps = withJDBC.initPrepareStatement("DELETE FROM resume WHERE uuid=?");
            ps.setString(1, uuid);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new StorageException(e.getMessage(), uuid);
        }
    }

    @Override
    public List<Resume> getAllSortedList() {
        List<Resume> resumes = new LinkedList<>();
        try {
            Statement ps = withJDBC.initStatement();
            ResultSet resultSet = ps.executeQuery("SELECT * FROM resume");
            while (resultSet.next()) {
                resumes.add(
                        new Resume(resultSet.getString(1).trim(),
                                resultSet.getString(2).trim())
                );
            }
        } catch (SQLException e) {
            throw new StorageException(e.getMessage());
        }
        return resumes;
    }

    @Override
    public int size() {
        int size;
        try {
            Statement ps = withJDBC.initStatement();
            ResultSet resultSet = ps.executeQuery("SELECT count(*) FROM resume");
            resultSet.next();
            size = resultSet.getInt(1);
        } catch (SQLException e) {
            throw new StorageException(e.getMessage());
        }
        return size;
    }

    public boolean isFoundException(String uuid) {
        boolean found = false;
        try {
            PreparedStatement statement = withJDBC.initPrepareStatement("SELECT * FROM resume WHERE uuid = ?");
            statement.setString(1, uuid);
            ResultSet resultSet = statement.executeQuery();
            found = resultSet.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return found;
    }
}