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
import java.util.Optional;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class SceneController3 implements Initializable  {
    //Når der står @FXML er det fordi det i fxml filen og bliver brugt til asscoricere tingene/link dem, når de er private.
    @FXML
    private VBox Layout3;

    @FXML
    private Button Back;

    @FXML
    private VBox vbox1;

    @FXML
    private Button addwishesButton;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label label4;

    @FXML
    private VBox vbox2;

    @FXML
    private Label Wishlist;



    public int xpos1 = -38;
    public int ypos1 = 45;
    public int xpos2 = 147;
    public int ypos2 = 2;
    public ChoiceBox<String> choicebox;
    public String[] choices = {"Edit", "Delete"};

    @FXML
    private VBox vbox3;


    private DbSql db;
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.db = Dbsqlgui.getDb();
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
    protected void addwishesButtonClick() {
        TextInputDialog navnTilKnap = new TextInputDialog();
        navnTilKnap.setTitle("Nyt ønske");
        navnTilKnap.setHeaderText("Indtast info om ønsket");
        navnTilKnap.setContentText("Ønske: ");

        Optional<String> userInput = navnTilKnap.showAndWait();

        if (userInput.isPresent()) {

            if (!userInput.isEmpty()) {
                addwishesButton = new Button(userInput.get());
                db.addWishToWishlist(Integer.parseInt(userInput.get()), Integer.parseInt(userInput.get()));

                Bloom bloom = new Bloom(0.33);
                addwishesButton.setEffect(bloom);
                addwishesButton.setStyle("-fx-background-color: green;");
                addwishesButton.setAlignment(Pos.CENTER_LEFT);
                addwishesButton.setFont(Font.font(20));
                addwishesButton.setPrefWidth(290);
                addwishesButton.setTranslateX(xpos1);
                addwishesButton.setTranslateY(ypos1);
                vbox2.getChildren().add(addwishesButton);

                choicebox= new ChoiceBox<>();
                choicebox.getItems().addAll(choices);
                choicebox.setTranslateX(xpos2);
                choicebox.setTranslateY(ypos2);
                choicebox.setStyle("-fx-background-color: green;");
                choicebox.setEffect(bloom);
                vbox2.getChildren().add(choicebox);

                addwishesButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //Kalder på newWishAppear metoden og indsætter string værdien userinput i onhellos  parameter.
                        newWishAppear(userInput.get());
                    }
                });

            }
        }
    }
    @FXML
    protected void newWishAppear(String labelNavn) {

    }



    @FXML
    protected void onHelloButtonClick2(ActionEvent event)throws IOException  {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene2.fxml"));
        Parent root = loader.load();

        Scene scene2 = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene2);
    }
}
