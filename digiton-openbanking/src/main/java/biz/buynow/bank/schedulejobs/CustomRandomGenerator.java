package biz.buynow.bank.schedulejobs;

import biz.buynow.bank.util.BuyNowRandomStringUtils;

public class CustomRandomGenerator {
    public static String generateRandomTransactionId() {
        boolean useLetters = true;
        boolean useNumbers = true;
        int transactionIdLength = 12;
        String transactionId = BuyNowRandomStringUtils.random(transactionIdLength, useLetters, useNumbers).toUpperCase();
        return transactionId;
    }

    public static String generateRandomUniqueId() {
        int uniqueIdLength = 12;
        boolean useLettersInId = true;
        boolean useNumbersInId = true;
        String uniqueId = BuyNowRandomStringUtils.random(uniqueIdLength, useLettersInId, useNumbersInId);
        return uniqueId;
    }
}
