package com.qunatcast.model;

import java.time.LocalDateTime;

/**
 * The model represent generic log message
 * before we have identified what kind of message it is
 */
public class LogMessage {
    private final String cookie;
    private final LocalDateTime dateTime;

    public LogMessage(String cookie, LocalDateTime dateTime) {
        this.cookie = cookie;
        this.dateTime = dateTime;
    }

    public String getCookie() {
        return cookie;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

}
