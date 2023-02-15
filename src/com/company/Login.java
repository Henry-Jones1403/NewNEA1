package com.company;

import com.company.Bookings.GymBooking;
import com.company.Memberships.Management;
import com.company.Memberships.Register;
import com.company.SQL.SQLSearches;

import java.util.Locale;
import java.util.Scanner;

public class Login {
    public static boolean Login() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter username: ");
        String username = input.next();
        System.out.println("Please enter password: ");
        String password = input.next();
        if (SQLSearches.LoginSQL(username, password)) {
            return true;
        }
        return false;

    }

    public static void Startup() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to MyLeisureCentre. Please select which facility you would like to use by typing in one of the following options." +
                "\n- Search\n- Register\n- Gym");
        String Option = input.next();
        switch (Option.toUpperCase(Locale.ROOT)) {
            case "SEARCH":
                Management.search(false);
                break;
            case "REGISTER":
                Register.Register();
                break;
            case "GYM":
                GymBooking.BookSlot();
                break;
        }
    }

}
