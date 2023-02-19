package com.company;

public class Main {

    public static void main(String[] args) {
        boolean loop = true;
        boolean access = false;
        int i = 0;
        while(!access){
            while(i<4){
                if(Login.Login()){
                    access = true;
                    i = 5;
                }i++;
            }if(i==4){
                System.out.println("----------------\nToo many failed attempts\n----------------");
                System.exit(0);
            }


        }while(loop){
          Login.Startup();
        }


    }
}
