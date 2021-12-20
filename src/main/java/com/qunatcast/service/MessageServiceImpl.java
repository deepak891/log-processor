package com.qunatcast.service;

import com.qunatcast.model.CookieMessage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.counting;

public class MessageServiceImpl implements MessageService<CookieMessage> {
    private final Map<LocalDate, List<String>> cookiesMap = new ConcurrentHashMap<>();

    @Override
    public CookieMessage createMessage(CookieMessage message) {
        if (!cookiesMap.containsKey(message.getDateTime())) {
            List<String> cookies = new ArrayList<>();
            cookies.add(message.getCookie());
            cookiesMap.put(message.getDateTime(), cookies);
        } else {
            final List<String> cookies = cookiesMap.get(message.getDateTime());
            cookies.add(message.getCookie());
            cookiesMap.put(message.getDateTime(), cookies);
        }
        return message;
    }

    @Override
    public List<String> getActiveCookies(LocalDate key) {
        final List<String> logMessages = cookiesMap.get(key);
        if (Objects.isNull(cookiesMap.get(key)))
            return null;

        final Map<String, Long> cookiesMap = getCookieCountMap(logMessages);
        return findActiveCookies(cookiesMap, getMaxCount(cookiesMap));
    }

    List<String> findActiveCookies(final Map<String, Long> cookiesMap, final Long activeCookieCount) {
        List<String> activeCookies = new ArrayList<>();

        for (Map.Entry<String, Long> element : cookiesMap.entrySet()) {
            if (Objects.equals(element.getValue(), activeCookieCount)) {
                activeCookies.add(element.getKey());
            }
        }
        return activeCookies;
    }

    Long getMaxCount(final Map<String, Long> cookiesMap) {
        return cookiesMap.entrySet()
                .stream().max(comparingByValue())
                .map(Map.Entry::getValue)
                .orElse(1L);

    }

    Map<String, Long> getCookieCountMap(final List<String> logMessages) {
        return logMessages.stream()
                .collect(Collectors.groupingBy(e -> e, counting()));
    }

}
