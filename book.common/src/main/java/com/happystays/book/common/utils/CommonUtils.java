package com.happystays.book.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class CommonUtils {

    public static final String DATE_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DATE_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private CommonUtils(){}

    public static Date formatDate(String date, String format) {
        try {
            DateFormat sourceFormat = new SimpleDateFormat(format);
            return sourceFormat.parse(date);
        } catch (Exception e) {
            log.error("error while formatting date");
        }
        return null;
    }
}
