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
        Connection conn = sqlConnection.Connector();
        String query = "insert into users (id,fullName,organization,rank) values (1,'test test','fire department',4)";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.execute();
        conn.close();
    }

    public void creat(){
        Model model=new Model();
        View view=new View();
        Controller controller=new Controller(model,view);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
