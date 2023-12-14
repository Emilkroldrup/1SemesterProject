package com.example.semesterproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


public class SceneController3 {
    //Når der står @FXML er det fordi det i fxml filen og bliver brugt til asscoricere tingene/link dem, når de er private.
    @FXML
    private VBox Layout3;

    @FXML
    private Button Back;

    @FXML
    private Label Wishlist;

    private DbSql db;
    public void setDb(DbSql dbb) {
        this.db = dbb;
    }
    public void setButtonText(String ButtonNavn) {
        //String index starter fra 0, så derfor bruger man -1.
        if(ButtonNavn.lastIndexOf('s') !=ButtonNavn.length() - 1){
            Wishlist.setText(ButtonNavn + "'s" + " WishList\uD83C\uDF81");
        }
        else{
            Wishlist.setText(ButtonNavn + "'" +" WishList\uD83C\uDF81");
        }
    }

    @FXML
    protected void onHelloButtonClick2()throws IOException  {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene2.fxml"));
        Parent root = loader.load();

        Scene scene2 = new Scene(root);
        Stage stage = (Stage) Layout3.getScene().getWindow();
        stage.setScene(scene2);
    }
}
