package Model;
import Controller.Controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class Model {
    private Controller controller;
    private Connection conn;
    static int eventId;

    public Model(){
        eventId=0;
    }

    public String creatEvent(String titleEvent, String[] category, String [] organization, int [] manInCharge){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);

        //add to event db
        String query = "insert into events (id,title,date,status) values(?,?,?,?)";
        try {
            conn = sqlConnection.Connector();
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1,eventId++);
            pst.setString(2,titleEvent);
            pst.setString(3,strDate);
            pst.setString(4,"open");
            pst.execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //add to category db
        for(int i=0;i<category.length;i++) {
            String query2 = "insert into eventCategory (eventID,category) values(?,?)";
            try {
                conn = sqlConnection.Connector();
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setInt(1, eventId);
                pst.setString(2, category[i]);
                pst.execute();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //add to organization db
        for(int i=0;i<organization.length;i++) {
            String query3 = "insert into organizationsEvent (eventID,organization,userID) values(?,?,?)";
            try {
                conn = sqlConnection.Connector();
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setInt(1, eventId);
                pst.setString(2, organization[i]);
                pst.setInt(1, manInCharge[i]);
                pst.execute();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "success";
    }

    private boolean checkIfUserExists(int userId){
        String query = "select count(*) as found from users where id='" + userId + "'";
        return foundQuery(query);
    }

    public String addUpdate(int userId, int currentEventId, String Update){
        //check if user exists
        boolean userFound=checkIfUserExists(userId);
        if(!userFound)
            return "user not found";
        //check if event exists
        boolean eventFound=checkIfEventExists(currentEventId);
        if(!eventFound)
            return "event not found";
        //check if user as Write permission
        boolean userAsPermission=checkIfUserAsPermission(userId,currentEventId);
        if(!userAsPermission)
            return "user dont have write permission";
        //insert update event to the db
        String query = "insert into eventsUpdate (eventID,update,date,userID) values(?,?,?,?)";
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        try {
            conn = sqlConnection.Connector();
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1,currentEventId);
            pst.setString(2,Update);
            pst.setString(3,strDate);
            pst.setInt(4,userId);
            pst.execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "success";
    }

    private boolean checkIfEventExists(int currentEventId) {
        String query = "select count(*) as found from events where id='" + currentEventId + "'";
        return foundQuery(query);
    }

    private boolean checkIfUserAsPermission(int userId, int currentEventId) {
        String queryForUser = "select rank from users where id='" + userId + "'";
        ResultSet userRankFound=resultSetQuery(queryForUser);
        String queryForUserCharge= "select userID from organizationsEvent where eventID='" + currentEventId + "'";
        ResultSet userCharge=resultSetQuery(queryForUserCharge);
        int idUserCharge=0;
        try {
            idUserCharge=userCharge.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String queryForChargeUser="select rank from users where id='" + idUserCharge + "'";
        if(idUserCharge!=0) {
            ResultSet userChargeRankFound = resultSetQuery(queryForChargeUser);
            try {
                return (userRankFound.getInt(1)>=userChargeRankFound.getInt(1));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private ResultSet resultSetQuery(String query){
        ResultSet nrs=null;
        try {
            conn = sqlConnection.Connector();
            PreparedStatement pst = conn.prepareStatement(query);
            try (ResultSet rs = pst.executeQuery()) {
                nrs = pst.executeQuery(query);
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nrs;
    }

    private boolean foundQuery(String query){
        try {
            conn = sqlConnection.Connector();
            PreparedStatement pst = conn.prepareStatement(query);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    boolean found = rs.getBoolean(1);
                    if (found)
                        return true;
                }
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getEventId() {
        return eventId;
    }

    public ArrayList<String> getAllWorkersInOrganization(String organization) {
        ArrayList<String>worker=new ArrayList<>();
        String query = "select id from users where organization='" + organization + "'";
        try(ResultSet userRankFound=resultSetQuery(query)) {
            while (userRankFound.next()) {
                worker.add(userRankFound.getString(1));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return  worker;
    }

}
