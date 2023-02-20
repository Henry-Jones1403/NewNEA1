package com.company.Memberships;

import com.company.SQL.Additions;
import com.company.SQL.Authorisation;
import com.company.SQL.SQLSearches;

import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class Register {
    public static void Register() {
        try{
            String Email = "";
            Scanner input = new Scanner(System.in);
            boolean[] admin = new boolean[2];
            System.out.println("Are you adding an admin or an account with special requirements?");
            if (input.next().toUpperCase(Locale.ROOT).equalsIgnoreCase("YES")){
                    admin = Authorisation.AuthoriseAdmin();
            }
            HashMap<String, String> registerValues = new HashMap<String, String>();
            input.nextLine();
            System.out.print("Please enter the Firstname: ");
            registerValues.put("FirstName", input.nextLine());
            System.out.print("Please enter the LastName: ");
            registerValues.put("LastName", input.nextLine());
            registerValues.put("Email", RegularExpressions.RE_Email(Email, "^(.+)@(\\S+)$", "Email", ""));
            registerValues.put("Phone", RegularExpressions.RE_Email(Email, "(0/91)?[7-9][0-9]{9}", "Phone", "+44 "));
            System.out.print("Please enter the Postcode: ");
            registerValues.put("Postcode", input.nextLine());
            System.out.print("Please enter the HouseNumber: ");
            registerValues.put("HouseNumber", input.nextLine());
            System.out.print("Please enter the Street: ");
            registerValues.put("Street", input.nextLine());
            Membership MemberDet = ProduceMemb.options();
            Additions.add("INSERT INTO Users(FirstName, LastName, Email, Phone, Postcode, HouseNumber, Street) VALUES('" + registerValues.get("FirstName")
                    + "', '" + registerValues.get("LastName") + "', '" + registerValues.get("Email") + "', '" + registerValues.get("Phone") + "', '"
                    + registerValues.get("Postcode") + "', '" + registerValues.get("HouseNumber") + "', '" + registerValues.get("Street") + "')");
            Additions.addAccount(admin[0], registerValues.get("FirstName"), registerValues.get("LastName"), registerValues.get("Phone"),
                    MemberDet, true, admin[1]);
            System.out.println("Successfully added new member.\n");
        }catch(Exception e){
            System.out.println("Please retry!");
        }

    }


}
