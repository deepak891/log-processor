package com.qunatcast.utils;


import com.qunatcast.model.LogMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FileLoaderUtil {
    private static final Logger LOG = LoggerFactory.getLogger(FileLoaderUtil.class);

    public static final Pattern PATTERN = Pattern.compile(",");
    public static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            .appendPattern("xxx")
            .toFormatter();

    public static List<LogMessage> loadMessages(final String path) throws IOException {
        return Files.lines(Path.of(path))
                .skip(1).map(line -> {
                    String[] arr = PATTERN.split(line);
                    return mapToCookieMessage(arr);
                }).collect(Collectors.toList());
    }

    private static LogMessage mapToCookieMessage(String[] arr) {
        return new LogMessage(
                arr[0],
                LocalDateTime.parse(arr[1], FORMATTER));
    }
}
