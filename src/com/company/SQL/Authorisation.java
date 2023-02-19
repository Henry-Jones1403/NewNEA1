package com.company.SQL;

import java.sql.*;
import java.util.Scanner;

public class Authorisation {
    public static boolean AuthoriseAdmin() {
        try {
            boolean BrutePrevent = false;
            while (!BrutePrevent) {
                for (int i = 0; i < 5; i++) {
                    Scanner input = new Scanner(System.in);
                    System.out.print("----------------\nAUTHORISATION NEEDED\nPLEASE ENTER UNIQUE AUTHORISATION CODE:");
                    String code = input.next();
                    System.out.print("PLEASE ENTER UNIQUE PASSCODE:");
                    String passcode = input.next();
                    String DatabaseLocation = System.getProperty("user.dir") + "\\ProjectDatabase.accdb";
                    Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
                    Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet Rs = stmt.executeQuery("SELECT * FROM Authorisation WHERE (Code = '" + code + "') AND (Passcode = '"
                            + passcode + "')");
                    while (Rs.next()) {
                        ResultSetMetaData rsmd = Rs.getMetaData();
                        if ((Rs.getInt(4)) > 1) {
                            System.out.println("AUTHORISATION COMPLETE\n----------------");
                            return true;
                        }

                    }

                }
                BrutePrevent = true;


            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Please Try again");
        }

        return false;
    }
}
