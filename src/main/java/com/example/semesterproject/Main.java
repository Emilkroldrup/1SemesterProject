package com.example.semesterproject;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DbSql db = new DbSql();

        User u1 = new User( "Mads", "mads-gmail.dk", "mads123");
        User u2 = new User( "Oliver", "oliver-gmail.com", "oliver123");
        User u3 = new User( "Emil", "emil-gmail.com", "emil123");
        User u4 = new User("Mikkel", "mikkel-gmail.dk", "mikkel123");
        db.createUser(u1);
        db.createUser(u2);
        db.createUser(u3);
        db.createUser(u4);

        WishList list1 = new WishList("test", "1/1/1");
        db.createWishlist(list1, db.fetchBrugerIdByName("Mikkel"));

        Wish w1 = new Wish("1/1/1", "Mikkel", "Et Stort kram fra susanne");
        Wish w2 = new Wish("2/2/2", "Emil", "To store kram fra Susanne");
        db.createWish(w1);
        db.createWish(w2);

        WishList list2 = new WishList("Emils ønsker", "2/2/2");
        db.createWishlist(list2, db.fetchBrugerIdByName("Emil"));

        db.addWishToWishlist(db.fetchWishIdsByUserId(db.fetchBrugerIdByName("Emil")), db.fetchWishlistIdByName("Emil") );

        //System.out.println(db.isPasswordMatch("mads-gmail.dk", "mads123"));








    }
}
