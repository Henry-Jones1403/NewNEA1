package com.company.Bookings;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;


public class GymBooking {
    public static void BookSlot(){
        Scanner input = new Scanner(System.in);
        LocalDate Selected = LocalDate.now();
        System.out.println("Please choose on of the following dates by typing it out:");
        for (int i = 1; i < 7; i++) {
            System.out.println(LocalDate.now().plusDays(i));

        }
    boolean dateLoop = false;
    while(!dateLoop){
        try{
            String date = input.next();
            Selected = LocalDate.parse(date);
            if(Selected.isAfter(LocalDate.now().plusDays(6))){
                System.out.println("Please select one of the shown dates.");
            }else{
                dateLoop = true;
            }

        }catch(DateTimeException D){
            System.out.println("Please enter it in the format yyyy-mm-dd");
        }catch (Exception e){
            System.out.println(e);
        }

    }System.out.println(Selected);

}
        }