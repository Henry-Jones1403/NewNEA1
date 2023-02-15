package com.company.Bookings;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class FormattCheck {
    public static LocalDate getLocalDate(Scanner input, LocalDate Selected) {
        boolean dateLoop = false;
        while (!dateLoop) {
            try {
                String date = input.next();
                Selected = LocalDate.parse(date);
                if (Selected.isAfter(LocalDate.now().plusDays(6))) {
                    System.out.println("Please select one of the shown dates.");
                } else {
                    dateLoop = true;
                }

            } catch (DateTimeException D) {
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
                if (Time.isAfter(LocalTime.of(20, 00))) {
                    System.out.println("Please select one of the shown dates.");
                } else {
                    dateLoop = true;
                }

            } catch (DateTimeException D) {
                System.out.println("Please enter it in the format hh:mm");
            } catch (Exception e) {
                System.out.println(e);
            }

        }
        return Time;
    }
}
