package com.company.Memberships;

import com.company.SQL.BookingSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

public class Print {
    public static void PrintBookings(String Facility) {
        boolean found = false;
        Scanner input = new Scanner(System.in);
        System.out.println("----------------");
        Users CurrentUser = Management.search(false);
        Accounts AccountHolder = BookingSQL.AccountFind(CurrentUser);
        extracted(found, AccountHolder, "Gym");
        extracted(found, AccountHolder, "Swim");
        extracted(found, AccountHolder, "Swim");
    }

    private static void extracted(boolean found, Accounts AccountHolder, String facility) {
        System.out.println(facility + ":  ");
        String DatabaseLocation = System.getProperty("user.dir") + "\\ProjectDatabase.accdb";
        try {
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM Gym WHERE Account = " + AccountHolder.getAccountID() + "";
            ResultSet RS = stmt.executeQuery(sql);
            long millis = System.currentTimeMillis();
            Date currentDate = new Date(millis);
            while (RS.next()) {
                Date tempdate = RS.getDate(2);
                if(tempdate.after(currentDate)) {
                    StringConcat(tempdate.toString(), RS.getString(3), RS.getString(4));
                    System.out.println();
                    found = true;
                }
            }
            if(!found){
                System.out.println("----------------\nYou currently have no " + facility + " bookings!\n----------------");
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void StringConcat(String Date, String Entry, String Exit) {
        System.out.println("Date of booking:  " + Date + ",  Time of Entry:  " + Entry + ",  Time of Exit:  " + Exit);
    }
}
