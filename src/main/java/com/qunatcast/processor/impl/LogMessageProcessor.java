package com.qunatcast.processor.impl;

import com.qunatcast.converter.LogMessageConverter;
import com.qunatcast.converter.MessageConverter;
import com.qunatcast.model.BackMessage;
import com.qunatcast.model.LogMessage;
import com.qunatcast.model.Message;
import com.qunatcast.model.MessageType;
import com.qunatcast.processor.MessageProcessor;
import com.qunatcast.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchProviderException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;

import static com.qunatcast.model.MessageType.COOKIE_LOG_MESSAGE;
import static java.time.Duration.between;
import static java.time.Instant.now;


public class LogMessageProcessor implements MessageProcessor<LocalDate, String> {
    private static final Logger LOG = LoggerFactory.getLogger(LogMessageProcessor.class);

    private final BlockingQueue<Message<LocalDateTime, LogMessage>> blockingQueue;
    private final MessageService<BackMessage> messageService;
    private final Map<MessageType, MessageConverter<LocalDateTime, LogMessage, BackMessage>> messageConverterMap = initializeConverterMap();

    public LogMessageProcessor(BlockingQueue<Message<LocalDateTime, LogMessage>> blockingQueue,
                               MessageService<BackMessage> messageService) {
        this.blockingQueue = blockingQueue;
        this.messageService = messageService;
    }

    private Map<MessageType, MessageConverter<LocalDateTime, LogMessage, BackMessage>> initializeConverterMap() {
        Map<MessageType, MessageConverter<LocalDateTime, LogMessage, BackMessage>> messageConverterMap = new EnumMap<>(MessageType.class);
        messageConverterMap.put(COOKIE_LOG_MESSAGE, new LogMessageConverter());
        return messageConverterMap;
    }

    @Override
    public void process() {
        Instant start = now();
        while (!blockingQueue.isEmpty()) {
            try {
                final Message<LocalDateTime, LogMessage> message = blockingQueue.take();
                Optional.ofNullable(messageConverterMap.get(message.getMessageType()))
                        .map(messageConverter -> messageConverter.convert(message))
                        .map(messageService::createMessage)
                        .orElseThrow(() -> new NoSuchProviderException("Converter Now found Exception"));
            } catch (Exception e) {
                LOG.error("Error processing message {}", e.getMessage());
            }
        }
        Instant finish = now();
        LOG.info("Message consumed from queue time taken {} ms", between(start, finish).toMillis());
    }

}
