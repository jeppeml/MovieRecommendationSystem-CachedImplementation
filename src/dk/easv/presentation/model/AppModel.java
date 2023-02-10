package dk.easv.presentation.model;

import dk.easv.entities.*;
import dk.easv.logic.LogicManager;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class AppModel {

    LogicManager logic = new LogicManager();
    // Models of the data in the view
    private final ObservableList<User>  obsUsers = FXCollections.observableArrayList();
  

    private final SimpleObjectProperty<User> obsLoggedInUser = new SimpleObjectProperty<>();

    public void loadUsers(){
        obsUsers.clear();
        obsUsers.addAll(logic.getAllUsers());
    }


    public User getObsLoggedInUser() {
        return obsLoggedInUser.get();
    }


    public boolean loginUserFromUsername(String userName) {
        User u = logic.getUser(userName);
        obsLoggedInUser.set(u);
        if (u==null)
            return false;
        else
            return true;
    }

    public List<Movie> getTopAverageRatedMovies()
    {
        return logic.getTopAverageRatedMovies(getObsLoggedInUser());
    }


    public List<Movie> getTopAverageRatedMoviesUserDidNotSee()
    {
        return logic.getTopAverageRatedMoviesUserDidNotSee(getObsLoggedInUser());
    }

    public List<TopMovie> getTopMoviesFromSimilarPeople()
    {
        return logic.getTopMoviesFromSimilarPeople(getObsLoggedInUser());
    }



}
