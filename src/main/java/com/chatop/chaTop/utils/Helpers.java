package com.chatop.chaTop.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Helpers class provides utility methods for format date.
 */
public class Helpers {
    /**
     * Format date and return the String result.
     *
     * @param date The date to format.
     * @return The formatted date.
     */
    public String formatDate(Date date) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
        return formater.format(date);
    }
}
