package View;

import Controller.Controller;

public class addUpdateView extends AView {


    //update description
    public javafx.scene.control.TextField updateDescription;
    //user creator
    public javafx.scene.control.TextField userID;
    //event id for update
    public javafx.scene.control.TextField eventID;

    public void setController(Controller theController) {
        theController = theController;
    }

    public addUpdateView() {

    }

    //sending the update ditails to the controller
    public void submitUpdate() {
        int userCreator = Integer.parseInt(userID.getText());
        int eventToUpdate = Integer.parseInt(eventID.getText());
        String updateDesc = updateDescription.getText();
        //sending to the controller, model will check that user and event exist and user have the needed access
        String result = theController.addUpdate(userCreator,eventToUpdate,updateDesc);
        //show alert ans

    }
}
