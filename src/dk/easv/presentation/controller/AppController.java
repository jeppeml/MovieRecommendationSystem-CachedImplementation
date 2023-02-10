package dk.easv.presentation.controller;

import dk.easv.entities.*;
import dk.easv.presentation.model.AppModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;
import java.util.List;



public class AppController implements Initializable {
    @FXML
    private TilePane tilePane1,tilePane2,tilePane3;

        @FXML
    private TextField lblLoggedInUser;

    @FXML
    private Button btnSignOut;

    private List<Movie> getTopAverageRatedMovies;

    private List<Movie> getTopAverageRatedMoviesUserDidNotSee;

    private List<TopMovie> getTopMoviesFromSimilarPeople;

    private AppModel model;
    private long timerStartMillis = 0;
    private String timerMsg = "";

    private final int LINE_BREAK_LETTER=12;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setModel(AppModel model) throws FileNotFoundException {
        this.model = model;
        showUserName();
        getTopAverageRatedMovies=model.getTopAverageRatedMovies();
        getTopAverageRatedMoviesUserDidNotSee=model.getTopAverageRatedMoviesUserDidNotSee();
        getTopMoviesFromSimilarPeople=model.getTopMoviesFromSimilarPeople();
        setUpGribPanes("getTopAverageRatedMovies",0);
        setUpGribPanes("getTopAverageRatedMoviesUserDidNotSee",1);
        setUpGribPanes("getTopMoviesFromSimilarPeople",2);
    }

//Marl Erev
    public void setUpGribPanes(String listName, int tilePane) throws FileNotFoundException {


        Random r0=new Random(124);
        Random r1=new Random(457);
        Random r2=new Random(788);
        TilePane[] tilePanes={tilePane1,tilePane2,tilePane3};
        javafx.scene.control.Label movieTitle;
        String line;

        Image picture;

        for (int i = 0; i < 5; i++) {

            if (listName=="getTopAverageRatedMovies")
            {
                line=changeLineInAString(getTopAverageRatedMovies.get(i).getTitle());
                movieTitle = new javafx.scene.control.Label(line);
                 picture =  new Image(new FileInputStream(("Resources/Pictures/Flower"+r0.nextInt(13)+".jpg")));

            }

            else if (listName=="getTopAverageRatedMoviesUserDidNotSee")
            {
                line=changeLineInAString(getTopAverageRatedMoviesUserDidNotSee.get(i).getTitle());
                movieTitle = new javafx.scene.control.Label(line);
                 picture =  new Image(new FileInputStream(("Resources/Pictures/Flower"+r1.nextInt(13)+".jpg")));
            }

            else
            {
                line=changeLineInAString(getTopMoviesFromSimilarPeople.get(i).getTitle());
                movieTitle = new javafx.scene.control.Label(line);
                picture =  new Image(new FileInputStream(("Resources/Pictures/Flower"+r2.nextInt(13)+".jpg")));
            }




            ImageView imageView= new ImageView(picture);
            imageView.setFitWidth(75);
            imageView.setFitHeight(100);
            VBox vbox=new VBox();
            vbox.setMinSize(200,200);
            vbox.setMaxSize(200,200);
            vbox.getChildren().add(imageView);
            vbox.getChildren().add(movieTitle);
            tilePanes[tilePane].getChildren().add(vbox);

        }


    }


    public String changeLineInAString(String line) {
        int length = line.length();
        String buildString = "";

        int number = length / LINE_BREAK_LETTER;

int i;

        for ( i = 0; i < number; i++) {


                if (line.charAt(LINE_BREAK_LETTER * (i + 1) - 1) == ' ') {
                    if (i==0)
                        buildString = buildString + line.substring(0,LINE_BREAK_LETTER * (i + 1)-1)+"\n";
                    else
                        buildString = buildString + line.substring(LINE_BREAK_LETTER * (i)-1,LINE_BREAK_LETTER * (i + 1)-1)+"\n";
                }
                else
                {
                    if (i==0)
                        buildString = buildString + line.substring(0,LINE_BREAK_LETTER * (i + 1)-1)+"-"+"\n";
                    else
                        buildString = buildString + line.substring(LINE_BREAK_LETTER * (i)-1,LINE_BREAK_LETTER * (i + 1)-1)+"-"+"\n";
                }


            }

        if (i==0)
            buildString = buildString + line.substring(0,length-1);
        else
        buildString = buildString + line.substring(LINE_BREAK_LETTER * (i)-1,length-1);


        return buildString;
    }







    public void handelSignOut(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure you want to Sign Out");
        alert.setContentText("Return back to the Login page");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage stage = (Stage) btnSignOut.getScene().getWindow();
            stage.close();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }
    public void showUserName(){
        lblLoggedInUser.setText(model.getObsLoggedInUser().getName());

    }



}
