package com.example.semesterproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.io.IOException;


public class SceneController0 {

    //Når der står @FXML er det fordi det i fxml filen og bliver brugt til asscoricere tingene/link dem, når de er private
    @FXML
    private BorderPane Layout0;

    @FXML
    private Button Login;

    @FXML
    private PasswordField Password;

    @FXML
    private TextField mail;

    private DbSql db;

    public SceneController0() {
        this.db = new DbSql();
    }

    //Loader FXML filen, hvor den så laver en ny scene med den loaded root node og så sætter den scene til den nuværende scene
    @FXML
    protected void onHelloButtonClick() throws IOException{
        String email = mail.getText();
        String password = Password.getText();
        if(db.fetchPasswordByMail(email, password)) {
            //Her laver jeg en fxmlloader med stien til scene1
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene1.fxml"));

            //Her kunne jeg også skrive Vbox da det er the root af den her scene, men jeg skriver bare parent da det virker med det hele. Så jeg giver rooten til Vbox Her
            Parent root = loader.load();

            //her henter jeg det nuværende vindue fra den her scene
            Stage stage = (Stage) Layout0.getScene().getWindow();

            //Opdatere scenen med en ny scene med inputtet top root element fra før
            stage.setScene(new Scene(root));
        }
    }

    //Funktion skal blive kaldt med det samme, så når man skriver det første bogstav at der er funktionen allerede blevet kaldt
    public void initialize()  {
        //tilføjer en listener til passwords tekstfelt
        Password.textProperty().addListener((observable, oldValue, newValue) -> {
            //Her bruger jeg newvalue aka tastaturens nyeste input til at fjerne mellemrum input.
            if( newValue.contains(" ")){
                String nytekst = newValue.replace(" ", "");
                Password.setText(nytekst);
            }
        });
    }
}