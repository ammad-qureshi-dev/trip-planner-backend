package com.trip_planner.app.utils;

import com.trip_planner.app.exceptions.HashingException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HashingUtil {
    private HashingUtil() {}

    public static String generateHashedInput(String s) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(s.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new HashingException(e.getMessage());
        }
    }
}
