package org.launchcode.meander.meander;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGeneratorTests {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String plainPassword = "nam2020";
        String encodedPassword = passwordEncoder.encode(plainPassword);


        System.out.println(encodedPassword);
    }
}