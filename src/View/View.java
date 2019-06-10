package View;

import Controller.Controller;
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
        loadView("/createEvent.fxml");
    }

    //loads the update fxml file
    public void addUpdate() {
        loadView("/addUpdate.fxml");
    }

    //add implementation of uc add organization
    public void addOrg() {

    }
    private void loadView(String view){
        Parent root = null;
        try {
            URL location = getClass().getResource(view);
            FXMLLoader myLoader = new FXMLLoader();
            myLoader.setLocation(location);
            root = myLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 600, 480);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}
