package com.stdu.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class DateIntervalCalculator {

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // 日期解析方法
    public static LocalDate parseDate(String dateStr) throws DateTimeParseException {
        return LocalDate.parse(dateStr, DATE_FORMATTER);
    }

    // 方式1：计算总天数间隔
    public static long dayDifference(LocalDate start, LocalDate end) {
        long days = ChronoUnit.DAYS.between(start, end);
         return days;
    }
    public static long getDaysFromToday(String dateStr) throws DateTimeParseException {
        // 创建日期格式器
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 解析输入日期
        LocalDate inputDate = LocalDate.parse(dateStr, formatter);

        // 获取当前日期（系统默认时区）
        LocalDate today = LocalDate.now();

        // 计算绝对间隔天数
        return Math.abs(ChronoUnit.DAYS.between(inputDate, today));
    }

}