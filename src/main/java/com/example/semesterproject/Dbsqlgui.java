package com.example.semesterproject;

public class Dbsqlgui {


    private static DbSql db;

    private Dbsqlgui() {
        // private constructor to prevent instantiation
    }

    public static DbSql getDb() {
      
        return db;
    }

    public static void initializeDb(DbSql dbInstance) {

            db = dbInstance;
        }
    }
