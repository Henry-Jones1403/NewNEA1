package com.company;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class PasswordManagement {
    public static String PasswordHasher(String Password){
        String password = "";
        try{
            MessageDigest Digester = MessageDigest.getInstance("MD5");
            Digester.update(Password.getBytes());
            byte[] PassBytes = Digester.digest();
            for (int i = 0; i < PassBytes.length; i++) {
                password = password + Integer.toString((PassBytes[i] & 0xff) + 0x100, 16).substring(1);
            }
            return password;
        }catch(Exception e){
            System.out.println(e);
        }return password;
    }
}
