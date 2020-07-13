package com.zxu.demo.java8.classsics_demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 */
public class DDateUtil {
    private static final ThreadLocal<SimpleDateFormat> DATETIME_FORMATTER = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    public static final ThreadLocal<SimpleDateFormat> DATETIME_EDIFORMAT = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy年MM月dd日 HH:mm"));

    public static String formYMdHms(Date date){
        return DATETIME_FORMATTER.get().format(date);
    }
}
