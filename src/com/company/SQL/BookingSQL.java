package com.company.SQL;

import com.company.Bookings.Booking;

import java.time.LocalDate;
import java.sql.*;

import java.sql.*;
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
            while(Rs.next()){
                Date dateformat= Rs.getDate(2);
                Booking newBooking = new Booking(Rs.getInt(1), Rs.getDate(2).toLocalDate(),
                        Rs.getTime(3).toLocalTime(), Rs.getTime(4).toLocalTime(), Rs.getInt(5));
                CurrentBookings.add(newBooking);
            }

        } catch (Exception e) {
            System.out.println(e);
        }return CurrentBookings;
    }
}
