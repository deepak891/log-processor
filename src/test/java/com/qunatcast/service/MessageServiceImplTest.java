package com.qunatcast.service;

import com.qunatcast.model.CookieMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MessageServiceImplTest {

    private MessageServiceImpl messageService;
    private CookieMessage cookieMessage;

    @BeforeEach
    void setUp() {
        messageService = new MessageServiceImpl();
        cookieMessage = new CookieMessage("AtY0laUfhglK3lC7", LocalDate.parse("2018-12-09"));
    }

    @Test
    void createMessage() {
        messageService.createMessage(cookieMessage);
        final List cookies = messageService.getActiveCookies(LocalDate.parse("2018-12-09"));
        assertEquals(List.of("AtY0laUfhglK3lC7"), cookies);
    }

    @Test
    void getActiveCookies() {
        messageService.createMessage(cookieMessage);
        final List cookies = messageService.getActiveCookies(LocalDate.parse("2018-12-09"));
        assertEquals(List.of("AtY0laUfhglK3lC7"), cookies);
    }

    @Test
    void shouldReturnNullIfNoCookiesFound() {
        messageService.createMessage(cookieMessage);
        final List cookies = messageService.getActiveCookies(LocalDate.parse("2018-10-09"));
        assertNull(cookies);
    }

    @Test
    void findActiveCookies() {
        final List<String> activeCookies = messageService.findActiveCookies(createDummyCookieMap(1L), 2L);
        assertEquals(List.of("AtY0laUfhglK3lC7"), activeCookies);
    }

    @Test
    void shouldReturnAllIfMultipleCookiesFound() {
        final List<String> activeCookies = messageService.findActiveCookies(createDummyCookieMap(2L), 2L);
        assertEquals(List.of("AtY0laUfhglK3lC7", "AtY0laUfhglK3lC6"), activeCookies);
    }

    @Test
    void getMaxCount() {
        final Long maxCount = messageService.getMaxCount(createDummyCookieMap(2L));
        assertEquals(maxCount.longValue(), 2L);
    }

    @Test
    void getCookieCountMap() {
        final Map<String, Long> cookieCountMap = messageService.getCookieCountMap(
                List.of("AtY0laUfhglK3lC7", "AtY0laUfhglK3lC6", "AtY0laUfhglK3lC7", "AtY0laUfhglK3lC6"));
        assertEquals(createDummyCookieMap(2L), cookieCountMap);
    }

    private Map<String, Long> createDummyCookieMap(Long count) {
        Map<String, Long> cookieMap = new HashMap<>();
        cookieMap.put("AtY0laUfhglK3lC7", 2L);
        cookieMap.put("AtY0laUfhglK3lC6", count);
        return cookieMap;
    }

}