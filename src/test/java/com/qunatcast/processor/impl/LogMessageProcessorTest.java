package com.qunatcast.processor.impl;

import com.qunatcast.model.LogMessage;
import com.qunatcast.model.Message;
import com.qunatcast.processor.MessageProcessor;
import com.qunatcast.service.MessageService;
import com.qunatcast.service.MessageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static com.qunatcast.model.MessageType.COOKIE_LOG_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LogMessageProcessorTest {

    private MessageProcessor<LocalDate, String> messageProcessor;
    private final BlockingQueue<Message<LocalDateTime, LogMessage>> blockingQueue = new LinkedBlockingQueue<>(100);
    private final MessageService messageService = new MessageServiceImpl();

    @BeforeEach
    void setUp() throws Exception {
        messageProcessor = new LogMessageProcessor(blockingQueue, messageService);
        blockingQueue.put(createMockMessage());
    }

    @Test
    void process() {
        messageProcessor.process();
        assertEquals(List.of("AtY0laUfhglK3lC7"), messageService.getActiveCookies(LocalDate.parse("2018-12-09")));
    }

    private Message<LocalDateTime, LogMessage> createMockMessage() {
        return new Message(LocalDate.parse("2018-12-09"), new LogMessage("AtY0laUfhglK3lC7",
                LocalDateTime.of(LocalDate.parse("2018-12-09"),
                        LocalTime.now())), COOKIE_LOG_MESSAGE);
    }

}