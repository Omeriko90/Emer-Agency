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
