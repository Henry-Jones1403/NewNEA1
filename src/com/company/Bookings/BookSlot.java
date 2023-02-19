package com.company.Bookings;

import com.company.Memberships.Accounts;
import com.company.Memberships.Management;
import com.company.Memberships.Users;
import com.company.SQL.Additions;
import com.company.SQL.BookingSQL;
import com.company.SQL.MembershipSQL;
import com.company.SQL.SQLSearches;
import org.hsqldb.rights.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class BookSlot {
    public static void BookSlot(String facility){
        boolean Completed = false;
        try{
            Users BookingUser = Management.search(false);
            Accounts BookingAccount = BookingSQL.AccountFind(BookingUser);
            System.out.println("----------------\nWelcome to the " + facility + " booking service!");
           if(MembershipSQL.checkMember(BookingAccount.getMembershipId(), facility)){
               Scanner input = new Scanner(System.in);
               while(!Completed){
                   LocalDate Selected = LocalDate.now();
                   LocalTime SelectedTime = LocalTime.of(0,0);
                   LocalTime now = LocalTime.of(13, 0, 0);
                   System.out.println("Please choose on of the following dates by typing it out:");
                   for (int i = 1; i < 7; i++) {
                       System.out.println(LocalDate.now().plusDays(i));

                   }
                   ArrayList<LocalTime> availableTimes = new ArrayList<>();
                   Selected = FormattCheck.getLocalDate(input, Selected);
                   System.out.println();
                   for (int i = 8; i < 21; i++) {
                       availableTimes.add(LocalTime.of(i, 0));
                   }
                   Completed = isCompleted(facility, Completed, BookingAccount, input, Selected, SelectedTime, availableTimes);
               }



           }else{
                   System.out.println("Sorry you do not have access to this booking system. Please upgrade membership.");

        }
        }catch (Exception e){
            System.out.println(e);

}


}

    private static boolean isCompleted(String facility, boolean Completed, Accounts BookingAccount, Scanner input, LocalDate Selected, LocalTime SelectedTime, ArrayList<LocalTime> availableTimes) {
        ArrayList<Booking> currentBookings = BookingSQL.Bookings("SELECT * FROM " + facility + " WHERE (BookingDate = '" + Selected + "') AND (Account = "
                + BookingAccount.getAccountID() + ")");
        if(currentBookings.size()<2){

            for (int i = 0; i < availableTimes.size(); i++) {
                currentBookings = BookingSQL.Bookings("SELECT * FROM " + facility + " WHERE (EntryTime = '" + availableTimes.get(i).toString() + "')");
                if (currentBookings.size()>4){
                    availableTimes.remove(i);
                    Completed = true;
                }
            }
            for (int i = 0; i < availableTimes.size(); i++) {
                System.out.println(availableTimes.get(i));
            }System.out.println("----------------\nPlease type what time you would like to be chosen.");
            SelectedTime = FormattCheck.getLocalTime(input, SelectedTime);
            currentBookings = BookingSQL.Bookings("SELECT * FROM " + facility + " WHERE (BookingDate = '" + Selected + "') AND (EntryTime = '"
                + SelectedTime.toString() + "')");
            if(currentBookings.size()<5){
                BookingSQL.Book(Selected, SelectedTime, SelectedTime.plusHours(1), BookingAccount.getAccountID(), facility);
                Completed = true;
                System.out.println("Your booking is complete. In your session there will be " + currentBookings.size() + " other people with you.");
            }else{
                System.out.println("Apologies, that booking slot is all booked up! Please try again using another date and time.");
            }
        }
        else{
            System.out.println("Sorry you have already used all of your bookings for today.");
    }
        return Completed;
    }
}