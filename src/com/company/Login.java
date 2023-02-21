package com.company;

import com.company.Bookings.BookSlot;
import com.company.Memberships.*;
import com.company.SQL.DeleteBooking;
import com.company.SQL.SQLSearches;

import java.util.Locale;
import java.util.Scanner;

public class Login {
    public static boolean Login() {             //handles the input of the username and password
        Scanner input = new Scanner(System.in);
        System.out.println("----------------\nPlease enter username: ");
        String username = input.next();
        System.out.println("----------------\nPlease enter password: ");
        String password = input.next();
        if (SQLSearches.LoginSQL(username, password)) {
            return true;
        }
        return false;

    }

    public static void Startup() {          //forms the home screen of the system. Gives the pathway to all other facilities
        Scanner input = new Scanner(System.in);
        System.out.println("----------------\nWelcome to MyLeisureCentre. Please select which facility you would like to use by typing in one of the following options." +
                "\n- Search\n- Register\n- Gym\n- Swim\n- Court\n- Bookings\n- Delete\n- Modify\n- All Emails");
        String Option = input.nextLine();
        switch (Option.toUpperCase(Locale.ROOT)) {
            case "SEARCH":
                SearchUser.search(true);
                break;
            case "REGISTER":
                Register.Register();
                break;
            case "GYM":
                BookSlot.BookSlot("Gym");
                break;
            case "SWIM":
                BookSlot.BookSlot("Swim");
                break;
            case "COURT":
                BookSlot.BookSlot("Court");                     //takes the input of what facility is being used.
                break;
            case "BOOKINGS":
                Print.PrintBookings("Gym");
                break;
            case "DELETE":
                DeleteBooking.deleteChoice();
                break;
            case "MODIFY":
                ChangeMembership.ChangeMembership();
                break;
            case "ALL EMAILS":
                SearchEmails.Emailsort();
                break;
        }
    }

}
