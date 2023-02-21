package com.company.Memberships;

import com.company.SQL.BookingSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class ChangeMembership {

    public static String ChangeMembership() {
        String sql = "";
        try {

            Users UserChange = SearchUser.search(false);
            Accounts AccountChange = BookingSQL.AccountFind(UserChange);
            Scanner input = new Scanner(System.in);
            System.out.println("----------------\nAre you removing a membership?");
            if (input.next().equalsIgnoreCase("yes")) {
                ChangeSQL(16, AccountChange.getAccountID());        //changes the membership value to 16 which is no options selected.
            } else {
                Membership newValues = ProduceMemb.options();
                ChangeSQL(newValues.getMembershipID(), AccountChange.getAccountID()); //gets the new membership id and changes the membershipid of the current account
            }
        } catch (Exception e) {
            System.out.println(e);
        } return sql;

    }
    public static void ChangeSQL(Integer newMemb, Integer Account){
        //gets the directory for this project and appends the name of the database file to it and sets it as the database location.
        String DatabaseLocation = System.getProperty("user.dir") + "\\ProjectDatabase.accdb";
        try {
            //sets the connection to the database using the database location
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            //creates a prepared statement
            PreparedStatement stmt = con.prepareStatement( "UPDATE Accounts SET MembershipID = ? WHERE AccountID = ?");
            stmt.setString(1,newMemb.toString());
            stmt.setString(2, Account.toString());          //inputs the correct values into the statement
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    }

