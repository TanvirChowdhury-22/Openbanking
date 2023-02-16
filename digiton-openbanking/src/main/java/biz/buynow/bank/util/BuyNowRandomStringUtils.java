package biz.buynow.bank.util;

import java.util.Random;

public class BuyNowRandomStringUtils {

    public static final String EMPTY = "";
    public static final String EMPTY_BOOLEAN_FIELDS = "Both fields can not be false";

    public static String random(final int length, final boolean letters, final boolean numbers) {
        int zero = '0';
        int nine = '9';
        int upper_a = 'A';
        int lower_z = 'z';

        /*
         * We are using a seed here as System.currentTimeMillis(), because it will
         * increase the randomness of the Number.
         */
        Random random = new Random(System.currentTimeMillis());
        int leftLimit = 0;
        int rightLimit = 0;

        if (length == 0) {
            return EMPTY;
        } else if (length < 0) {
            throw new IllegalArgumentException("Requested random string length " + length + " is less than 0.");
        }

        if (letters && numbers) {
            leftLimit = zero;
            rightLimit = lower_z;
        } else if (!letters && numbers) {
            leftLimit = zero;
            rightLimit = nine;
        } else if (letters && !numbers) {
            leftLimit = upper_a;
            rightLimit = lower_z;
        } else {
            throw new IllegalArgumentException(EMPTY_BOOLEAN_FIELDS);
        }

        String generatedString;

        // we are using the filter here to avoid ASCII chars of (58-64) & (91-96) as they are symbols, not alphabets or numbers
        try {
            generatedString = random.ints(leftLimit, rightLimit + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(length)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        }

        return generatedString;
    }
}
