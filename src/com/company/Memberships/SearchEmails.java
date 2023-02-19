package com.company.Memberships;

import com.company.SQL.SQLSearches;

import java.util.ArrayList;

public class SearchEmails {
    public static void Emailsort() {
        ArrayList<Users> AllUsers = SQLSearches.Search("SELECT* FROM Users");
        String[] Emails = new String[AllUsers.size()];
        for (int i = 0; i < AllUsers.size(); i++) {
            Emails[i] = AllUsers.get(i).getEmail();
        }
        Sort(Emails);
        for (int i = 0; i < Emails.length; i++) {
            System.out.println(Emails[i]);
        }
    }

    private static void Sort(String[] Emails) {
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
        System.out.println("----------------\nAll Emails:");
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
