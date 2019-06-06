package View;

import Controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.util.ArrayList;

public class createEventView {

    ArrayList<Pair> organizations = new ArrayList<>();
    ObservableList<String> allOrganizations;

    Controller controller;
    //new event name
    public javafx.scene.control.TextField eventName;
    //categories display
    public javafx.scene.control.CheckBox categoriesList;
    //organization choice box
    public javafx.scene.control.ChoiceBox <String> allOrg;
    //all users of Org
    public javafx.scene.control.ChoiceBox<String> usersOrg;
    //add another organization
    public javafx.scene.control.Button btn_addOrg;

    public createEventView(Controller theController) {
        controller = theController;
        allOrganizations = FXCollections.observableArrayList("POLICE", "MAGEN DAVID ADOM", "FIRE AND RESCUE");
        allOrg.setItems(allOrganizations);
        usersOrg.setDisable(true);
    }

    //action for submit event button
    public void submitEvent() {
        String[] finalOrg = 
        //send the details to the controller create event function
        controller.creatEvent(eventName.getText(),)

        //after submit button deletes orgs array
        organizations = new ArrayList<>();
    }

    //case of more then one organization for event
    public void addAnotherOrg() {
        Pair newOrg = new Pair(allOrg.getValue(), usersOrg.getValue());
        //case when try to add the same org twice
        if (organizations.contains(newOrg)) {
            //need to show alert

        } else {
            organizations.add(newOrg);
        }
    }
    //gets all users of the wanted org
    public void getUsersOfOrg(){
        usersOrg.setDisable(false);
        ArrayList <String> allUsersPerOrg=controller.getAllWorkersInOrganization(allOrg.getValue());
        usersOrg.setItems(FXCollections.observableArrayList(allUsersPerOrg));
    }
}
