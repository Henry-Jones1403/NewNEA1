package com.company.Memberships;

import com.company.SQL.SQLSearches;

import java.util.ArrayList;
import java.util.Scanner;

public class Management {
    public static Users search(boolean imbedded) {
        boolean UserFound = false;
        try {
            while(!UserFound){
                if(imbedded){
                    System.out.print("Please enter search criteria: ");
                }else{
                    System.out.println("Please enter email or phone:");
                }
                Scanner input = new Scanner(System.in);
                String criteria = input.nextLine();
                ArrayList<Users> choices = SQLSearches.Search("SELECT * FROM Users WHERE FirstName = '" + criteria + "' OR LastName = '" + criteria + "' OR Email = '"
                        + criteria + "' OR Phone = '" + criteria + "' OR Postcode = '" + criteria + "' OR HouseNumber = '"
                        + criteria + "' OR Street = '" + criteria + "'");
                if (choices.size() != 0) {
                    if (choices.size() > 1) {
                        for (int i = 0; i < choices.size(); i++) {
                            System.out.println((i + 1) + " :  " + choices.get(i).getPhone());
                        }
                        System.out.println("Please select which user is desired.");
                        Users SelectedUser = choices.get((input.nextInt() - 1));
                        System.out.println(SelectedUser.toString());
                        return SelectedUser;
                    } else {
                        System.out.println(choices.get(0).toString());
                        return choices.get(0);
                    }
                } else {
                    System.out.println("Please try again entering one of the following pieces of information. Firstname, Lastname, " +
                            "Email, Phone, postcode, HouseNumber, Street");

                }


            }
            }catch (Exception e) {
            System.out.println(e);
        }

return null;
    }


}
