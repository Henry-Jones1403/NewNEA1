package com.company;

public class Main {

    public static void main(String[] args) {
        boolean loop = true;
        boolean access = false;
        int i = 0;
        while(!access){         //starts the loop to allow multiple logins
            while(i<4){             //allows only 4 attempts
                if(Login.Login()){
                    access = true;          //if the username and password match then allow access
                    i = 5;
                }i++;
            }if(i==4){
                System.out.println("----------------\nToo many failed attempts\n----------------");
                System.exit(0);     //if the maximum amount of attempts has been reached close the program
            }
        }while(loop){
          Login.Startup();      //Enters into the main code.
        }


    }
}
