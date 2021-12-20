package com.qunatcast.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.qunatcast.utils.FileLoaderUtil.loadMessages;
import static com.qunatcast.utils.LogConstant.FILE_PATH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FileLoaderUtilTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void shouldLoadFile() throws IOException {
        assertNotNull(loadMessages(FILE_PATH + "logfile.csv"));
        assertEquals(8, loadMessages(FILE_PATH + "logfile.csv").size());
    }

}