package sample;

import Model.sqlConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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


    public static void main(String[] args) {
        launch(args);
    }
}
