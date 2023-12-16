package com.example.semesterproject;

public class WishlistSession {
    private static WishlistSession instance;
    private int userId;

    private WishlistSession() {
    }

    public static WishlistSession getInstance() {
        if (instance == null) {
            instance = new WishlistSession();
        }
        return instance;
    }

    public int getWishlistId() {
        return userId;
    }

    public void setWishlistId(int userId) {
        this.userId = userId;
    }

}
