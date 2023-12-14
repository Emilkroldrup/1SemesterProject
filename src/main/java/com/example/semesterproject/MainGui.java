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
        // Initialize the database connection
        DbSql db = new DbSql();
        Dbsqlgui.initializeDb(db);

        // Load Scene0.fxml and set the DbSql instance in SceneController0
        FXMLLoader loader0 = new FXMLLoader(MainGui.class.getResource("Scene0.fxml"));
        Scene scene0 = new Scene(loader0.load(), 375, 812);
        SceneController0 controller0 = loader0.getController();
        controller0.setDb(Dbsqlgui.getDb());

 // Load Scene1.fxml and set the DbSql instance in SceneController1
        FXMLLoader loader1 = new FXMLLoader(MainGui.class.getResource("Scene1.fxml"));
        Scene scene1 = new Scene(loader1.load(), 375, 812);
        SceneController1 controller1 = loader1.getController();
        controller1.setDb(Dbsqlgui.getDb());

        // Load Scene2.fxml and set the DbSql instance in SceneController2
        FXMLLoader loader2 = new FXMLLoader(MainGui.class.getResource("Scene2.fxml"));
        Scene scene2 = new Scene(loader2.load(), 375, 812);
        SceneController2 controller2 = loader2.getController();
        controller2.setDb(Dbsqlgui.getDb());

        // Load Scene3.fxml and set the DbSql instance in SceneController3
        FXMLLoader loader3 = new FXMLLoader(MainGui.class.getResource("Scene3.fxml"));
        Scene scene3 = new Scene(loader3.load(), 375, 812);
        SceneController3 controller3 = loader3.getController();
        controller3.setDb(Dbsqlgui.getDb());

        // Set up the stage with the initial scene
        stage.setTitle("JuleGaveRegisterApp");
        stage.setScene(scene0);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}