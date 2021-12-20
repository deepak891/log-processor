package com.qunatcast.loader.impl;

import com.qunatcast.loader.MessageLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static com.qunatcast.utils.TestUtil.FILE_PATH;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageLoaderServiceTest {

    private BlockingQueue blockingQueue = new LinkedBlockingQueue<>(10);
    private MessageLoader messageLoaderService;

    @BeforeEach
    void setUp() {
        messageLoaderService = new MessageLoaderService(FILE_PATH + "logfile.csv", blockingQueue);
    }

    @Test
    void loadMessage() throws Exception {
        messageLoaderService.loadMessage();
        assertEquals(8, blockingQueue.size());
    }
}