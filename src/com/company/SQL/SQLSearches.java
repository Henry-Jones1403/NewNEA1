package com.company.SQL;

import com.company.Memberships.Membership;
import com.company.Memberships.Users;
import com.company.PasswordManagement;
import org.hsqldb.rights.User;

import java.sql.*;
import java.util.ArrayList;

public class SQLSearches {

    public static boolean LoginSQL(String Username, String Password) {
        String DatabaseLocation = System.getProperty("user.dir") + "\\ProjectDatabase.accdb";
        try {
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");

            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM Accounts";
            ResultSet RS = stmt.executeQuery(sql);
            boolean username = false;
            boolean password = false;
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

    public static ArrayList<Users> Search(String sql) {
        boolean register = true;
        ArrayList<Users> output = new ArrayList<>();
        String DatabaseLocation = System.getProperty("user.dir") + "\\ProjectDatabase.accdb";
        try {
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
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