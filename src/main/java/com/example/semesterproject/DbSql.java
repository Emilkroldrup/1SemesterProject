package com.example.semesterproject;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.effect.Bloom;
import javafx.scene.text.Font;

import java.sql.*;

public class DbSql {

    private Connection connection;

    DbSql() {
        try {
            String url = "jdbc:sqlite:C://Users//mikke//DBbrowserDATABASE//wishListAppdb.db";
            connection = DriverManager.getConnection(url);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public Connection getConnection() {
        return connection;
    }
    public void createUser(User u) {
        try (PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO bruger (navn, email, kodeord) VALUES (?, ?, ?)")) {
            pstmt.setString(1, u.getName());
            pstmt.setString(2, u.getEmail());
            pstmt.setString(3, u.getPassword());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createWish(Wish w) {
        try (PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO Wish (WishCreatedDate, wishContent) VALUES ( ?, ?)")) {
            pstmt.setString(1, w.getDateCreated());
            pstmt.setString(2, w.getWishContent());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeWish(int wishId) {
        try (PreparedStatement pstmt = connection.prepareStatement(
                "DELETE FROM Wish WHERE wishId = ?")) {
            pstmt.setInt(1, wishId);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void editWish(int wishId, String newWishCreated, String newWishContent) {
        try (PreparedStatement pstmt = connection.prepareStatement(
                "UPDATE Wish SET wishCreatedDate = ?, wishContent = ? WHERE wishId = ?")) {
            pstmt.setString(1, newWishCreated);
            pstmt.setString(2, newWishContent);
            pstmt.setInt(3, wishId);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createWishlist(WishList wl, int userId) {
        try (PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO WishList ( listeNavn, oprettelsesDato, brugerId) VALUES ( ?, ?, ?)")) {
            pstmt.setString(1, wl.getListName());
            pstmt.setString(2, wl.getCreationDate());
            pstmt.setInt(3, userId);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeWishlist(int wishlistId) {
        try {
            connection.setAutoCommit(false);

            try (PreparedStatement pstmtWishes = connection.prepareStatement(
                    "DELETE FROM Wish WHERE WishListid = ?")) {
                pstmtWishes.setInt(1, wishlistId);
                pstmtWishes.executeUpdate();
            }

            try (PreparedStatement pstmtWishlist = connection.prepareStatement(
                    "DELETE FROM WishList WHERE wishListId = ?")) {
                pstmtWishlist.setInt(1, wishlistId);
                pstmtWishlist.executeUpdate();
            }

            connection.commit();
        } catch (SQLException throwables) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
            throwables.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addWishToWishlist(int wishId, int wishlistId) {
        try (PreparedStatement pstmt = connection.prepareStatement(
                "UPDATE Wish SET WishListId = ? WHERE wishId = ?")) {
            pstmt.setInt(1, wishlistId);
            pstmt.setInt(2, wishId);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String getNameById(int id) throws SQLException {
        String name = null;
        String sql = "SELECT navn FROM bruger where brugerid = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()){
                    name = rs.getString("navn");
                }
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return name;
    }

    public int fetchBrugerIdByName(String name) {
        int brugerId = -1;
        String sql = "SELECT brugerId FROM bruger WHERE navn = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    brugerId = rs.getInt("brugerId");
                }
            }
        }   catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return brugerId;
    }

    public int fetchWishlistIdByName(String wishlistName) {
        int wishlistId = -1;
        String sql = "SELECT wishListId From WishList WHERE listeNavn = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, wishlistName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    wishlistId = rs.getInt("wishListId");
                }
            }
        }   catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return wishlistId;
    }
/*
    public List<Integer> fetchWishIdsByUserId(int userId) {
        List<Integer> wishIds = new ArrayList<>();
        String sql = "SELECT w.wishId FROM Wish w INNER JOIN WishList wl ON w.wishListId = wl.wishListId WHERE wl.brugerId = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    wishIds.add(rs.getInt("wishId"));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return wishIds;
    }

 */

    public int fetchWishIdsByUserId(int userId) {
        int wishId = -1;
        String sql = "SELECT w.wishId FROM Wish w INNER JOIN WishList wl ON w.wishListId = wl.wishListId WHERE wl.brugerId = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    wishId = (rs.getInt("wishId"));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return wishId;
    }

    public int fetchWishIdByWishName(String wishName){
        int Id = -1;
        String sql = "SELECT wishId FROM wish WHERE wishContent = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, wishName);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Id = rs.getInt("wishId");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Id;
    }

    public boolean fetchPasswordByMail(String mail, String pass) {
        String password = null;
        String sql = "SELECT kodeord FROM bruger WHERE email = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, mail);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    password = rs.getString("kodeord");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return password != null && password.equals(pass);
    }

    public int fetchUserIdByMail(String mail){
        int userId = -1;
        String sql = "SELECT brugerId FROM bruger WHERE email = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, mail);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()){
                    userId = rs.getInt("brugerId");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userId;
    }


}

