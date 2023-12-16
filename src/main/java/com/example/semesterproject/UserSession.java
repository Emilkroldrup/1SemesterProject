package com.example.semesterproject;

public class UserSession {
    private static UserSession instance;
    private int userId;

    private UserSession() {
        // private constructor to enforce singleton pattern
    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
