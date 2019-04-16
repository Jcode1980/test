package com.toyrobot;

import com.toyrobot.service.RobotServiceImpl;
import org.apache.log4j.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class ClientTest {
    private static final String TEST_INSTRUCTIONS_FILE="src/main/resources/test/TestInstructionsFile.txt";
    private static final String TEST_INSTRUCTIONS_OFB_FILE="src/main/resources/test/TestInstructionsOutOfBounds.txt";
    private static final String TEST_INSTRUCTIONS_BOUNDARIES_FILE="src/main/resources/test/TestInstructionsBoundariesFile.txt";
    private static final String TEST_INSTRUCTIONS_INVALID_COMMAND_IGNORED_FILE="src/main/resources/test/TestInstructionsInvalidInstructionsIgnoredFile.txt";

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
    public void startSimulation() throws IOException {
        Path testInstructionsPath = Paths.get(TEST_INSTRUCTIONS_FILE);
        Client client = new Client(new RobotServiceImpl(), 4, 4, new FileInputStream(testInstructionsPath.toFile()));
        client.startSimulation();
        assertThat(outContent.toString(), containsString("2,1,EAST"));
        assertThat(outContent.toString(), containsString("2,3,NORTH"));

    }


    @Test
    public void IT_startSimulation_shouldHitBoundaryAndPrintsNonValidMoveString() throws IOException {
        Path outOfBoundsFilePath = Paths.get(TEST_INSTRUCTIONS_OFB_FILE);
        Client client = new Client(new RobotServiceImpl(), 4, 4, new FileInputStream(outOfBoundsFilePath.toFile()));
        client.startSimulation();

        assertThat(outContent.toString(), containsString("Next move is not valid"));

    }

    @Test
    public void IT_processCommandFile_moveAroundBoundariesAndEndSimulationNormally() throws IOException {
        Path testBoundariesFilePath = Paths.get(TEST_INSTRUCTIONS_BOUNDARIES_FILE);
        Client client = new Client(new RobotServiceImpl(),4, 4, new FileInputStream(testBoundariesFilePath.toFile()));
        client.startSimulation();

        assertThat(outContent.toString(), containsString("3,0,EAST"));
        assertThat(outContent.toString(), containsString("3,3,NORTH"));
        assertThat(outContent.toString(), containsString("0,3,WEST"));
        assertThat(outContent.toString(), containsString("0,0,SOUTH"));
    }


    @Test
    public void IT_processCommandFile_invalidCommandShouldBeIgnored() throws IOException {
        Path testBoundariesFilePath = Paths.get(TEST_INSTRUCTIONS_INVALID_COMMAND_IGNORED_FILE);
        Client client = new Client(new RobotServiceImpl(),4, 4, new FileInputStream(testBoundariesFilePath.toFile()));
        client.startSimulation();

        assertThat(outContent.toString(), containsString("Please place robot before trying to rotate."));
        assertThat(outContent.toString(), containsString("Robot has not yet been placed"));
        assertThat(outContent.toString(), containsString("unkown command :dodge"));
        assertThat(outContent.toString(), containsString("2,3,NORTH"));

    }



}