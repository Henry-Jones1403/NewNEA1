package com.company.Bookings;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class FormattCheck {     //checks the date and time inputted by the user is the correct formatt
    public static LocalDate getLocalDate(Scanner input, LocalDate Selected) {
        boolean dateLoop = false;
        while (!dateLoop) {
            try {
                String date = input.next();
                Selected = LocalDate.parse(date);
                if (Selected.isAfter(LocalDate.now().plusDays(6))) { //checks the date is within the correct range shown
                    System.out.println("Please select one of the shown dates.");
                } else {
                    dateLoop = true;
                }

            } catch (DateTimeException D) {         //if the input throws up an error for this the input is not in the correct format
                System.out.println("Please enter it in the format yyyy-mm-dd");
            } catch (Exception e) {
                System.out.println(e);
            }

        }
        return Selected;
    }

    public static LocalTime getLocalTime(Scanner input, LocalTime Time) {
        boolean dateLoop = false;
        while (!dateLoop) {
            try {
                Time = LocalTime.parse(input.next());
                if (Time.isAfter(LocalTime.of(20, 00))||Time.isBefore(LocalTime.of(8, 00))){           //checks the time is within the correct range shown
                    System.out.println("Please select one of the shown dates.");
                } else {
                    dateLoop = true;
                }

            } catch (DateTimeException D) { //if the input throws up an error for this the input is not in the correct format
                System.out.println("Please enter it in the format hh:mm");
            } catch (Exception e) {
                System.out.println(e);
            }

        }
        return Time;
    }
}
