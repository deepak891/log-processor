package com.qunatcast.service;

import java.time.LocalDate;
import java.util.List;

public interface MessageService<T> {

    public T createMessage(T message);

    public List<String> getActiveCookies(LocalDate key);

}
