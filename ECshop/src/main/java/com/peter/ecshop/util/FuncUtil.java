package com.peter.ecshop.util;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
@Component
public class FuncUtil {
    public  String generateRandomUserId() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        Set<Character> usedChars = new HashSet<>();
        while (sb.length() < 5) {
            char c = chars.charAt(random.nextInt(chars.length()));
            if (!usedChars.contains(c)) {
                sb.append(c);
                usedChars.add(c);
            }
        }
        return sb.toString();
    }
}
