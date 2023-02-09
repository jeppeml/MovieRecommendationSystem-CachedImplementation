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

    private void startTimer(String message){
        timerStartMillis = System.currentTimeMillis();
        timerMsg = message;
    }

    private void stopTimer(){
        System.out.println(timerMsg + " took : " + (System.currentTimeMillis() - timerStartMillis) + "ms");
    }

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


        Random random=new Random();
        TilePane[] tilePanes={tilePane1,tilePane2,tilePane3};
        javafx.scene.control.Label movieTitle;
        for (int i = 0; i < 6; i++) {

            if (listName=="getTopAverageRatedMovies")
            {
                movieTitle = new javafx.scene.control.Label(getTopAverageRatedMovies.get(i).getTitle());
            }
            else if (listName=="getTopAverageRatedMoviesUserDidNotSee")
            {
                 movieTitle = new javafx.scene.control.Label(getTopAverageRatedMoviesUserDidNotSee.get(i).getTitle());
            }
            else
            {
                 movieTitle = new javafx.scene.control.Label(getTopMoviesFromSimilarPeople.get(i).getTitle());
            }

            Image picture =  new Image(new FileInputStream(("Resources/Pictures/Flower"+random.nextInt(13)+".jpg")));
            ImageView imageView= new ImageView(picture);
            imageView.setFitWidth(75);
            imageView.setFitHeight(100);
            VBox vbox=new VBox();
            vbox.getChildren().add(imageView);
            vbox.getChildren().add(movieTitle);
            tilePanes[tilePane].getChildren().add(vbox);

        }



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

    /*
     lvUsers.setItems(model.getObsUsers());
        lvTopForUser.setItems(model.getObsTopMovieSeen());
        lvTopAvgNotSeen.setItems(model.getObsTopMovieNotSeen());
        lvTopSimilarUsers.setItems(model.getObsSimilarUsers());
        lvTopFromSimilar.setItems(model.getObsTopMoviesSimilarUsers());

        startTimer("Load users");
        model.loadUsers();
        stopTimer();

        lvUsers.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldUser, selectedUser) -> {
                    startTimer("Loading all data for user: " + selectedUser);
                    model.loadData(selectedUser);
                });

        // Select the logged-in user in the listview, automagically trigger the listener above
        lvUsers.getSelectionModel().select(model.getObsLoggedInUser());
        showUserName();
     */


}
