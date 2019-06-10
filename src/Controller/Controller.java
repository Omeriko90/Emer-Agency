package Controller;
import Model.Model;
import View.View;

import java.util.ArrayList;

public class Controller {
    private Model model;

    public Controller(Model mainModel){
        model=mainModel;
    }

    public String creatEvent(String titleEvent, String[] category, String[] organization, String[] manInCharge){
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
