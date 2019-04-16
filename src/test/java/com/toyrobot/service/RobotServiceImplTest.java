package com.toyrobot.service;

import com.toyrobot.model.Simulation;
import org.apache.log4j.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RobotServiceImplTest {
    private static final String APPENDER_NAME = "log4jRuleAppender";
    private static final Layout LAYOUT = new SimpleLayout();
    private Logger logger;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private RobotService robotService;

    @Before
    public void setUp(){
        logger = Logger.getRootLogger();
        Appender appender = new WriterAppender(LAYOUT, outContent);
        appender.setName(APPENDER_NAME);
        logger.addAppender(appender);

        robotService = new RobotServiceImpl();
    }

    @After
    public void tearDown() {
        logger.removeAppender(APPENDER_NAME);
    }

    @Test
    public void createSimulation() throws Exception{
        Simulation simulation = robotService.createSimulation(4,4);

        assertNotNull(simulation.robot());
        assertNotNull(simulation.gridBoard());
        assertThat(simulation.gridBoard().getHeight(), is(4));
        assertThat(simulation.gridBoard().getWidth(), is(4));

    }

    @Test (expected = IllegalArgumentException.class)
    public void createSimulation_shouldThrowErrorWhenNegativeWidthIsPassedIn() throws Exception{
        robotService.createSimulation(-4,4);
    }

    @Test (expected = IllegalArgumentException.class)
    public void createSimulation_shouldThrowErrorWhenNegativeHeightIsPassedIn() throws Exception{
        robotService.createSimulation(4,-4);
    }

    @Test
    public void IT_processCommandForJob() throws Exception{
        Simulation simulation = robotService.createSimulation(4,4);
        robotService.processCommandForJob("place 0,0,East", simulation);
        robotService.processCommandForJob("move", simulation);
        robotService.processCommandForJob("move", simulation);
        robotService.processCommandForJob("report", simulation);

        assertThat(outContent.toString(), containsString("2,0,EAST"));
    }


    @Test
    public void IT_processCommandForJob_shouldCorrectlyDisplayOutput() throws IOException {
        Simulation simulation = robotService.createSimulation(4,4);
        robotService.processCommandForJob("place 3,3,West", simulation);
        robotService.processCommandForJob("move", simulation);
        robotService.processCommandForJob("move", simulation);
        robotService.processCommandForJob("report", simulation);
        robotService.processCommandForJob("left", simulation);
        robotService.processCommandForJob("move", simulation);
        robotService.processCommandForJob("move", simulation);
        robotService.processCommandForJob("move", simulation);
        robotService.processCommandForJob("report", simulation);

        assertThat(outContent.toString(), containsString("1,3,WEST"));
        assertThat(outContent.toString(), containsString("1,0,SOUTH"));

    }

    @Test(expected = NullPointerException.class)
    public void processCommandForJob_shouldThrowExceptionWhenSimulationIsNull() {
        robotService.processCommandForJob("place 0,0,East", null);
    }

    @Test(expected = NullPointerException.class)
    public void processCommandForJob_shouldThrowExceptionWhenCommandIsNull() throws IOException{
        Simulation simulation = robotService.createSimulation(4,4);
        robotService.processCommandForJob(null, simulation);
    }

    @Test
    public void processCommandForJob_shouldReturnFalseWhenGivenUnknownCommand() throws IOException{
        Simulation simulation = robotService.createSimulation(4,4);
        boolean successfull = robotService.processCommandForJob("Fly", simulation);
        assertFalse(successfull);
    }

}