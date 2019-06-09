package View;

import Controller.Controller;
import Model.Model;



public class AView {

    Controller theController;

    public AView(){
        theController = new Controller();
        Model model=new Model();
        theController.setModel(model);
    }
}
