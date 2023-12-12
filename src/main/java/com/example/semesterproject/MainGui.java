package com.example.semesterproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainGui extends Application {
    //Loader applikationen op og start scenen, plus størrelsen og nanvnet
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainGui.class.getResource("Scene0.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 375, 812);
        stage.setTitle("JuleGaveRegisterApp");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}