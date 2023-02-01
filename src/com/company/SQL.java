package com.company;

import com.company.Memberships.Users;
import org.hsqldb.rights.User;

import java.sql.*;
import java.util.ArrayList;

public class SQL {

    public static boolean LoginSQL(String Username, String Password) {
        String DatabaseLocation = System.getProperty("user.dir") + "\\ProjectDatabase.accdb";
        try {
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");

            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM Accounts";
            ResultSet RS = stmt.executeQuery(sql);
            while (RS.next()) {
                if (Username.equals(RS.getString("Username"))) {
                    if (RS.getString("Password").equals(Password)) {
                        con.close();
                        return true;
                    }
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Error in the SQL class: " + e);
        }
        return false;


    }

    public static ArrayList<Users> print(String sql) {

        ArrayList<Users> output = new ArrayList<>();
        String DatabaseLocation = System.getProperty("user.dir") + "\\ProjectDatabase.accdb";
        try {
            System.out.println(sql);
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet Rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = Rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            Rs.last();
            int rows = Rs.getRow();
            Rs.beforeFirst();
            while (Rs.next()){
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
    public static void add(){
        String DatabaseLocation = System.getProperty("user.dir") + "\\ProjectDatabase.accdb";
        try{
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            Statement stmt = con.createStatement();
            ResultSet Rs = stmt.executeQuery("INSERT INTO Users VALUES(6, 'zack', 'duckering', 'zackduckering@gmail.com', '07588445698', 'rh67ty', '67', 'parkhurst road')");
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }



}