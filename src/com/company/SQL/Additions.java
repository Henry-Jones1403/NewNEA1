package com.company.SQL;

import com.company.Memberships.Accounts;
import com.company.Memberships.Membership;
import com.company.Memberships.Users;
import com.company.PasswordManagement;

import java.sql.*;
import java.util.ArrayList;

public class Additions {
    public static void add(String sqlst) {  //Adds entries into the database.
        //gets the directory for this project and appends the name of the database file to it and sets it as the database location.
        String DatabaseLocation = System.getProperty("user.dir") + "\\ProjectDatabase.accdb";
        try {
            //sets the connection to the database using the database location
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            //forms a statement
            Statement stmt = con.createStatement();
            //Executes the SQL and returns
            stmt.execute(sqlst);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
//adds new account into account row matching with user
    public static void  addAccount(boolean admin, String FirstName_, String LastName_, String PhoneNumber, Membership member, boolean imbedded, boolean SpecialRequirements) {
        String DatabaseLocation = System.getProperty("user.dir") + "\\ProjectDatabase.accdb";
        ArrayList<Users> repeated_names = new ArrayList<>();
        Integer UserID = 0;
        String unHashedPassword = "";
        try {
            repeated_names = SQLSearches.Search("SELECT * FROM USERS WHERE LastName = '" + LastName_ + "' AND FirstName = '" + FirstName_ + "'");
            for (int i = 0; i < repeated_names.size(); i++) {                       //Forms a username by recognising how many repeating surnames there are
                if (repeated_names.get(i).getPhone().equals(PhoneNumber)) {
                    UserID = repeated_names.get(i).getUserID();

                }
            }
            if(imbedded){
                String username = FirstName_ + "_" + LastName_ + Integer.toString(repeated_names.size());
                //sets the connection to the database using the database location
                Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
                //prepares a statement to insert
                PreparedStatement pstmt = con.prepareStatement("INSERT " +
                        "INTO Accounts(Username, Password, Admin, UserID, MembershipID, SpecialRequirements) " +
                        "VALUES(?, ?, ?, ?, ?, ?)");
                unHashedPassword = PasswordManagement.passwordGenerator();
                String password = PasswordManagement.PasswordHasher(unHashedPassword);
                pstmt.setString(1, username);
                pstmt.setString(2, password);               //inputs the correct values into the prepared statement
                pstmt.setBoolean(3, admin);
                pstmt.setInt(4, UserID);
                pstmt.setInt(5, member.getMembershipID());
                pstmt.setBoolean(6, SpecialRequirements);
                //Executes the SQL and returns the results as a ResultSet
                pstmt.executeUpdate();
                System.out.println("Your temporary password is: " + unHashedPassword);      //outputs temporary password
                con.close();
            }

        } catch (Exception e) {

            System.out.println(e);
        }
    }
}
