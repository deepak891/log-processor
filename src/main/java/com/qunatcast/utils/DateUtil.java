package com.qunatcast.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtil {

    public static LocalDate toDate(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();
    }
}
