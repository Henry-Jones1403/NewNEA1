package com.company.SQL;

import com.company.Memberships.Users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class Additions {
    public static void add(String First, String Last, String EmailAd, String PhoneNum, String Postc, String HouseName, String StreetAd) {
        String DatabaseLocation = System.getProperty("user.dir") + "\\ProjectDatabase.accdb";
        try {
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            Statement stmt = con.createStatement();
            stmt.execute("INSERT INTO Users(FirstName, LastName, Email, Phone, Postcode, HouseNumber, Street) VALUES(' " + First + "', '" + Last +
                    "', '" + EmailAd + "', '" + PhoneNum + "', '" + Postc + "', '" + HouseName + "', '" + StreetAd + "')");
            con.close();
        } catch (Exception e) {

            System.out.println(e);
        }
    }

    public static void addAccount(boolean admin, String FirstName_, String LastName_, String PhoneNumber) {
        String DatabaseLocation = System.getProperty("user.dir") + "\\ProjectDatabase.accdb";
        ArrayList<Users> repeated_names = new ArrayList<>();
        Integer UserID = 0;
        try {
            repeated_names = SQL.Search("SELECT * FROM USERS WHERE LastName = '" + LastName_ + "'");
            for (int i = 0; i < repeated_names.size(); i++) {
                if (repeated_names.get(i).getPhone().equals(PhoneNumber)) {
                    UserID = repeated_names.get(i).getUserID();
                }
            }
            String username = FirstName_ + "_" + LastName_ + Integer.toString(repeated_names.size());
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            Statement stmt = con.createStatement();
            stmt.execute("INSERT INTO Memberships(Gym, Swim, Court, Premium) VALUES('true', 'true', 'true', 'true')");

            stmt.execute("INSERT INTO Accounts(Username, Password, Admin, UserID, MembershipID ) VALUES('" + username + "', 'password', '" + admin + "', '" + UserID + "', '" + SQL.MembershipFind().getMembershipID() + "')");
            con.close();
        } catch (Exception e) {

            System.out.println(e);
        }
    }
}
