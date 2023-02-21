package com.company.SQL;

import com.company.Bookings.Booking;
import com.company.Bookings.FormattCheck;
import com.company.Memberships.Accounts;
import com.company.Memberships.SearchUser;
import com.company.Memberships.Users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class DeleteBooking {
    public static void deleteChoice(){              //chooses what booking to delete
        Scanner input = new Scanner(System.in);
        //finds which facility of the leisure centre the booking will be deleted from
        System.out.println("----------------\nPlease choose which facility you would like to delete a booking from:\n- Gym\n- Swim\n- Court ");
        String Facility = input.next();
        if((Facility.equalsIgnoreCase("swim"))||(Facility.equalsIgnoreCase("gym"))||(Facility.equalsIgnoreCase("court"))){
            LocalDate Selected = LocalDate.now();
            LocalTime SelectedTime = LocalTime.of(0,0);
            LocalTime now = LocalTime.of(13, 0, 0);
            System.out.println("----------------\nPlease choose on of the following dates by typing it out:");
            for (int i = 1; i < 7; i++) {               //Outputs all the dates of the next 7 days
                System.out.println(LocalDate.now().plusDays(i));
            }
            Selected = FormattCheck.getLocalDate(input, Selected);      //Check to make sure the input is in the correct format so it can be read by SQL
            Users DeletingUser = SearchUser.search(false);
            Accounts DeletingAccount = BookingSQL.AccountFind(DeletingUser);        //Creates an account object with the users information
            Deletion(Selected, DeletingAccount.getAccountID(), Facility);       //Deletes the selected booking
        }else{
            System.out.println("Please input a correct facility using the specified format.");
        }

    }
    public static void Deletion(LocalDate deletedDate, Integer account, String facility){       //deletes booking
        String time;
        ArrayList<Booking> currentBookings = new ArrayList<>();
        //Finds all bookings which are on the selected date by the selected account and prints them out
        currentBookings = BookingSQL.Bookings("SELECT * FROM " + facility + " WHERE (BookingDate = '" + deletedDate + "') AND (Account = "
                + account + ")");
        if(currentBookings.size()>1){
            time = Multiplebookings(currentBookings);
        }else {
            time = currentBookings.get(0).getEntryTime().toString();
        }
        //gets the directory for this project and appends the name of the database file to it and sets it as the database location.
        String DatabaseLocation = System.getProperty("user.dir") + "\\ProjectDatabase.accdb";
            try {
                //sets the connection to the database using the database location
                Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
                //Creates a prepared statement
                PreparedStatement stmt = con.prepareStatement("DELETE FROM " + facility + " Where Account = ? AND BookingDate = ? AND EntryTime = ?");
                stmt.setInt(1,account);
                stmt.setString(2, deletedDate.toString());          //inputs the correct values into the prepared statement
                stmt.setString(3, time);
                stmt.executeUpdate();
                con.close();
                System.out.println("Deletion Successful\n----------------");
        }catch (Exception e) {
                System.out.println(e);


        }
    }

    private static String Multiplebookings(ArrayList<Booking> currentBookings) {
        Scanner input = new Scanner(System.in);
        LocalTime temp = LocalTime.of(0, 0, 0);
        System.out.println("Please select which booking you would like to delete");
        for (int i = 0; i < currentBookings.size(); i++) {                          //outputs all of the available bookings to delete and returns the selected one
            System.out.println(currentBookings.get(i).getEntryTime().toString());
        }
        return FormattCheck.getLocalTime(input, temp).toString();
    }

}
