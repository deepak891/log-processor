package com.qunatcast.utils;

import com.qunatcast.model.LogMessage;
import com.qunatcast.model.Message;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.qunatcast.model.MessageType.COOKIE_LOG_MESSAGE;

public class TestUtil {
    public static String FILE_PATH = "src/main/resources/";

    public static Message createMessage() {
        return new Message(LocalDate.parse("2018-12-09"), new LogMessage("AtY0laUfhglK3lC7",
                LocalDateTime.of(LocalDate.parse("2018-12-09"),
                        LocalTime.now())), COOKIE_LOG_MESSAGE);
    }
}
