package com.company.Bookings;

import com.company.Memberships.Accounts;
import com.company.Memberships.SearchUser;
import com.company.Memberships.Users;
import com.company.SQL.BookingSQL;
import com.company.SQL.MembershipSQL;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;


public class BookSlot {
    public static void BookSlot(String facility){       //books a slot in one of the 3 facilities in the leisure centre
        boolean Completed = true;
        Scanner input = new Scanner(System.in);
        try{
            Users BookingUser = SearchUser.search(false);
            Accounts BookingAccount = BookingSQL.AccountFind(BookingUser);      //gets the account object for the user
            while(Completed){
                System.out.println("Is the customer " + BookingAccount.getFirstName() + " " + BookingAccount.getLastName());
                if(input.next().equalsIgnoreCase("yes")){       //checks it is the correct user
                    Completed = false;
                }
            }
            System.out.println("----------------\nWelcome to the " + facility + " booking service!");
           if(MembershipSQL.checkMember(BookingAccount.getMembershipId(), facility)){       //checks the user has the correct permissions to use the facility

               while(!Completed){
                   LocalDate Selected = LocalDate.now();
                   LocalTime SelectedTime = LocalTime.of(0,0);
                   System.out.println("Please choose on of the following dates by typing it out:");
                   for (int i = 1; i < 7; i++) {
                       System.out.println(LocalDate.now().plusDays(i));         //outputs the next 7 days which are the only options to book the facility

                   }
                   ArrayList<LocalTime> availableTimes = new ArrayList<>();
                   Selected = FormattCheck.getLocalDate(input, Selected);
                   System.out.println();
                   for (int i = 8; i < 21; i++) {
                       availableTimes.add(LocalTime.of(i, 0));          //adds all times the facility is open to an arraylist
                   }

                   //makes the booking
                   Completed = isCompleted(facility, Completed, BookingAccount, input, Selected, SelectedTime, availableTimes);
               }



           }else{
                   System.out.println("Sorry you do not have access to this booking system. Please upgrade membership.");

        }
        }catch (Exception e){
            System.out.println(e);

}


}//Prints out all available times which are not fully booked and then takes you booking
    private static boolean isCompleted(String facility, boolean Completed, Accounts BookingAccount, Scanner input, LocalDate Selected, LocalTime SelectedTime, ArrayList<LocalTime> availableTimes) {
        ArrayList<Booking> currentBookings = BookingSQL.Bookings("SELECT * FROM " + facility + " WHERE (BookingDate = '" + Selected + "') AND (Account = "
                + BookingAccount.getAccountID() + ")");
        if(currentBookings.size()<2){       //checks the user has not used more than their 2 allocated bookings per day

            for (int i = 0; i < availableTimes.size(); i++) {
                currentBookings = BookingSQL.Bookings("SELECT * FROM " + facility + " WHERE (BookingDate = '" + Selected + "')" +
                        " AND (EntryTime = '" + availableTimes.get(i).toString() + "')");       //finds all bookings for that day at every hour
                if (currentBookings.size()>4){
                    availableTimes.remove(i);           //if any hours are fully booked it removes it from the arraylist of available times
                    Completed = true;
                }
            }
            for (int i = 0; i < availableTimes.size(); i++) {
                System.out.println(availableTimes.get(i));
            }System.out.println("----------------\nPlease type what time you would like to be chosen.");
            SelectedTime = FormattCheck.getLocalTime(input, SelectedTime);                  //takes the input of what time the user would like
            currentBookings = BookingSQL.Bookings("SELECT * FROM " + facility + " WHERE (BookingDate = '" + Selected + "') AND (EntryTime = '"
                + SelectedTime.toString() + "')");
            if(currentBookings.size()<5){
                BookingSQL.Book(Selected, SelectedTime, SelectedTime.plusHours(1), BookingAccount.getAccountID(), facility, BookingAccount.isSpecialRequirement());
                Completed = true;           //finishes the loop

                //outputs how many people will be using the facility at the same time as you.
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