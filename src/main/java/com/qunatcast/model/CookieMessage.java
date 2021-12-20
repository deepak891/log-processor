package com.qunatcast.model;

import java.time.LocalDate;

/**
 * Represents after the message is taken from queue
 * and converted to appropriate type
 */
public class CookieMessage implements BackMessage{
    private final String cookie;
    private final LocalDate dateTime;

    public CookieMessage(String cookie, LocalDate dateTime) {
        this.cookie = cookie;
        this.dateTime = dateTime;
    }

    public String getCookie() {
        return cookie;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

}
