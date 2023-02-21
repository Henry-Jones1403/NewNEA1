package com.company.Memberships;

import com.company.SQL.MembershipSQL;
import com.company.SQL.SQLSearches;

import java.util.Scanner;

public class ProduceMemb {
    public static Membership options() {        //produces a membership object from inputs.
        try {
            Scanner input = new Scanner(System.in);
            boolean gym = false;
            boolean swim = false;
            boolean court = false;
            boolean premium = false;
            System.out.print("Please answer 'YES' or 'NO' to the following membership options.\nGYM:");
            if (input.next().equalsIgnoreCase("YES")) {
                gym = true;
            }
            System.out.print("Swim: ");
            if (input.next().equalsIgnoreCase("YES")) {                 //asks for seperate inputs of boolean for Swim, Gym, Court and premium
                swim = true;
            }                                                                      // these values then create the facilities that are available to them
            System.out.print("Court: ");
            if (input.next().equalsIgnoreCase("YES")) {
                court = true;
            }
            System.out.print("Premium: ");
            if (input.next().equalsIgnoreCase("YES")) {
                premium = true;
            }

            //Creates a Membership object eith the membership id of that sequence of membership boolean values.
            Membership memberOption = new Membership(MembershipSQL.MembershipFind(gym, swim, court, premium), gym, swim, court, premium);
            return memberOption;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }
}
