package Controller;
import Model.Model;
import View.View;

import java.util.ArrayList;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model mainModel,View modelView){
        model=mainModel; view=modelView;
    }

    public Controller(){

    }

    public void setModel(Model theModel){
        model=theModel;
    }

    public void setView(View theView){
        view=theView;

    }
    public String creatEvent(String titleEvent, String[] category, String[] organization, String[] usersSelected){
        int[] manInCharge = new int[usersSelected.length];
        //just casting the users from string to int expected by the model
        for(int i = 0 ; i<manInCharge.length;i++){
            manInCharge[i] = Integer.parseInt(usersSelected[i]);
        }
        return model.creatEvent(titleEvent,category,organization,manInCharge);
    }

    public String addUpdate(int userId, int currentEventId, String Update){
        return model.addUpdate(userId,currentEventId,Update);
    }

    public int getEventId(){
        return model.getEventId();
    }

    public ArrayList<String> getAllWorkersInOrganization(String organization){
        return model.getAllWorkersInOrganization(organization);
    }

}
