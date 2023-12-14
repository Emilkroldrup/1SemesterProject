package com.example.semesterproject;

public class Dbsqlgui {

    private static DbSql db;

    private Dbsqlgui() {
        // private constructor to prevent instantiation
    }

    public static DbSql getDb() {
        if (db == null) {
            throw new IllegalStateException("Database not initialized. Call initializeDb before accessing.");
        }
        return db;
    }

    public static void initializeDb(DbSql dbInstance) {
        if (db == null) {
            db = dbInstance;
        } else {
            throw new IllegalStateException("Database already initialized.");
        }
    }
}
