package com.company.SQL;

import com.company.Bookings.Booking;
import com.company.Memberships.Accounts;
import com.company.Memberships.Users;

import java.time.LocalDate;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class BookingSQL {
    public static ArrayList<Booking> Bookings(String sql) {     //Finds the bookings
        ArrayList<Booking> CurrentBookings = new ArrayList<Booking>();

        //gets the directory for this project and appends the name of the database file to it and sets it as the database location.
        String DatabaseLocation = System.getProperty("user.dir") + "\\ProjectDatabase.accdb";
        try {
            //sets the connection to the database using the database location
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            //forms a statement which is used to format the results
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //Executes the SQL and returns the results as a ResultSet
            ResultSet Rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = Rs.getMetaData();
            //Loops through all the records
            while (Rs.next()) {
                Date dateformat = Rs.getDate(2);
                Booking newBooking = new Booking(Rs.getInt("GymBookingID"), Rs.getDate("BookingDate").toLocalDate(),
                        LocalTime.parse(Rs.getString("EntryTime")), LocalTime.parse(Rs.getString("ExitTime")), Rs.getInt("Account"));
                CurrentBookings.add(newBooking);
            }
            //close the database
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return CurrentBookings;
    }

    public static Accounts AccountFind(Users BookingUser) {         //find the account and create an object
        ArrayList<Booking> CurrentBookings = new ArrayList<Booking>();
        //gets the directory for this project and appends the name of the database file to it and sets it as the database location.
        String DatabaseLocation = System.getProperty("user.dir") + "\\ProjectDatabase.accdb";
        try {
            //sets the connection to the database using the database location
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            //forms a statement which is used to format the results
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //Executes the SQL and returns the results as a ResultSet
            ResultSet Rs = stmt.executeQuery("SELECT * FROM Accounts WHERE UserID = '" + BookingUser.getUserID() + "'");
            ResultSetMetaData rsmd = Rs.getMetaData();
            //Loops through all the records
            while (Rs.next()) {
                Accounts BookingAccount = new Accounts(BookingUser.getUserID(), BookingUser.getFirstName(), BookingUser.getLastName(), BookingUser.getEmail(),
                        BookingUser.getPhone(), BookingUser.getPostcode(), BookingUser.getHouseNumber(),
                        BookingUser.getStreet(), Rs.getInt(1), Rs.getInt(6), Rs.getString(2), Rs.getBoolean(7));
                con.close();
                return BookingAccount;
            }
            //closes the database
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static void Book(LocalDate date, LocalTime Entry, LocalTime Exit, int account, String table, boolean Requirements) {
        //gets the directory for this project and appends the name of the database file to it and sets it as the database location.
        String DatabaseLocation = System.getProperty("user.dir") + "\\ProjectDatabase.accdb";
        try {
            //sets the connection to the database using the database location
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            //forms a prepared statement
            PreparedStatement stmt = con.prepareStatement("INSERT INTO " + table + "(BookingDate, EntryTime, ExitTime, Account, Requirements) Values(?, ?, ?, ?, ?)");
            //input the correct values into the SQL query ready for execution
            stmt.setString(1, date.toString());
            stmt.setString(2, Entry.toString());
            stmt.setString(3, Exit.toString());
            stmt.setInt(4, account);
            stmt.setBoolean(5, Requirements);
            //Executes the SQL
            stmt.executeUpdate();
            //closes the database
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
