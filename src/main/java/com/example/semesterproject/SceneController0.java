package com.example.semesterproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;


public class SceneController0 {

    //Når der står @FXML er det fordi det i fxml filen og bliver brugt til asscoricere tingene/link dem, når de er private
    @FXML
    private BorderPane Layout0;

    @FXML
    private Button Login;

    @FXML
    private TextField Password;



    @FXML
    protected void onHelloButtonClick() throws IOException{


        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene1.fxml"));
        Parent root = loader.load();



        Scene scene1 = new Scene(root);
        Stage stage = (Stage) Layout0.getScene().getWindow();
        stage.setScene(scene1);

    }
    //Funktion skal blive kaldt med det samme, så når man skriver det første bogstav at der er funktionen allerede blevet kaldt
    public void initialize()  {


        //Her adder jeg en listener, new value er altid det første/nye  input, hvor oldvalue Får det næste første input når newinput har fået input2
        Password.textProperty().addListener((observable, oldValue, newValue) -> {
            //Her bruger jeg newvalue og ikke oldvalue, da newvalue er det første input, så hvis jeg tager old ville der bare ske fejl.
            if( newValue.contains(" ")){
                Password.setText(newValue.replaceAll(" ", ""));
            }
            else{
                Password.setText(newValue.replaceAll(".", "*"));
            }
        });
    }

}