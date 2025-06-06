package utils;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class NumbersUtils {
    public Integer strToInt(String text) {
        text = text.replaceAll("[^0-9]", "");

        return Integer.valueOf(text);
    }

    public Double strToDouble(String text) {
        double price;
        try {
            String rawText = text.replaceAll("[^\\d.]", ""); // удаляет $, пробелы, буквы и т.д.
            if (rawText.isEmpty()) {
                throw new NumberFormatException("Empty numeric string");
            }
            price = Double.parseDouble(rawText);
        } catch (NumberFormatException e) {
            price = 0.0;
        }

        return price;
    }
    public Integer getRandomNumber(int from, int to) {
        Random random = new Random();

        return from + random.nextInt(to - from + 1);
    }

    public String generate10DigitNumber() {
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            sb.append(getRandomNumber(0, 9));
        }
        return sb.toString();
    }
}
