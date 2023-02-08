package dk.easv;

import dk.easv.presentation.controller.LogInController;
import dk.easv.presentation.model.AppModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("presentation/view/LogIn.fxml"));
        Parent root = loader.load();
        
        primaryStage.setTitle("Movie Recommendation System 1.1");
        // primaryStage.setFullScreen(true);
        primaryStage.setScene(new Scene(root));
        primaryStage.getScene().getStylesheets().add(getClass().getResource("/CSS/Login.css").toExternalForm());
        primaryStage.setMaxHeight(620);
        primaryStage.setMaxWidth(700);
        primaryStage.setMinHeight(620);
        primaryStage.setMinWidth(700);
        primaryStage.setResizable(false);
        primaryStage.show();


    }

    public static void main(String[] args){
        launch(args);
    }
}
