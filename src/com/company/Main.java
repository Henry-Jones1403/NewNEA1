package com.company;

import com.company.Bookings.GymBooking;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        GymBooking.BookSlot();
        boolean loop = true;
        boolean access = false;
        while(!access){
            if(Login.Login()){
                access = true;
            }

        }while(loop){
          Login.Startup();
        }


    }
}
