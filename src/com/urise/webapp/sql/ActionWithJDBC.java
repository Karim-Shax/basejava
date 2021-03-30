package com.urise.webapp.sql;


import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.StorageException;

import java.sql.*;

public class ActionWithJDBC {
    private final ConnectionFactory factory;

    public ActionWithJDBC(ConnectionFactory connectionFactory) {
        factory = connectionFactory;
    }

    public void execute(String sql) {
        execute(sql, PreparedStatement::execute);
    }

    public <T> T execute(String sql, SqlExecutor<T> executor) {
        try (Connection connection = factory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            return executor.execute(statement);
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                throw new ExistStorageException(e.getMessage());
            }
            throw new StorageException(e.getMessage());
        }
    }

    public <T> T transactionalExecute(SqlTransaction<T> executor) {
        try (Connection conn = factory.getConnection()) {
            try {
                conn.setAutoCommit(false);
                T res = executor.execute(conn);
                conn.commit();
                return res;
            } catch (SQLException e) {
                conn.rollback();
                if (e.getSQLState().equals("23505")) {
                    throw new ExistStorageException(e.getMessage());
                }
                throw new StorageException(e.getMessage());
            }
        } catch (SQLException e) {
            throw new StorageException(e.getMessage());
        }
    }

}
