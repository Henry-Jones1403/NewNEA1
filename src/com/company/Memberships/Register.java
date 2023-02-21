package com.company.Memberships;

import com.company.SQL.Additions;
import com.company.SQL.Authorisation;
import com.company.SQL.SQLSearches;

import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class Register {
    public static void Register() {     //registers new account and user onto the database.
        try{
            String Email = "";          //initialises email variable
            Scanner input = new Scanner(System.in);
            boolean[] admin = new boolean[2];                       //creates a list which stores the boolean values of admin and SpecialRequirements
            System.out.println("Are you adding an admin or an account with special requirements?");
            if (input.next().toUpperCase(Locale.ROOT).equalsIgnoreCase("YES")){
                    admin = Authorisation.AuthoriseAdmin();                         //inputs the values for specialrequirements and admin into the list
            }
            HashMap<String, String> registerValues = new HashMap<String, String>();                 //creates a hashmap to store the value of each piece of data required
            input.nextLine();
            System.out.print("Please enter the Firstname: ");
            registerValues.put("FirstName", input.nextLine());
            System.out.print("Please enter the LastName: ");
            registerValues.put("LastName", input.nextLine());
            registerValues.put("Email", RegularExpressions.RE_Email(Email, "^(.+)@(\\S+)$", "Email", ""));      //Checks for regular expressions
            registerValues.put("Phone", RegularExpressions.RE_Email(Email, "(0/91)?[7-9][0-9]{9}", "Phone", "+44 "));
            System.out.print("Please enter the Postcode: ");
            registerValues.put("Postcode", input.nextLine());
            System.out.print("Please enter the HouseNumber: ");
            registerValues.put("HouseNumber", input.nextLine());
            System.out.print("Please enter the Street: ");
            registerValues.put("Street", input.nextLine());
            Membership MemberDet = ProduceMemb.options();
            //adds the data inputted into the Users table
            Additions.add("INSERT INTO Users(FirstName, LastName, Email, Phone, Postcode, HouseNumber, Street) VALUES('" + registerValues.get("FirstName")
                    + "', '" + registerValues.get("LastName") + "', '" + registerValues.get("Email") + "', '" + registerValues.get("Phone") + "', '"
                    + registerValues.get("Postcode") + "', '" + registerValues.get("HouseNumber") + "', '" + registerValues.get("Street") + "')");
            //Creates a username and password and inputs in the additional data, such as membership. It then links that entry to the userID entry just inputted.
            Additions.addAccount(admin[0], registerValues.get("FirstName"), registerValues.get("LastName"), registerValues.get("Phone"),
                    MemberDet, true, admin[1]);
            System.out.println("Successfully added new member.\n");
        }catch(Exception e){
            System.out.println("Please retry!");
        }

    }


}
