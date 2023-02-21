package com.company.Memberships;

import com.company.SQL.BookingSQL;

import java.sql.*;
import java.util.Date;
import java.util.Scanner;

public class Print {
    public static void PrintBookings(String Facility) {     //prints all bookings of a user
        boolean found = false;
        Scanner input = new Scanner(System.in);
        System.out.println("----------------");
        Users CurrentUser = SearchUser.search(false);           //Creates a user object storing all of the details
        Accounts AccountHolder = BookingSQL.AccountFind(CurrentUser);   //Creates an accounts object extending the user object in order to obtain accountID and requirements
        printBookings(found, AccountHolder, "Gym");
        printBookings(found, AccountHolder, "Swim");            //Finds the bookings separately for, gym, swim, and courts.
        printBookings(found, AccountHolder, "Court");
    }

    private static void printBookings(boolean found, Accounts AccountHolder, String facility) {
        System.out.println(facility + ":  ");
        //gets the directory for this project and appends the name of the database file to it and sets it as the database location.
        String DatabaseLocation = System.getProperty("user.dir") + "\\ProjectDatabase.accdb";
        try {
            //sets the connection to the database using the database location
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            //forms a statement which is used to format the results
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //Searches the facility table and the account table at the same time to mathc them.
            String sql = "SELECT * " +
                    "FROM " + facility + ", Accounts " +
                    "WHERE " + facility + ".Account = " + AccountHolder.getAccountID() + " AND Accounts.AccountID = " + facility + ".Account";
            //Executes the SQL and returns the results as a ResultSet
            ResultSet RS = stmt.executeQuery(sql);
            long millis = System.currentTimeMillis();
            Date currentDate = new Date(millis);
            //Loops through all the records
            while (RS.next()) {
                Date tempdate = RS.getDate(2);
                if(tempdate.after(currentDate)) {
                    //outputs the bookings in a readable format
                    StringConcat(tempdate.toString(), RS.getString(3), RS.getString(4), RS.getBoolean("Accounts.SpecialRequirements"));
                    System.out.println();
                    found = true;
                }
            }
            if(!found){
                System.out.println("----------------\nYou currently have no " + facility + " bookings!\n----------------");
            }
            con.close();
            //close the database

        } catch (Exception e) {
            System.out.println(e);
        }
    }
//concatenates the string into an outputtable version
    public static void StringConcat(String Date, String Entry, String Exit, boolean Requirements) {
        System.out.println("Date of booking:  " + Date + ",  Time of Entry:  " + Entry + ",  Time of Exit:  " + Exit + ", Special requirements = " + Requirements);
    }
}
