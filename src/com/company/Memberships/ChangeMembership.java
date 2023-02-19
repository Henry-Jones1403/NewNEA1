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

            Users UserChange = Management.search(false);
            Accounts AccountChange = BookingSQL.AccountFind(UserChange);
            Scanner input = new Scanner(System.in);
            System.out.println("----------------\nAre you removing a membership?");
            if (input.next().equalsIgnoreCase("yes")) {
                ChangeSQL(16, AccountChange.getAccountID());
            } else {
                Membership newValues = ProduceMemb.options();
                ChangeSQL(newValues.getMembershipID(), AccountChange.getAccountID());
            }
        } catch (Exception e) {
            System.out.println(e);
        } return sql;

    }
    public static void ChangeSQL(Integer newMemb, Integer Account){
        String DatabaseLocation = System.getProperty("user.dir") + "\\ProjectDatabase.accdb";
        try {
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            PreparedStatement stmt = con.prepareStatement( "UPDATE Accounts SET MembershipID = ? WHERE AccountID = ?");
            stmt.setString(1,newMemb.toString());
            stmt.setString(2, Account.toString());
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    }

