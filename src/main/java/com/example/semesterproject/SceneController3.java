package com.example.semesterproject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.Bloom;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Optional;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class SceneController3  {
    //Når der står @FXML er det fordi det i fxml filen og bliver brugt til asscoricere tingene/link dem, når de er private.


    @FXML
    private VBox vbox1;

    @FXML
    private Button addwishesButton;

    @FXML
    private Label Wishlist;

    public int xpos1 = -38;
    public int ypos1 = 45;
    public int xpos2 = 147;
    public int ypos2 = 2;


    private DbSql db;

    private Connection connection;
    public Optional<String> Userinput;
    public Button NavnWish;
    public ChoiceBox<String> Mulighedbox;
    public String[] Muligheder = {"Edit", "Delete"};

    public void initialize() {
        this.db = Dbsqlgui.getDb();
        this.connection = db.getConnection();
        try {
            String sql = "SELECT wishContent FROM wish WHERE wishListId =" + WishlistSession.getInstance().getWishlistId();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                NavnWish = new Button(rs.getString("wishContent"));
                Bloom bloom = new Bloom(0.33);
                NavnWish.setEffect(bloom);
                NavnWish.setStyle("-fx-background-color: green;");
                NavnWish.setAlignment(Pos.CENTER_LEFT);
                NavnWish.setFont(Font.font(20));
                NavnWish.setPrefWidth(290);
                NavnWish.setTranslateX(xpos1);
                NavnWish.setTranslateY(ypos1);
                vbox1.getChildren().add(NavnWish);

                Mulighedbox = new ChoiceBox<>();
                Mulighedbox.getItems().addAll(Muligheder);
                Mulighedbox.setTranslateX(xpos2);
                Mulighedbox.setTranslateY(ypos2);
                Mulighedbox.setStyle("-fx-background-color: green;");
                Mulighedbox.setEffect(bloom);
                vbox1.getChildren().add(Mulighedbox);
            }
            rs.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void setButtonText(String ButtonNavn) {
        //String index starter fra 0, så derfor bruger man -1.
        if (ButtonNavn.lastIndexOf('s') != ButtonNavn.length() - 1) {
            Wishlist.setText(ButtonNavn + "'s" + " WishList\uD83C\uDF81");
        } else {
            Wishlist.setText(ButtonNavn + "'" + " WishList\uD83C\uDF81");
        }
    }

    @FXML
    protected void addwishesButtonClick() throws SQLException {

        TextInputDialog Navntilknap = new TextInputDialog();
        Navntilknap.setTitle("Ønske Navn");
        Navntilknap.setHeaderText("Skriv navnet på din ønskeliste☃");
        Navntilknap.setContentText("Navn:");
        Userinput = Navntilknap.showAndWait();

        if (Userinput.isPresent()) {
            if (!Userinput.get().isEmpty()) {
                NavnWish = new Button(Userinput.get());
                Wish w = new Wish("1/1/1", db.getNameById(UserSession.getInstance().getUserId()), Userinput.get());
                db.createWish(w);
                db.addWishToWishlist(db.fetchWishIdByWishName(Userinput.get()), WishlistSession.getInstance().getWishlistId());

                Bloom bloom = new Bloom(0.33);
                NavnWish.setEffect(bloom);
                NavnWish.setStyle("-fx-background-color: green;");
                NavnWish.setAlignment(Pos.CENTER_LEFT);
                NavnWish.setFont(Font.font(20));
                NavnWish.setPrefWidth(290);
                NavnWish.setTranslateX(xpos1);
                NavnWish.setTranslateY(ypos1);
                vbox1.getChildren().add(NavnWish);

                Mulighedbox = new ChoiceBox<>();
                Mulighedbox.getItems().addAll(Muligheder);
                Mulighedbox.setTranslateX(xpos2);
                Mulighedbox.setTranslateY(ypos2);

                Mulighedbox.setStyle("-fx-background-color: green;");
                Mulighedbox.setEffect(bloom);
                vbox1.getChildren().add(Mulighedbox);
            }
        }
    }


    @FXML
    protected void onHelloButtonClick2(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene2.fxml"));
        Parent root = loader.load();

        Scene scene2 = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene2);
    }
}

