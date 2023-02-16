package biz.buynow.bank.controller;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
// Java program to calculate SHA hash value

public class HashGenerator {
    public static String SHA1 = "SHA-1";
    public static String SHA256 = "SHA-256";
    public static String SHA256withRSA = "SHA256withRSA";

    public static byte[] getSHAInstance(String input, String hashMethod) throws NoSuchAlgorithmException {
        // Static getInstance method is called with hashing SHA
        MessageDigest messageDigest = MessageDigest.getInstance(hashMethod);

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    public static String getSHA256(String input) {
        try {
            String output = toHexString(getSHAInstance(input, HashGenerator.SHA256));
            return output;
        }
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown for incorrect algorithm: " + e);
        }
        return null;
    }

    public static String getSHA1(String input) {
        try {
            String output = toHexString(getSHAInstance(input, HashGenerator.SHA1));
            return output;
        }
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown for incorrect algorithm: " + e);
        }
        return null;
    }
}
