package com.urise.webapp.sql;


import java.sql.*;

public class ActionWithJDBC {
    private final ConnectionFactory factory;
    private Connection connection;

    public ActionWithJDBC(String dbUrl, String dbUser, String dbPassword) {
        factory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        initConnection(factory);
    }

    private void initConnection(ConnectionFactory factory) {
        try {
            this.connection = factory.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public PreparedStatement initPrepareStatement(String sql) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return statement;
    }

    public Statement initStatement() {
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return statement;
    }
}
