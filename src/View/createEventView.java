package View;

import Controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.util.Pair;

import java.util.ArrayList;

public class createEventView extends AView{

    ArrayList<String> organizationsChoosen = new ArrayList<>();
    ArrayList<String> usersChoosen = new ArrayList<>();
    ArrayList<String> categoryChoosen = new ArrayList<>();
    ObservableList<String> allOrganizations;
    ObservableList<String> allCategiries;

    //new event name
    public javafx.scene.control.TextField eventName;
    //categories display
    public javafx.scene.control.ChoiceBox<String> categoriesList;
    //organization choice box
    public javafx.scene.control.ChoiceBox<String> allOrg;
    //all users of Org
    public javafx.scene.control.ChoiceBox<String> usersOrg;
    //add another organization
    public javafx.scene.control.Button btn_addOrg;
    //add another category
    public javafx.scene.control.Button btn_addCategory;


    @FXML
    public void initialize() {
        allOrganizations = FXCollections.observableArrayList("POLICE", "MAGEN DAVID ADOM", "FIRE AND RESCUE");
        allCategiries = FXCollections.observableArrayList("category1", "category2", "category3");
        allOrg.setItems(allOrganizations);
        categoriesList.setItems(allCategiries);
        categoriesList.setValue("category1");
        allOrg.setValue("POLICE");
        allOrg.setVisible(true);
        usersOrg.setDisable(true);
    }

    public createEventView() {
    }

    //action for submit event button
    public void submitEvent() {
        addAnotherOrg();
        String[] finalOrg = getfinalOrfanizations();
        String[] finalUsers = getfinalUsers();
        String[] finalCategories = getfinalCategories();

        //send the details to the controller create event function
        String result = theController.creatEvent(eventName.getText(),finalCategories,finalOrg,finalUsers);
        //show alert result

        //after submit button deletes orgs array
        organizationsChoosen = new ArrayList<>();
        usersChoosen = new ArrayList<>();
        categoryChoosen = new ArrayList<>();
    }

    //return string array with all selected categories
    private String[] getfinalCategories() {
        String[] allCategories = new String[categoryChoosen.size()];
        for (int i = 0; i < allCategories.length; i++) {
            String category = categoryChoosen.get(i);
            allCategories[i] = category;
        }
        return allCategories;
    }

    //insert all organization into string array
    private String[] getfinalOrfanizations() {
        String[] allOrg = new String[organizationsChoosen.size()];
        for (int i = 0; i < organizationsChoosen.size(); i++) {
            String org = organizationsChoosen.get(i);
            allOrg[i] = org;
        }
        return allOrg;
    }

    //return all final users into string array
    private String[] getfinalUsers() {
        String[] allusers = new String[usersChoosen.size()];
        for (int i = 0; i < usersChoosen.size(); i++) {
            String user = usersChoosen.get(i);
            allusers[i] = user;
        }
        return allusers;
    }

    //case of more then one organization for event
    public void addAnotherOrg() {
        String ChoosenOrg = allOrg.getValue();
        String choosenUser = usersOrg.getValue();
        //case when try to add the same org twice
        if(organizationsChoosen.contains(ChoosenOrg)){
            //alert "organization already added"
        } else {
            organizationsChoosen.add(ChoosenOrg);
            usersChoosen.add(choosenUser);
        }
    }

    //gets all users of the wanted org
    public void getUsersOfOrg() {
        usersOrg.setDisable(false);
        ArrayList<String> allUsersPerOrg = theController.getAllWorkersInOrganization(allOrg.getValue());
        usersOrg.setItems(FXCollections.observableArrayList(allUsersPerOrg));
    }

    public void addAnotherCategory(){
        if(categoryChoosen.contains(categoriesList.getValue())){
            //alert category already selected
        }
        else{
            categoryChoosen.add(categoriesList.getValue());
        }
    }
}
