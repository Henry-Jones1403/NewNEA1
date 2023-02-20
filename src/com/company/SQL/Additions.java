package com.company.SQL;

import com.company.Memberships.Accounts;
import com.company.Memberships.Membership;
import com.company.Memberships.Users;
import com.company.PasswordManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class Additions {
    public static void add(String sqlst) {
        String DatabaseLocation = System.getProperty("user.dir") + "\\ProjectDatabase.accdb";
        try {
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            Statement stmt = con.createStatement();
            stmt.execute(sqlst);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void  addAccount(boolean admin, String FirstName_, String LastName_, String PhoneNumber, Membership member, boolean imbedded, boolean SpecialRequirements) {
        String DatabaseLocation = System.getProperty("user.dir") + "\\ProjectDatabase.accdb";
        ArrayList<Users> repeated_names = new ArrayList<>();
        Integer UserID = 0;
        try {
            repeated_names = SQLSearches.Search("SELECT * FROM USERS WHERE LastName = '" + LastName_ + "'");
            for (int i = 0; i < repeated_names.size(); i++) {
                if (repeated_names.get(i).getPhone().equals(PhoneNumber)) {
                    UserID = repeated_names.get(i).getUserID();

                }
            }
            if(imbedded){

                String username = FirstName_ + "_" + LastName_ + Integer.toString(repeated_names.size());
                Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
                Statement stmt = con.createStatement();
                String password = PasswordManagement.PasswordHasher("password");
                stmt.execute("INSERT INTO Accounts(Username, Password, Admin, UserID, MembershipID) VALUES('" + username + "', '"
                        + password + "', '" + admin + "', '" + UserID + "', '" + member.getMembershipID() + "', '" + SpecialRequirements + "')");
                con.close();
            }

        } catch (Exception e) {

            System.out.println(e);
        }
    }
}
