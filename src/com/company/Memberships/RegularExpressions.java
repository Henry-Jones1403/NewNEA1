package com.company.Memberships;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressions {
    public static String RE_Email(String CheckedValue, String regex, String field, String Attribute) {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter " + field + ": " + Attribute);
        String email = input.next();
        String Rejectedpattern = "^(.+)@(\\S+)$";
        while (!(Pattern.compile(regex).matcher(email).matches())) {
            input.nextLine();
            System.out.print("Please enter correct " + field + ":");
            email = input.next();

    }return email;
}

        }


