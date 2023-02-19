package com.company.Memberships;

import com.company.SQL.MembershipSQL;
import com.company.SQL.SQLSearches;

import java.util.Scanner;

public class ProduceMemb {
    public static Membership options() {
        try {
            Scanner input = new Scanner(System.in);
            boolean gym = false;
            boolean swim = false;
            boolean court = false;
            boolean premium = false;
            String gymS = "false";
            String swimS = "false";
            String courtS = "false";
            String premiumS = "false";
            System.out.print("Please answer 'YES' or 'NO' to the following membership options.\nGYM:");
            if (input.next().equalsIgnoreCase("YES")) {
                gym = true;
                gymS = "true";
            }
            System.out.print("Swim: ");
            if (input.next().equalsIgnoreCase("YES")) {
                swim = true;
                swimS = "true";
            }
            System.out.print("Court: ");
            if (input.next().equalsIgnoreCase("YES")) {
                court = true;
                courtS = "true";
            }
            System.out.print("Premium: ");
            if (input.next().equalsIgnoreCase("YES")) {
                premium = true;
                premiumS = "true";
            }
            ;
            Membership memberOption = new Membership(MembershipSQL.MembershipFind(gymS, swimS, courtS, premiumS), gym, swim, court, premium);
            return memberOption;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }
}
