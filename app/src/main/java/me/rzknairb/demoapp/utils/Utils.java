package me.rzknairb.demoapp.utils;

import android.content.Intent;
import android.net.Uri;
import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Utils {

    public static String generateCategoryCode(String category) {
        return category.trim().replace(" ", "-");
    }
    public static Date randomDate() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.set(Calendar.MONTH, randBetween(0,11));
        calendar.set(Calendar.YEAR, 2019);
        calendar.set(Calendar.DAY_OF_MONTH, randBetween(1,29));
        return calendar.getTime();
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

    public static String getDayMonth(Date date) {
        String dayOfTheWeek = (String) DateFormat.format("EEEE", date); // Thursday
        String day          = (String) DateFormat.format("dd",   date); // 20
        String monthString  = (String) DateFormat.format("MMM",  date); // Jun
        String monthNumber  = (String) DateFormat.format("MM",   date); // 06
        String year         = (String) DateFormat.format("yyyy", date); // 2013

        return String.format("%s %s", monthString, day);
    }

    public static Intent sendEmailAction(String email, String username) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + email));
        return Intent.createChooser(emailIntent, "Hello dear " + username);
    }

    public static Intent sendPhoneAction(String phoneNumber) {
        return new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null));
    }
}
