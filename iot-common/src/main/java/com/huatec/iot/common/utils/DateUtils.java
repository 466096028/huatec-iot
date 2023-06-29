package com.huatec.iot.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * @program: huatec-iot-api
 * @description: 时间工具类
 * @author: jiangtaohou
 * @create: 2023-05-05 15:03
 **/
public class DateUtils {

    private static final ZoneOffset ZONE_OFFSET = ZoneOffset.ofHours(8);
    public static DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static String YYYYMMDD = "yyyyMMdd";

    public static String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";

    public static String YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static Long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public static String getCurrentDateTime() {
        return getDateToString(System.currentTimeMillis());
    }

    /**
     * 将日期转化为默认的格式显示
     */
    public static String getDateToString(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat(YYYYMMDDHHMMSS);
        return sf.format(d);
    }

    /**
     * 将日期转化为默认的格式显示
     */
    public static String getDateToString1(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat(YYYYMMDDHHMM);
        return sf.format(d);
    }
    /**
     * 将日期转化为默认的格式显示
     */
    public static String getDateToString2(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat(YYYY_MM_DD);
        return sf.format(d);
    }

    /**
     * 将日期转化为默认的格式显示
     * @param date Date实例
     * @return 2023-05-06
     */
    public static String dateToString(Date date) {
        return dateToString(date, YYYYMMDD);
    }


    /**
     * 将日期转化为默认的格式显示
     * @param date Date实例
     * @return 2023-05-06
     */
    public static String dateToString1(Date date) {
        return dateToString(date, YYYYMMDDHHMMSS);
    }
    /**
     * @param date Date实例
     * @param format yyyyMMdd
     * @return
     */
    public static String dateToString(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.format(date);
    }

    public static LocalDate parseDate(String date){
        return LocalDate.parse(date, yyyyMMdd);
    }

    public static long tsMin(LocalDate localDate) {
        return localDate.atTime(LocalTime.MIN).toInstant(ZONE_OFFSET).toEpochMilli();
    }

    public static long tsMax(LocalDate localDate) {
        return localDate.atTime(LocalTime.MAX).toInstant(ZONE_OFFSET).toEpochMilli();
    }

    public static void main(String[] args) {
        System.out.println(stringToTimeStamp("2023-01-01 01:01:01"));
    }

    public static int getDays(String start, String end) {
        LocalDate startDate = parseDate(start);
        LocalDate endDate = parseDate(end);
        int days = Long.valueOf(DAYS.between(startDate, endDate)).intValue();
        return days + 1;
    }

    public static long timestampMin(String date) {
        LocalDate localDate = parseDate(date);
        return tsMin(localDate);
    }

    public static long timestampMax(String date) {
        LocalDate localDate = parseDate(date);
        return tsMax(localDate);
    }

    /**
     * 将日期字符串转为时间戳
     * @param date
     * @return
     */
    public static long stringToTimeStamp(String date){
        long timeStemp = 0;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYYYMMDDHHMMSS);
            Date parse = simpleDateFormat.parse(date);
            timeStemp = parse.getTime();
        } catch (Exception e) {
        }

        return timeStemp;
    }

}
