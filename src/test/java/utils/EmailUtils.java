package utils;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class EmailUtils {
    private static final String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_.-";
    private static final String DOMAIN_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.-";
    private static final String TLD_CHARS = "abcdefghijklmnopqrstuvwxyz";

    private static final Random random = new Random();

    public static String generateEmail() {
        String localPart = generatePart(CHARS, 5, 12);
        String domain = generatePart(DOMAIN_CHARS, 3, 10);
        String tld = generatePart(TLD_CHARS, 2, 5); // как по паттерну — от 2 до 5 символов

        return localPart + "@" + domain + "." + tld;
    }

    private static String generatePart(String chars, int minLength, int maxLength) {
        int length = minLength + random.nextInt(maxLength - minLength + 1);
        StringBuilder part = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            part.append(chars.charAt(random.nextInt(chars.length())));
        }
        return part.toString();
    }
}
