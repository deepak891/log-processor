package com.qunatcast.loader.impl;

import com.qunatcast.loader.MessageLoader;
import com.qunatcast.model.LogMessage;
import com.qunatcast.model.Message;
import com.qunatcast.utils.FileLoaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.concurrent.BlockingQueue;

import static com.qunatcast.model.MessageType.COOKIE_LOG_MESSAGE;

public class MessageLoaderService implements MessageLoader {
    private static final Logger LOG = LoggerFactory.getLogger(MessageLoaderService.class);

    private final String fileName;
    private final BlockingQueue<Message<LocalDateTime, LogMessage>> blockingQueue;

    public MessageLoaderService(String fileName,
                                BlockingQueue<Message<LocalDateTime, LogMessage>> blockingQueue) {
        this.fileName = fileName;
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void loadMessage() throws IOException {
        Instant start = Instant.now();
        FileLoaderUtil.loadMessages(fileName)
                .forEach(this::publishMessageToQueue);
        Instant finish = Instant.now();
        LOG.info("Message published to queue, total time taken :: {} ms", Duration.between(start, finish).toMillis());
    }

    private void publishMessageToQueue(LogMessage message) {
        try {
            blockingQueue.put(new Message<>(message.getDateTime(), message, COOKIE_LOG_MESSAGE));
        } catch (InterruptedException e) {
            LOG.error("Error message publishing to queue {}", e.getMessage());
        }
    }

}
