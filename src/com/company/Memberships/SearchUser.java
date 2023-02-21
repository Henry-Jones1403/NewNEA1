package com.company.Memberships;

import com.company.SQL.Additions;
import com.company.SQL.SQLSearches;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Scanner;

public class SearchUser {
    public static Users search(boolean imbedded) {      //Searches for a user using an inputted value from the user and outputs the result
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
                //produces an arraylist of users based of the matching entries in the database
                ArrayList<Users> choices = SQLSearches.Search("SELECT * " +
                        "FROM Users " +
                        "WHERE FirstName = '" + criteria + "' OR LastName = '" + criteria + "' OR Email = '"
                        + criteria + "' OR Phone = '" + criteria + "' OR Postcode = '" + criteria + "' OR HouseNumber = '"
                        + criteria + "' OR Street = '" + criteria + "' ");
                if (choices.size() != 0) {
                    if (choices.size() > 1) { //if there are multiple records matching the search prints out the phones numbers and asks which one is wanted
                        for (int i = 0; i < choices.size(); i++) {
                            System.out.println((i + 1) + " :  " + choices.get(i).getPhone());
                        }
                        System.out.println("Please select which user is desired.");
                        Users SelectedUser = choices.get((input.nextInt() - 1));
                        if(imbedded){
                            System.out.println(SelectedUser.toString());
                        }

                        return SelectedUser;
                    } else {
                        if(imbedded) {
                            System.out.println(choices.get(0).toString());
                        }
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
