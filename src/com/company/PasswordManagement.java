package com.company;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Random;

public class PasswordManagement {                               //Hashes password for data security
    public static String PasswordHasher(String Password) {
        String password = "";                                  //Used source Java-Create a secure password hash By Lokesh Gupta (25/01/2022) to help
        try {                                       //https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
            MessageDigest Digester = MessageDigest.getInstance("MD5");
            Digester.update(Password.getBytes());
            byte[] PassBytes = Digester.digest();
            for (int i = 0; i < PassBytes.length; i++) {                        //converts bytes of password into hexadecimal
                password = password + Integer.toString((PassBytes[i] & 0xff) + 0x100, 16).substring(1);
            }
            return password;
        } catch (Exception e) {
            System.out.println(e);
        }
        return password;
    }

    public static String passwordGenerator() {              //Generates a random password
        String password = "";                                   //initialises password variable
        ArrayList<Character> Characters = new ArrayList<>();
        Random random = new Random();
        for (char i = 'a'; i <= 'z'; i++) {
            Characters.add(i);
        }
        for (char i = 'A'; i <= 'Z'; i++) {                           //3 for loops add all upper and lowercase letters to arraylist as well as symbols.
            Characters.add(i);
        }
        for (char i = '!'; i <= '&'; i++) {
            Characters.add(i);
        }
        for (int i = 0; i < 8; i++) {
            password = password + Characters.get(random.nextInt(Characters.size()));           //randomly selects 8 of the characters from the arrayList
        }
        return password;
    }
}
