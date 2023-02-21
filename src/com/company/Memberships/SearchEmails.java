package com.company.Memberships;

import com.company.SQL.SQLSearches;

import java.util.ArrayList;

public class SearchEmails {     //prints all emails in alphabetical order
    public static void Emailsort() {
        ArrayList<Users> AllUsers = SQLSearches.Search("SELECT* FROM Users");       //Gets all rows from the users table
        String[] Emails = new String[AllUsers.size()];      //initialises list which will store all emails
        for (int i = 0; i < AllUsers.size(); i++) {
            Emails[i] = AllUsers.get(i).getEmail();         //Enters all emails into the list
        }
        Sort(Emails);       //sorts the emails
        System.out.println("----------------\nAll Emails:");
        for (String email : Emails) {
            System.out.println(email);
        }
    }

    private static void Sort(String[] Emails) {         //help using https://www.codepile.net/pile/n627owzd to explain fundamentals
        if (Emails.length < 2) {
            return;
        }
        int middle = Emails.length / 2;
        String[] left = new String[middle];
        String[] right = new String[Emails.length - middle];
        for (int i = 0; i < middle; i++) {
            left[i] = Emails[i];
        }
        for (int i = middle; i < Emails.length; i++) {
            right[i - middle] = Emails[i];
        }
        Sort(left);
        Sort(right);
        merge(Emails, left, right);
    }

    private static void merge(String[] Emails, String[] left, String[] right) {
        int i = 0;
        int j = 0;
        int k = 0;
        Character.getNumericValue(left[i].charAt(0));
        while (i < left.length && j < right.length) {
            if (Character.getNumericValue(left[i].charAt(0)) <= Character.getNumericValue(right[j].charAt(0))) {
                Emails[k] = left[i];
                i++;
            } else {
                Emails[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < left.length) {
            Emails[k] = left[i];
            i++;
            k++;
        }
        while (j < right.length) {
            Emails[k] = right[j];
            j++;
            k++;
        }

    }
}
