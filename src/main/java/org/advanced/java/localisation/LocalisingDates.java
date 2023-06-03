package org.advanced.java.localisation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class LocalisingDates {
    public static void main(String[] args) {
        Locale locUS     = Locale.US;               // en_US
        Locale locFrench = new Locale("fr", "FR");  // fr_FR
        Locale locGerman = Locale.GERMANY;          // de_DE
        Locale locSweden = Locale.getDefault();
        
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);// 2022-01-06T13:25:11.229190500

        DateTimeFormatter dateMediumStyle    = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        // Using the United States locale (en_US)
        System.out.println(dateMediumStyle.withLocale(locUS).format(ldt));     // Jan 6, 2022
        // Using the French locale
        System.out.println(dateMediumStyle.withLocale(locFrench).format(ldt)); // 6 janv. 2022
        // Using the Default (Sweden) locale
        System.out.println("Sweden: " + dateMediumStyle.withLocale(locSweden).format(ldt));  // 2 juni 2023

        DateTimeFormatter timeShortStyle     = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        // Using the United States locale (en_US)
        System.out.println(timeShortStyle.withLocale(locUS).format(ldt));      // 1:49 PM
        // Using the German locale
        System.out.println(timeShortStyle.withLocale(locGerman).format(ldt));  // 13:49
        // Using the Default (Sweden) locale
        System.out.println("Sweden: " + timeShortStyle.withLocale(locSweden).format(ldt));      // 21:47

        DateTimeFormatter datetimeShortStyle = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        DateTimeFormatter dateTimeMediumStyle = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        DateTimeFormatter dateTimeLongStyle = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        DateTimeFormatter dateTimeFullStyle = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
        // Using the default locale (en_IE)
        System.out.println(datetimeShortStyle.format(ldt));                   // 06/01/2022, 13:34
        // Using the United States locale (en_US)
        System.out.println(datetimeShortStyle.withLocale(locUS).format(ldt)); // 1/6/22, 1:34 PM
        // Using the Default (Sweden) locale
        System.out.println("Sweden: " + datetimeShortStyle.withLocale(locSweden).format(ldt));      // 2023-06-02 21:47
        System.out.println();
        System.out.println(dateMediumStyle.format(ldt));            // 2 juni 2023
//        System.out.println(dateTimeLongStyle.format(ldt));        // DateTimeException (Why ?)
//        System.out.println(dateTimeFullStyle.format(ldt));        // DateTimeException (Why ?)

    }
}
