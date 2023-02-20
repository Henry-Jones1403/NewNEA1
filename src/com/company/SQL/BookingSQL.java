package com.company.SQL;

import com.company.Bookings.Booking;
import com.company.Memberships.Accounts;
import com.company.Memberships.Users;

import java.time.LocalDate;
import java.sql.*;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BookingSQL {
    public static ArrayList<Booking> Bookings(String sql) {
        ArrayList<Booking> CurrentBookings = new ArrayList<Booking>();
        String DatabaseLocation = System.getProperty("user.dir") + "\\ProjectDatabase.accdb";
        try {
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet Rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = Rs.getMetaData();
            while (Rs.next()) {
                Date dateformat = Rs.getDate(2);
                Booking newBooking = new Booking(Rs.getInt("GymBookingID"), Rs.getDate("BookingDate").toLocalDate(),
                        LocalTime.parse(Rs.getString("EntryTime")), LocalTime.parse(Rs.getString("ExitTime")), Rs.getInt("Account"));
                CurrentBookings.add(newBooking);
            }
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return CurrentBookings;
    }

    public static Accounts AccountFind(Users BookingUser) {
        ArrayList<Booking> CurrentBookings = new ArrayList<Booking>();
        String DatabaseLocation = System.getProperty("user.dir") + "\\ProjectDatabase.accdb";
        try {
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet Rs = stmt.executeQuery("SELECT * FROM Accounts WHERE UserID = '" + BookingUser.getUserID() + "'");
            ResultSetMetaData rsmd = Rs.getMetaData();
            while (Rs.next()) {
                Accounts BookingAccount = new Accounts(BookingUser.getUserID(), BookingUser.getFirstName(), BookingUser.getLastName(), BookingUser.getEmail(),
                        BookingUser.getPhone(), BookingUser.getPostcode(), BookingUser.getHouseNumber(),
                        BookingUser.getStreet(), Rs.getInt(1), Rs.getInt(6), Rs.getString(2), Rs.getBoolean(7));
                con.close();
                return BookingAccount;
            }con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static void Book(LocalDate date, LocalTime Entry, LocalTime Exit, int account, String table, boolean Requirements) {
        String DatabaseLocation = System.getProperty("user.dir") + "\\ProjectDatabase.accdb";
        try {
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            PreparedStatement stmt = con.prepareStatement("INSERT INTO " + table + "(BookingDate, EntryTime, ExitTime, Account, Requirements) Values(?, ?, ?, ?, ?)");
            stmt.setString(1,date.toString());
            stmt.setString(2, Entry.toString());
            stmt.setString(3, Exit.toString());
            stmt.setInt(4, account);
            stmt.setBoolean(5, Requirements);

            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
