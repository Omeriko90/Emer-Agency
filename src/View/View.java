package View;

import Controller.Controller;
import Model.Model;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class View extends AView {


    public void setController(Controller theController) {
        theController = theController;
    }

    @FXML
    public void initialize() {
    }

    //load create event fxml
    public void createEvent() throws IOException {
        Parent root = null;
        try {
            //URL location = getClass().getResource("/createEvent.fxml");
            String path = "file:\\C:\\Users\\dtzalach\\IdeaProjects\\Emer-Agency\\out\\production\\EmerAgency\\createEvent.fxml";//location.getPath();
            URL finalLocation = new URL(path);
            FXMLLoader myLoader = new FXMLLoader();
            myLoader.setLocation(finalLocation);//getClass().getResource("C:\\Users\\dtzalach\\IdeaProjects\\Emer-Agency\\src\\ViewFxml"));//"/ViewFxml/createEvent.fxml"));
            root = myLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 600, 480);
        //scene.getStylesheets().add(getClass().getResource("/TravelApp.css").toExternalForm());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    //loads the update fxml file
    public void addUpdate() {
        Parent root = null;
        try {
            //URL location = getClass().getResource("/createEvent.fxml");
            String path = "file:\\C:\\Users\\dtzalach\\IdeaProjects\\Emer-Agency\\out\\production\\EmerAgency\\addUpdate.fxml";//location.getPath();
            URL finalLocation = new URL(path);
            FXMLLoader myLoader = new FXMLLoader();
            myLoader.setLocation(finalLocation);//getClass().getResource("/ViewFxml/addUpdate.fxml"));
            root = myLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 600, 480);
        //scene.getStylesheets().add(getClass().getResource("/TravelApp.css").toExternalForm());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    //add implementation of uc add organization
    public void addOrg() {

    }
}
