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

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;


public class SceneController2 {

    //Når der står @FXML er det fordi det i fxml filen og bliver brugt til asscoricere tingene/link variblem til elementet i fxml filen, når de er private
    @FXML
    private Label Wishlist;

    @FXML
    private BorderPane Layout2;

    @FXML
    private Button Back;

    @FXML
    private Button add;

    @FXML private VBox vbox;
    public int xpos1 = -38;
    public int ypos1 = 45;
    public int xpos2 = 147;
    public int ypos2 = 2;
    public Button NavnWishList;
    public ChoiceBox<String> Mulighedbox;
    public String[] Muligheder = {"Edit", "Delete"};

    GemData data = GemData.getInstance();

    public static int counter = 0;


    Optional<String> Userinput;


    private DbSql db;
    private Connection connection;

    public void initialize()  {
        this.db = Dbsqlgui.getDb();
        this.connection= db.getConnection();
        try{
            String sql = "SELECT listeNavn FROM WishList WHERE brugerId = " + UserSession.getInstance().getUserId();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                NavnWishList = new Button(rs.getString("listeNavn"));
                Bloom bloom = new Bloom(0.33);
                NavnWishList.setEffect(bloom);
                NavnWishList.setStyle("-fx-background-color: green;");
                NavnWishList.setAlignment(Pos.CENTER_LEFT);
                NavnWishList.setFont(Font.font(20));
                NavnWishList.setPrefWidth(290);
                NavnWishList.setTranslateX(xpos1);
                NavnWishList.setTranslateY(ypos1);
                vbox.getChildren().add(NavnWishList);

                Mulighedbox= new ChoiceBox<>();
                Mulighedbox.getItems().addAll(Muligheder);
                Mulighedbox.setTranslateX(xpos2);
                Mulighedbox.setTranslateY(ypos2);
                //Her kan jeg ikke ændre dropbox farven, min forståelse er at man skal bruge css for at at gøre det
                Mulighedbox.setStyle("-fx-background-color: green;");
                Mulighedbox.setEffect(bloom);
                vbox.getChildren().add(Mulighedbox);
                NavnWishList.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        try {
                            //Kalder på onhelloclick metoden og indsætter string værdien userinput i onhellos  parameter.
                            onHelloButtonClick1(event, Userinput.get());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });


            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    @FXML
    protected void onHelloButtonClick1( ActionEvent event, String ButtonNavn) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene3.fxml"));
        Parent root = loader.load();

        //Her kalder jeg på scene3 controller og loader den
        SceneController3 scene3Controller = loader.getController();
        //Loader bare Navnet på knappen og sætter det ind på scene3's controller. <
        // Jeg kalder også på scene3s metode setbuttontext og indsætter buttonnavn værdien i dens parameter
        scene3Controller.setButtonText(ButtonNavn);

        Scene scene3 = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene3);

    }
    @FXML
    private void addNewButton() {
        //Her oprettet man et textinput dialog, som brugeren skal skrive navnet på
        TextInputDialog Navntilknap = new TextInputDialog();
        //Navn på textdialog
        Navntilknap.setTitle("Ønskeliste Navn");
        //Text input, for at vide hvad brugeren skal
        Navntilknap.setHeaderText("Skriv navnet på din ønskeliste☃");
        //Foran brugerens input inputtet
        Navntilknap.setContentText("Navn:");

        //Her bruger vi bare showandwait,altså så brugeren kan få skrevet input og at den først kommer med navnet når de klikker OK
        //Optional som gør at userinputtet kan være null eller ikke være null og udfylde betingelser de to betingelser
        Userinput = Navntilknap.showAndWait();

        //Hvis man trykker OK får den en værdi og den køre dette>
        if(Userinput.isPresent()){
            //Ligemeget hvad hvis brugeren trykker ok så får userinput en værdi, derfor tjekker man om strengen ikke er tom før man fortsætter
            if(!Userinput.get().isEmpty()){
                //Her kalder jeg Button NavnWishList og gir den et userinput som teksten.
                NavnWishList = new Button(Userinput.get());
                WishList wl = new WishList(Userinput.get(), "11-11-11");
                db.createWishlist(wl, UserSession.getInstance().getUserId());
                //Her justerer jeg den og gir den resten af effekterne mm.
                Bloom bloom = new Bloom(0.33);
                NavnWishList.setEffect(bloom);
                NavnWishList.setStyle("-fx-background-color: green;");
                NavnWishList.setAlignment(Pos.CENTER_LEFT);
                NavnWishList.setFont(Font.font(20));
                NavnWishList.setPrefWidth(290);
                NavnWishList.setTranslateX(xpos1);
                NavnWishList.setTranslateY(ypos1);
                vbox.getChildren().add(NavnWishList);

                Mulighedbox= new ChoiceBox<>();
                Mulighedbox.getItems().addAll(Muligheder);
                Mulighedbox.setTranslateX(xpos2);
                Mulighedbox.setTranslateY(ypos2);
                //Her kan jeg ikke ændre dropbox farven, min forståelse er at man skal bruge css for at at gøre det
                Mulighedbox.setStyle("-fx-background-color: green;");
                Mulighedbox.setEffect(bloom);
                vbox.getChildren().add(Mulighedbox);


                //En event handler der bliver kaldt ved tryk/klik af knappen Navnwishlist
                NavnWishList.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        try {
                            //Kalder på onhelloclick metoden og indsætter string værdien userinput i onhellos  parameter.
                            onHelloButtonClick1(event, Userinput.get());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

            }
        }
    }

    @FXML
    protected void onHelloButtonClick2() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene1.fxml"));
        Parent root = loader.load();


        Scene scene1 = new Scene(root);
        Stage stage = (Stage) Layout2.getScene().getWindow();
        stage.setScene(scene1);

    }
}