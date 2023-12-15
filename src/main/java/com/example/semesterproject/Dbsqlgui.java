package com.example.semesterproject;

public class Dbsqlgui {

    private static DbSql db;

    private Dbsqlgui() {
        // private constructor to prevent instantiation
    }

    public static DbSql getDb() {
        System.out.println("DEBUG: Dbsqlgui - getDb - db: " + db);
        if (db == null) {
            throw new IllegalStateException("Database not initialized. Call initializeDb before accessing.");
        }
        return db;
    }

    public static void initializeDb(DbSql dbInstance) {
        if (db == null) {
            db = dbInstance;
            System.out.println("DEBUG: Dbsqlgui - Database initialized.");
        } else {
            System.out.println("DEBUG: Dbsqlgui - Database reinitialized.");
            // If reinitialization is allowed, you can update the existing instance.
            db = dbInstance;
        }
    }
}