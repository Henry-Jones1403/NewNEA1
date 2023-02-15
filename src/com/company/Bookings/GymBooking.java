package com.company.Bookings;

import com.company.Memberships.Accounts;
import com.company.Memberships.Management;
import com.company.Memberships.Users;
import com.company.SQL.Additions;
import com.company.SQL.BookingSQL;
import com.company.SQL.SQLSearches;
import org.hsqldb.rights.User;

import java.sql.Time;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class GymBooking {
    public static void BookSlot(){
        Users BookingUser = Management.search(false);
        System.out.println(BookingUser);
        Accounts BookingAccount = BookingSQL.AccountFind(BookingUser);
        System.out.println("----------------\nWelcome to the gym booking service!");
        Scanner input = new Scanner(System.in);
        LocalDate Selected = LocalDate.now();
        LocalTime SelectedTime = LocalTime.of(0,0);
        LocalTime now = LocalTime.of(13, 0, 0);
        System.out.println("Please choose on of the following dates by typing it out:");
        for (int i = 1; i < 7; i++) {
            System.out.println(LocalDate.now().plusDays(i));

        }
        Selected = FormattCheck.getLocalDate(input, Selected);
        System.out.println();
        System.out.println("----------------\nPlease type what time you would like to be chosen.");
        for (int i = 8; i < 21; i++) {
            System.out.println(LocalTime.of(i, 0));
        }
        SelectedTime = FormattCheck.getLocalTime(input, SelectedTime);
        ArrayList<Booking> currentBookings = BookingSQL.Bookings("SELECT * FROM GYM WHERE (BookingDate = '" + Selected + "') AND (EntryTime = '13:00')");
        if(currentBookings.size()<5){
            System.out.println("INSERT INTO GYM(BookingDate, EntryTime, ExitTime, Account) VALUES('" + Selected + "', '" + SelectedTime.toString() + "', '" +
                    SelectedTime.plusHours(1).toString() + "', '" + BookingAccount.getAccountID() + "')");
            Additions.add("INSERT INTO GYM(BookingDate, EntryTime, ExitTime, Account) VALUES('" + Selected + "', '" + SelectedTime.toString() + "', '" +
                    SelectedTime.plusHours(1).toString() + "', '" + BookingAccount.getAccountID() + "' )");
        }
}


}