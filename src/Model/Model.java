package Model;
import Controller.Controller;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class Model {
    private Controller controller;
    private Connection conn;
    static int eventId=0;

    public Model(){
        String query = "select max(id) from events";
        try {
            Statement pst = conn.createStatement();
            try (ResultSet rs = pst.executeQuery(query)) {
                ResultSet nrs = pst.executeQuery(query);
                eventId=nrs.getInt(1);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateEventId(){
        Model.eventId++;
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
            UpdateEventId();
            pst.setInt(1,eventId);
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
                PreparedStatement pst = conn.prepareStatement(query2);
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
                PreparedStatement pst = conn.prepareStatement(query3);
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
        String query = "select count(*) as found from users where id=" + userId + "";
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
        String query = "select count(*) as found from events where id=" + currentEventId + "";
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

    private boolean checkIfUserAsPermission(int userId, int currentEventId) {
        String queryForUser = "select rank from users where id=" + userId + "";
        int userRankFoundAns=0;
        try {
            Statement pst = conn.createStatement();
            try (ResultSet rs = pst.executeQuery(queryForUser)) {
                ResultSet nrs = pst.executeQuery(queryForUser);
                userRankFoundAns=nrs.getInt(1);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        String queryForUserCharge= "select userID from organizationsEvent where eventID=" + currentEventId + "";
        int idUserCharge=0;
        try {
            Statement pst = conn.createStatement();
            try (ResultSet rs = pst.executeQuery(queryForUserCharge)) {
                ResultSet nrs = pst.executeQuery(queryForUserCharge);
                idUserCharge=nrs.getInt(1);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        String queryForChargeUser="select rank from users where id=" + idUserCharge + "";
        int chargeRank=0;
        if(idUserCharge!=0) {
            try {
                Statement pst = conn.createStatement();
                try (ResultSet rs = pst.executeQuery(queryForChargeUser)) {
                    ResultSet nrs = pst.executeQuery(queryForChargeUser);
                    chargeRank=nrs.getInt(1);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return userRankFoundAns>=chargeRank;
    }

    private ResultSet resultSetQuery(String query){
        ResultSet nrs=null;
        try {
            conn = sqlConnection.Connector();
            PreparedStatement pst = conn.prepareStatement(query);
            try (ResultSet rs = pst.executeQuery(query)) {
                nrs = pst.executeQuery(query);
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nrs;
    }


    public int getEventId() {
        return eventId;
    }

    public ArrayList<String> getAllWorkersInOrganization(String organization) {
        ArrayList<String>worker=new ArrayList<>();
        String query = "select id from users where organization='" + organization + "'";
        try(ResultSet userRankFound=resultSetQuery(query)) {
            while (userRankFound.next()) {
                worker.add(String.valueOf(userRankFound.getInt(1)));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return  worker;
    }

}
