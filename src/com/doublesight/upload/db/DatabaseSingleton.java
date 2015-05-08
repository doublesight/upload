package com.doublesight.upload.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class DatabaseSingleton {

    private static volatile DatabaseSingleton mInstance;
    private static final Object LOCK = new Object();
    private Connection mConnection;

    public static DatabaseSingleton getInstance() {
        DatabaseSingleton ret = mInstance;
        if (ret == null) {
            synchronized (LOCK) {
                ret = mInstance;
                if (ret == null) {
                    ret = new DatabaseSingleton();
                    mInstance = ret;
                }
            }
        }
        return ret;
    }

    private DatabaseSingleton() {
        try {
            mConnection = DriverManager.getConnection("jdbc:sqlite:campaigns.db");
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            System.exit(-1);
        }

        createInitialTable();
    }

    private void createInitialTable() {
        try (Statement stmt = mConnection.createStatement()) {
            final String sql = "CREATE TABLE IF NOT EXISTS CAMPAIGNS " +
                    "(ID INT PRIMARY KEY ASC AUTOINCREMENT," +
                    "FAVORABLE_TIME_EPOCHES INTEGER, " +
                    "FAVORABLE_WEATHER_EPOCHES INTEGER, " +
                    "WEIGHTED_TAGS TEXT NOT NULL, " +
                    "MEDIA BLOB NOT NULL)";
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
