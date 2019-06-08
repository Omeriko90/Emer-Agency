package Controller;
import Model.sqlConnection;
import javafx.application.Application;
import javafx.stage.Stage;
import Controller.Controller;
import Model.Model;
import View.View;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Model model=new Model();
        View view=new View();
        Controller controller=new Controller(model,view);
        String [] category=new String[2];
        category[0]="criminal";
        category[1]="nationalistic";
        String [] organization=new String[2];
        organization[0]="POLICE";
        organization[1]="MAGEN DAVID ADOM";
        int [] manIn=new int[2];
        manIn[0]=1111;
        manIn[1]=2222;
        //System.out.println(controller.creatEvent("Bank robbery",category,organization,manIn));
        System.out.println(controller.addUpdate(1111,0,"event start"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
