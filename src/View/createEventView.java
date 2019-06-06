package View;

import Controller.Controller;
import javafx.util.Pair;

import java.util.ArrayList;

public class createEventView {

    ArrayList<Pair> organizations = new ArrayList<>();

    Controller controller;
    //new event name
    public javafx.scene.control.TextField eventName;
    //categories display
    public javafx.scene.control.CheckBox categoriesList;
    //organization choice box
    public javafx.scene.control.ChoiceBox allOrg;
    //all users of Org
    public javafx.scene.control.ChoiceBox usersOrg;
    //add another organization
    public javafx.scene.control.Button btn_addOrg;

    public createEventView(Controller theController) {
        controller = theController;
    }

    //action for submit event button
    public void submitEvent() {
        //send the details to the controller create event function

        //after submit button deletes orgs array
        organizations = new ArrayList<>();
    }

    //case of more then one organization for event
    public void addAnotherOrg() {
    Pair newOrg = new Pair(allOrg.getValue(),usersOrg.getValue());
    organizations.add(newOrg);
    }
}
