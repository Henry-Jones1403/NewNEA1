package com.company;

import com.company.Bookings.GymBooking;

public class Main {

    public static void main(String[] args) {
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
