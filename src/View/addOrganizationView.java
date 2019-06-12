package View;

import Controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.util.ArrayList;

public class addOrganizationView extends AView{
    ObservableList<String> allOrganizations;
    public javafx.scene.control.TextField invitedID;
    //user creator
    public javafx.scene.control.TextField inviterID;
    //event id for update
    public javafx.scene.control.TextField eventID;
    public javafx.scene.control.ChoiceBox<String> allOrg;
    //all users of Org

    public void setController(Controller theController) {
        theController = theController;
    }


    public addOrganizationView() {


    }

    @FXML
    public void initialize() {
        allOrganizations = FXCollections.observableArrayList("POLICE", "MAGEN DAVID ADOM", "FIRE AND RESCUE");
        allOrg.setItems(allOrganizations);
        allOrg.setValue("POLICE");
        allOrg.setVisible(true);
//        usersOrg.setDisable(true);
    }

    public void sendInvitation() {
        int fromUser = Integer.parseInt(inviterID.getText());
        int toUser = Integer.parseInt(invitedID.getText());
        int eventToUpdate = Integer.parseInt(eventID.getText());
        String invitedOrg = allOrg.getValue();
//        String invitedOrg = allOrg.getItems();
        String result = theController.addOrganization(fromUser,eventToUpdate,toUser, invitedOrg);
        //show alert ans

    }
//    public void getAllOrgs() {
//        allOrg.setDisable(false);
//        ArrayList<String> allUsersPerOrg = theController.getAllWorkersInOrganization(allOrg.getValue());
//        allOrg.setItems(FXCollections.observableArrayList(allUsersPerOrg));
//    }
}

