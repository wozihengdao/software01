package com.stdu.util;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class HolidayChecker {
    public static boolean isHoliday(LocalDate date) {
        // 简单实现
        return date.getDayOfWeek() == DayOfWeek.SATURDAY
                || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }
}