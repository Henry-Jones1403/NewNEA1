package com.company.SQL;

import com.company.Bookings.FormattCheck;
import com.company.Memberships.Accounts;
import com.company.Memberships.Management;
import com.company.Memberships.Users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class DeleteBooking {
    public static void deleteChoice(){
        Scanner input = new Scanner(System.in);
        System.out.println("----------------\nPlease choose which facility you would like to delete a booking from:\n- Gym\n- Swim\n- Court ");
        String Facility = input.next();
        if((Facility.equalsIgnoreCase("swim"))||(Facility.equalsIgnoreCase("gym"))||(Facility.equalsIgnoreCase("court"))){
            LocalDate Selected = LocalDate.now();
            LocalTime SelectedTime = LocalTime.of(0,0);
            LocalTime now = LocalTime.of(13, 0, 0);
            System.out.println("----------------\nPlease choose on of the following dates by typing it out:");
            for (int i = 1; i < 7; i++) {
                System.out.println(LocalDate.now().plusDays(i));
            }
            Selected = FormattCheck.getLocalDate(input, Selected);
            Users DeletingUser = Management.search(false);
            Accounts DeletingAccount = BookingSQL.AccountFind(DeletingUser);
            Deletion(Selected, DeletingAccount.getAccountID(), Facility);
        }else{
            System.out.println("Please input a correct facility using the specified format.");
        }

    }
    public static void Deletion(LocalDate deletedDate, Integer account, String facility){
        String DatabaseLocation = System.getProperty("user.dir") + "\\ProjectDatabase.accdb";
        try {
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            PreparedStatement stmt = con.prepareStatement("DELETE FROM " + facility + " Where Account = ? AND BookingDate = ?");
stmt.setInt(1,account);
stmt.setString(2, deletedDate.toString());
            stmt.executeUpdate();
            con.close();
            System.out.println("Deletion Successful\n----------------");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
