package com.company.Memberships;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressions {       //checks phone and email for regular expressions to makes ure they are correctly formatted
    public static String RE_Email(String CheckedValue, String regex, String field, String Attribute) {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter " + field + ": " + Attribute);
        String email = input.next();
        while (!(Pattern.compile(regex).matcher(email).matches())) {            //checks the input against a set of prohibited rules
            input.nextLine();
            System.out.print("Please enter correct " + field + ":");
            email = input.next();

    }return email;
}

        }


