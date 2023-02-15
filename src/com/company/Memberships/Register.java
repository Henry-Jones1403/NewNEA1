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
            Scanner input = new Scanner(System.in);
            boolean admin = false;
            System.out.println("Are you adding an admin?");
            if (input.next().toUpperCase(Locale.ROOT).equalsIgnoreCase("YES")){
                    admin = Authorisation.AuthoriseAdmin();
            }
            HashMap<String, String> registerValues = new HashMap<String, String>();
            input.nextLine();
            System.out.print("Please enter the Firstname: ");
            registerValues.put("FirstName", input.nextLine());
            System.out.print("Please enter the LastName: ");
            registerValues.put("LastName", input.nextLine());
            System.out.print("Please enter the Email: ");
            registerValues.put("Email", input.nextLine());
            System.out.print("Please enter the Phone: ");
            registerValues.put("Phone", input.nextLine());
            System.out.print("Please enter the Postcode: ");
            registerValues.put("Postcode", input.nextLine());
            System.out.print("Please enter the HouseNumber: ");
            registerValues.put("HouseNumber", input.nextLine());
            System.out.print("Please enter the Street: ");
            registerValues.put("Street", input.nextLine());
            Membership tester = options();
            System.out.println(tester.toString());

            Additions.add("INSERT INTO Users(FirstName, LastName, Email, Phone, Postcode, HouseNumber, Street) VALUES('" + registerValues.get("FirstName")
                    + "', '" + registerValues.get("LastName") + "', '" + registerValues.get("Email") + "', '" + registerValues.get("Phone") + "', '"
                    + registerValues.get("Postcode") + "', '" + registerValues.get("HouseNumber") + "', '" + registerValues.get("Street") + "')");
            Additions.addAccount(admin, registerValues.get("FirstName"), registerValues.get("LastName"), registerValues.get("Phone"), tester, true);
            System.out.println("Successfully added new member.\n");
        }catch(Exception e){
            System.out.println("Please retry!");
        }

    }

    public static Membership options() {
        try {
            Scanner input = new Scanner(System.in);
            boolean gym = false;
            boolean swim = false;
            boolean court = false;
            boolean premium = false;
            String gymS = "false";
            String swimS = "false";
            String courtS = "false";
            String premiumS = "false";
            System.out.print("Please answer 'YES' or 'NO' to the following membership options.\nGYM:");
            if (input.next().equalsIgnoreCase("YES")) {
                gym = true;
                gymS = "true";
            }
            System.out.print("Swim: ");
            if (input.next().equalsIgnoreCase("YES")) {
                swim = true;
                swimS = "true";
            }
            System.out.print("Court: ");
            if (input.next().equalsIgnoreCase("YES")) {
                court = true;
                courtS = "true";
            }
            System.out.print("Premium: ");
            if (input.next().equalsIgnoreCase("YES")) {
                premium = true;
                premiumS = "true";
            }
            ;
            Membership memberOption = new Membership(SQLSearches.MembershipFind(gymS, swimS, courtS, premiumS), gym, swim, court, premium);
            return memberOption;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }
}
