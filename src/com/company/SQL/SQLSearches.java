package com.company.SQL;

import com.company.Memberships.Membership;
import com.company.Memberships.Users;
import com.company.PasswordManagement;
import org.hsqldb.rights.User;

import java.sql.*;
import java.util.ArrayList;

public class SQLSearches {

    public static boolean LoginSQL(String Username, String Password) {          //confirms the username and password are correct and the permissions are correct
        //gets the directory for this project and appends the name of the database file to it and sets it as the database location.
        String DatabaseLocation = System.getProperty("user.dir") + "\\ProjectDatabase.accdb";
        try {
            //sets the connection to the database using the database location
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            //forms a statement which is used to format the results
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM Accounts";
            //Executes the SQL and returns the results as a ResultSet
            ResultSet RS = stmt.executeQuery(sql);
            boolean username = false;
            boolean password = false;
            //Loops through all the records
            while (RS.next()) {
                if (Username.equals(RS.getString("Username"))) {
                    username = true;
                    if (RS.getString("Password").equals(PasswordManagement.PasswordHasher(Password))) {
                        password = true;
                        if (RS.getBoolean(4)) {
                            con.close();
                            return true;
                        } else if (!RS.getBoolean(4)) {
                            System.out.println("Sorry you do not have permission to use this system!");
                        }
                    }
                }
            }if(!username){
                System.out.println("Incorrect username");
            }else if(!password){
                System.out.println("password incorrect");
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Error in the SQL class: " + e);
        }
        return false;


    }

    public static ArrayList<Users> Search(String sql) {                     //creates a list of User objects with all of the data from the tables
        boolean register = true;
        ArrayList<Users> output = new ArrayList<>();
        //gets the directory for this project and appends the name of the database file to it and sets it as the database location.
        String DatabaseLocation = System.getProperty("user.dir") + "\\ProjectDatabase.accdb";
        try {
            //sets the connection to the database using the database location
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            //forms a statement which is used to format the results
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //Executes the SQL and returns the results as a ResultSet
            ResultSet Rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = Rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            Rs.last();
            int rows = Rs.getRow();
            Rs.beforeFirst();
            while (Rs.next()) {
                for (int i = 0; i < rows; i++) {
                    Users newUser = new Users(Rs.getInt(1), Rs.getString(2), Rs.getString(3),
                            Rs.getString(4), Rs.getString(5), Rs.getString(6), Rs.getInt(7), Rs.getString(8));
                    output.add(newUser);
                    Rs.next();
                }
            }
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return output;
    }


}