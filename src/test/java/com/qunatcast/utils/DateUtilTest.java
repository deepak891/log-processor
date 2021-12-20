package com.qunatcast.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateUtilTest {

    private DateUtil dateUtil ;
    @BeforeEach
    void setUp() {
        dateUtil = new DateUtil();
    }

    @Test
    void toDate() {
        final LocalDate localDate = DateUtil.toDate(LocalDateTime.of(LocalDate.parse("2018-12-09"), LocalTime.now()));
        assertEquals(LocalDate.parse("2018-12-09"), localDate);
    }
}