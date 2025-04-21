package com.stdu.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    // 直接获取当前日期的格式化字符串
    public static String getCurrentDate() {
        return LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE); // 内置格式直接匹配 yyyy-MM-dd
    }

    // 或者显式指定格式
    public static String getCurrentDateCustom() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.now().format(formatter);
    }
}