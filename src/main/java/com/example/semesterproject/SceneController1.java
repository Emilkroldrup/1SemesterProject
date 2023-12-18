package com.example.semesterproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

public class SceneController1  implements Initializable {

    //Når der står @FXML er det fordi det i fxml filen og bliver brugt til asscoricere tingene/link dem, når de er private. Her ville det forbinde det her element fra scene builder til koden om Layout1
    @FXML private BorderPane Layout1;


    private DbSql db;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.db = Dbsqlgui.getDb();
    }

    @FXML
    protected void onHelloButtonClick1() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene2.fxml"));
        Parent root = loader.load();
        System.out.println(UserSession.getInstance().getUserId());
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