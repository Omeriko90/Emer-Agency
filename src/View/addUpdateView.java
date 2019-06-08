package View;

import Controller.Controller;

public class addUpdateView {

    Controller controller;

    //update description
    public javafx.scene.control.TextField updateDescription;
    //user creator
    public javafx.scene.control.TextField userID;
    //event id for update
    public javafx.scene.control.TextField eventID;

    public addUpdateView(Controller theController){
        controller=theController;
    }

    //sending the update ditails to the controller
    public void submitUpdate(){
        int userCreator = Integer.parseInt(userID.getText());
        int eventToUpdate = Integer.parseInt(eventID.getText());
        String updateDesc = updateDescription.getText();
    }
}
