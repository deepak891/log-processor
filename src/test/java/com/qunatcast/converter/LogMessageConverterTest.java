package com.qunatcast.converter;

import com.qunatcast.model.BackMessage;
import com.qunatcast.model.CookieMessage;
import com.qunatcast.utils.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LogMessageConverterTest {

    private MessageConverter messageConverter;
    @BeforeEach
    void setUp() {
        messageConverter = new LogMessageConverter();
    }

    @Test
    void convert() {
        final BackMessage message = messageConverter.convert(TestUtil.createMessage());
        assertEquals("AtY0laUfhglK3lC7",((CookieMessage) message).getCookie());
    }
    
}