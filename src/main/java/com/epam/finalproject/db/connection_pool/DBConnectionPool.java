package com.epam.finalproject.db.connection_pool;


import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnectionPool {

    private static final BasicDataSource ds = new BasicDataSource();
    private static final String DATABASE_BUNDLE_NAME = "database";

    private DBConnectionPool() {}

    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(DATABASE_BUNDLE_NAME);
        ds.setDriverClassName(resourceBundle.getString("db.driver"));
        ds.setUrl(resourceBundle.getString("db.url"));
        ds.setUsername(resourceBundle.getString("db.user"));
        ds.setPassword(resourceBundle.getString("db.password"));
        ds.setMinIdle(3);//set min count of non-active connection
        ds.setMaxIdle(7);//set max count of non-active connection
        ds.setMaxOpenPreparedStatements(50);
    }
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
