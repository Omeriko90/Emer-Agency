package Model;

import java.sql.Connection;
import java.sql.DriverManager;


public class sqlConnection {

    //The connection to the DB
    public static Connection Connector(){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:EmerAgency.db");
            return conn;
        }catch (Exception e){
            return null;
        }
    }
}