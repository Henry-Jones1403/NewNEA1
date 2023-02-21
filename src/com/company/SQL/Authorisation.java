package com.company.SQL;

import java.sql.*;
import java.util.Scanner;

public class Authorisation {
    public static boolean[] AuthoriseAdmin() {      //provide authorisation to add a new admin or an account with special requirements
        boolean[] adminReq = {false, false};
        try {
            boolean BrutePrevent = false;
            while (!BrutePrevent) {
                for (int i = 0; i < 5; i++) {
                    Scanner input = new Scanner(System.in);
                    System.out.print("----------------\nAUTHORISATION NEEDED\nPLEASE ENTER UNIQUE AUTHORISATION CODE:");
                    String code = input.next();
                    System.out.print("PLEASE ENTER UNIQUE PASSCODE:");
                    String passcode = input.next();
                    //gets the directory for this project and appends the name of the database file to it and sets it as the database location.
                    String DatabaseLocation = System.getProperty("user.dir") + "\\ProjectDatabase.accdb";
                    //sets the connection to the database using the database location
                    Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
                    //forms a statement which is used to format the results
                    Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    //Executes the SQL and returns the results as a ResultSet
                    ResultSet Rs = stmt.executeQuery("SELECT * FROM Authorisation WHERE (Code = '" + code + "') AND (Passcode = '"
                            + passcode + "')");
                    //Loops through all the records
                    while (Rs.next()) {
                        ResultSetMetaData rsmd = Rs.getMetaData();
                        if ((Rs.getInt(4)) > 1) {
                            System.out.print("Admin: ");
                            adminReq[0] = yesNo(input.next());      //inputs into the list the correct boolean values
                            System.out.print("Special Requirements: ");
                            adminReq[1] = yesNo(input.next());
                            System.out.println("AUTHORISATION COMPLETE\n----------------");
                            return adminReq;
                        }

                    }

                }
                BrutePrevent = true;


            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Please Try again");
        }

        return adminReq;
    }

    private static boolean yesNo(String answer) {
        if (answer.equalsIgnoreCase("yes")) {       //turns human language into boolean
            return true;
        } else {
            return false;
        }
    }
}
