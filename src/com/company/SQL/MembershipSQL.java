package com.company.SQL;

import com.company.Memberships.Users;

import java.sql.*;
import java.util.ArrayList;

public class MembershipSQL {
    public static int MembershipFind(Boolean Gym, Boolean swim, Boolean court, Boolean premium) {         //finds the membership id of a specific membership choice
        boolean register = true;
        ArrayList<Users> output = new ArrayList<>();
        //gets the directory for this project and appends the name of the database file to it and sets it as the database location.
        String DatabaseLocation = System.getProperty("user.dir") + "\\ProjectDatabase.accdb";
        try {
            //sets the connection to the database using the database location
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            //forms a statement which is used to format the results
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //Executes the SQL and returns the results as a ResultSet
            ResultSet Rs = stmt.executeQuery("SELECT * FROM Memberships WHERE (Gym = '" + Gym.toString() + "') AND (Swim = '" + swim.toString() +
                    "') AND (Court = '" + court.toString() + "') AND (Premium = '" + premium.toString() + "')");
            ResultSetMetaData rsmd = Rs.getMetaData();
            //Loops through all the records
            while (Rs.next()){
                int MembID = Rs.getInt(1);
                con.close();
                return MembID;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return 16;

    }public static boolean checkMember(int Account, String Facility){               //Checks a user has the correct membership details to use systems
        //gets the directory for this project and appends the name of the database file to it and sets it as the database location.
        String DatabaseLocation = System.getProperty("user.dir") + "\\ProjectDatabase.accdb";
        try {
            //sets the connection to the database using the database location
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            //forms a statement which is used to format the results
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //Executes the SQL and returns the results as a ResultSet
            ResultSet Rs = stmt.executeQuery("SELECT " + Facility+ " FROM Memberships WHERE MembershipID = " + Account + "");
            ResultSetMetaData rsmd = Rs.getMetaData();
            //Loops through all the records
            while (Rs.next()){
                boolean access = Rs.getBoolean(Facility);
                con.close();
                return access;
            }

        } catch (Exception e) {
            System.out.println(e);
        }return false;
    }
}
