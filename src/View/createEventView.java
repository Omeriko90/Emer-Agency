package View;

import Controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.util.ArrayList;

public class createEventView {

    ArrayList<Pair<String,String>> organizations = new ArrayList<>();
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
        String[] finalOrg = getfinalOrfanizations();
        String[] finalUsers = getfinalUsers();

        //send the details to the controller create event function
        controller.creatEvent(eventName.getText(),,finalOrg,finalUsers);

        //after submit button deletes orgs array
        organizations = new ArrayList<>();
    }
    //insert all organization into string array
    private String[] getfinalOrfanizations() {
        String[] allOrg = new String[organizations.size()];
        for(int i=0;i<organizations.size();i++){
            String org = organizations.get(i).getKey();
            allOrg[i] = org;
        }
        return allOrg;
    }
    //return all final users into string array
    private String[] getfinalUsers() {
        String[] allusers = new String[organizations.size()];
        for(int i=0;i<organizations.size();i++){
            String user = organizations.get(i).getValue();
            allusers[i] = user;
        }
        return allusers;
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
