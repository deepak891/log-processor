package com.qunatcast;

import com.qunatcast.loader.MessageLoader;
import com.qunatcast.loader.impl.MessageLoaderService;
import com.qunatcast.model.LogMessage;
import com.qunatcast.model.Message;
import com.qunatcast.processor.MessageProcessor;
import com.qunatcast.processor.impl.LogMessageProcessor;
import com.qunatcast.service.MessageService;
import com.qunatcast.service.MessageServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LogProcessorMain {
    private static final Logger LOG = LoggerFactory.getLogger(LogProcessorMain.class);

    public static void main(String[] args) throws IOException {
        LOG.info("App starts...");
        if (checkArguments(args)) {
            LOG.info("FileName : {}, date: {} ", args[1], args[3]);
            final BlockingQueue<Message<LocalDateTime, LogMessage>> blockingQueue = new LinkedBlockingQueue<>(Integer.MAX_VALUE);
            MessageLoader loaderService = new MessageLoaderService(args[1], blockingQueue);
            loaderService.loadMessage();
            MessageService messageService = new MessageServiceImpl();
            MessageProcessor<LocalDate, String> messageProcessor = new LogMessageProcessor(blockingQueue, messageService);
            messageProcessor.process();
            LOG.info("Active cookies are :: {}", messageService.getActiveCookies(LocalDate.parse(args[3])));
        }
    }

    private static boolean checkArguments(String[] args) {
        if (args.length < 3) {
            LOG.error("Supplied wrong number of argument, please enter in following format '-f cookie_log.csv -d 2018-12-09'");
            return false;
        }
        return true;
    }

    private static void sleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            LOG.warn("Error while sleeping for waiting new created topics!!");
        }
    }

}
