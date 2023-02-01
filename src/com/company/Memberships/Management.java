package com.company.Memberships;

import com.company.SQL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Management {
    public static void search() {
        try {
            System.out.print("Please enter search criteria:       ");
            Scanner input = new Scanner(System.in);
            String criteria = input.nextLine();
            ArrayList<Users> choices = SQL.print("SELECT * FROM Users WHERE FirstName = '" + criteria + "' OR LastName = '" + criteria + "' OR Email = '"
                    + criteria + "' OR Phone = '" + criteria + "' OR Postcode = '" + criteria + "' OR HouseNumber = '"
                    + criteria + "' OR Street = '" + criteria + "'");
            if(choices.size()>1){
                for (int i = 0; i < choices.size(); i++) {
                    System.out.println((i +1) + " :  " + choices.get(i).getPhone());
                }
                System.out.println("Please select which user is desired.");
            }
            System.out.println(choices.get((input.nextInt()-1)).toString());
        } catch (Exception e) {
            System.out.println(e);
        }

    }public static void Register(){
        Scanner input = new Scanner(System.in);

        HashMap<String, String> registerValues = new HashMap<String, String>();
        System.out.print("Please enter the Firstname: ");
        registerValues.put("FirstName", input.next());
        System.out.print("Please enter the LastName: ");
        registerValues.put("LastName", input.next());
        System.out.print("Please enter the Email: ");
        registerValues.put("Email", input.next());
        System.out.print("Please enter the Phone: ");
        registerValues.put("Phone", input.next());
        System.out.print("Please enter the Postcode: ");
        registerValues.put("Postcode", input.next());
        System.out.print("Please enter the HouseNumber: ");
        registerValues.put("HouseNumber", input.next());
        System.out.print("Please enter the Street: ");
        registerValues.put("Street", input.next());
        SQL.add();

    }
}
