package com.toyrobot;

import org.apache.log4j.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class ApplicationTest {
    private static final String TEST_INSTRUCTIONS_FILE="src/main/resources/test/TestInstructionsFile.txt";
    private static final String APPENDER_NAME = "log4jRuleAppender";
    private static final Layout LAYOUT = new SimpleLayout();

    private Logger logger;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        logger = Logger.getRootLogger();
        Appender appender = new WriterAppender(LAYOUT, outContent);
        appender.setName(APPENDER_NAME);
        logger.addAppender(appender);
    }

    @After
    public void tearDown() {
        logger.removeAppender(APPENDER_NAME);
    }


    @Test
    public void main_shouldOutputCorrectValue() throws IOException {
        Application.main(new String[]{TEST_INSTRUCTIONS_FILE});
        assertThat(outContent.toString(), containsString("2,1,EAST"));
        assertThat(outContent.toString(), containsString("2,3,NORTH"));

    }
}