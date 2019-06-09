package Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Model.Model;
import View.View;
import View.addUpdateView;
import View.createEventView;

public class EmerAgency extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
//        Model model=new Model();
//        View view=new View();
//        Controller controller=new Controller(model,view);
//        String [] category=new String[2];
//        category[0]="criminal";
//        category[1]="nationalistic";
//        String [] organization=new String[2];
//        organization[0]="POLICE";
//        organization[1]="MAGEN DAVID ADOM";
//        int [] manIn=new int[2];
//        manIn[0]=1111;
//        manIn[1]=2222;
//        //System.out.println(controller.creatEvent("Bank robbery",category,organization,manIn));
//        System.out.println(controller.addUpdate(1111,0,"event start"));
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(EmerAgency.class.getResource("/View.fxml"));
        Model model=new Model();
        View view=new View();//fxmlLoader.getController();
        Controller controller=new Controller(model,view);
        view.setController(controller);
        Scene scene = new Scene(root, 600, 480);
        //scene.getStylesheets().add(getClass().getResource("/TravelApp.css").toExternalForm());
        primaryStage.setTitle("Emer-Agency");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
