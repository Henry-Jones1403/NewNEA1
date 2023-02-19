package com.company.SQL;

import com.company.Memberships.Users;

import java.sql.*;
import java.util.ArrayList;

public class MembershipSQL {
    public static int MembershipFind(String Gym, String swim, String court, String premium) {
        boolean register = true;
        ArrayList<Users> output = new ArrayList<>();
        String DatabaseLocation = System.getProperty("user.dir") + "\\ProjectDatabase.accdb";
        try {
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet Rs = stmt.executeQuery("SELECT * FROM Memberships WHERE (Gym = '" + Gym.toString() + "') AND (Swim = '" + swim.toString() +
                    "') AND (Court = '" + court.toString() + "') AND (Premium = '" + premium.toString() + "')");
            ResultSetMetaData rsmd = Rs.getMetaData();
            while (Rs.next()){
                int MembID = Rs.getInt(1);
                con.close();
                return MembID;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return 16;

    }public static boolean checkMember(int Account, String Facility){
        String DatabaseLocation = System.getProperty("user.dir") + "\\ProjectDatabase.accdb";
        try {
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet Rs = stmt.executeQuery("SELECT " + Facility+ " FROM Memberships WHERE MembershipID = " + Account + "");
            ResultSetMetaData rsmd = Rs.getMetaData();
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
