package utils;

import lombok.experimental.UtilityClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

@UtilityClass
public class DateUtils {
    public String generateRandomDate() {
        LocalDate startDate = LocalDate.of(1970, 1, 1);
        LocalDate endDate = LocalDate.now();

        long daysBetween = endDate.toEpochDay() - startDate.toEpochDay();
        long randomDays = new Random().nextInt((int) daysBetween + 1);

        LocalDate randomDate = startDate.plusDays(randomDays);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);

        return randomDate.format(formatter);
    }

    public static String getMonth(String dateStr) {
        String[] parts = dateStr.split(" ");
        if (parts.length == 3) {
            return parts[1];
        }

        return null;
    }

    public static String getYear(String dateStr) {
        String[] parts = dateStr.split(" ");
        if (parts.length == 3) {
            return parts[2];
        }

        return null;
    }

    public static String reformatDate(String inputDate) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
        Date date;
        try {
            date = inputFormat.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }

        // Получаем полный месяц
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.ENGLISH);
        String fullMonth = monthFormat.format(date);

        // Получаем день месяца
        SimpleDateFormat dayFormat = new SimpleDateFormat("d", Locale.ENGLISH);
        int day = Integer.parseInt(dayFormat.format(date));
        String dayWithSuffix = getDayWithSuffix(day);

        // Получаем год
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.ENGLISH);
        String year = yearFormat.format(date);

        return String.format("%s %s, %s", fullMonth, dayWithSuffix, year);
    }

    public static String formatDate(String input) {
        // Входной формат: "01 June,2025"
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd MMMM,yyyy", Locale.ENGLISH);

        // Выходной формат: "01 Jun 2025"
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);

        Date date;
        try {
            date = inputFormat.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }

        return outputFormat.format(date);
    }

    private static String getDayWithSuffix(int day) {
        if (day >= 11 && day <= 13) {
            return day + "th";
        }
        switch (day % 10) {
            case 1:  return day + "st";
            case 2:  return day + "nd";
            case 3:  return day + "rd";
            default: return day + "th";
        }
    }
}
