package View;

import Controller.Controller;
import Model.Model;
import javafx.scene.control.Alert;


public class AView {

    Controller theController;

    public AView(){
        theController = new Controller();
        Model model=new Model();
        theController.setModel(model);
    }
    public void showAlert(String property) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(property);
        alert.showAndWait();
    }
}
