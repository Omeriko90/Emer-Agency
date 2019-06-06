package Controller;
import Model.Model;
import View.View;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model mainModel,View mainView){
        model=mainModel;
        view=mainView;

    }
}
