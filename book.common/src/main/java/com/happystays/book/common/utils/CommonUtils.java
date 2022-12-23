package com.happystays.book.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {

    public static final String DATE_YYYY_MM_DD = "yyyy-MM-dd";

    public static final String DATE_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";


    public static Date formatDate(String Date, String format) {
        try {
            DateFormat sourceFormat = new SimpleDateFormat(format);
            return sourceFormat.parse(Date);
        } catch (Exception e) {
            System.out.println("error while formatting date");
        }
        return null;
    }

}
