package Controller;
import Model.sqlConnection;
import javafx.application.Application;
import javafx.stage.Stage;
import Controller.Controller;
import Model.Model;
import View.View;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Model model=new Model();
        Controller controller=new Controller(model);
        String [] category=new String[2];
        category[0]="criminal";
        category[1]="nationalistic";
        String [] organization=new String[2];
        organization[0]="POLICE";
        organization[1]="MAGEN DAVID ADOM";
        String [] manIn=new String[2];
        manIn[0]="Racheli Sagron";
        manIn[1]="Omer Domb";
//        System.out.println(controller.creatEvent("Bank robbery",category,organization,manIn));
        System.out.println(controller.addUpdate(1,5,"Started tracking"));
//        ArrayList<String> work=new ArrayList<>();
//        work=controller.getAllWorkersInOrganization("POLICE");
//        for(int i=0;i<work.size();i++)
//            System.out.println(work.get(i));

    }

    public static void main(String[] args) {
        launch(args);
    }
}
