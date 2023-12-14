package com.example.semesterproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController1 {

    //Når der står @FXML er det fordi det i fxml filen og bliver brugt til asscoricere tingene/link dem, når de er private. Her ville det forbinde det her element fra scene builder til koden om Layout1
    @FXML private BorderPane Layout1;

    @FXML
    private Button wishbuying;

    @FXML
    private Button Back;

    @FXML
    private Label JuleText;



    @FXML
    protected void onHelloButtonClick1() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene2.fxml"));
        Parent root = loader.load();


        Stage stage = (Stage) Layout1.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    protected void onHelloButtonClick2() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene0.fxml"));
        Parent root = loader.load();

        Scene scene0 = new Scene(root);
        Stage stage = (Stage) Layout1.getScene().getWindow();
        stage.setScene(scene0);
    }
}