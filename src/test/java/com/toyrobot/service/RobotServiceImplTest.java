package com.toyrobot.service;

import com.toyrobot.enums.ActionType;
import com.toyrobot.model.GridBoard;
import com.toyrobot.model.Robot;
import com.toyrobot.model.Simulation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RobotServiceImplTest {
//    private Simulation simulation;
//    private Robot robot;
//    private GridBoard gridBoard;

    private RobotService robotService;

    @Before
    public void setUp(){
        robotService = new RobotServiceImpl();
    }

    @After
    public void tearDown() throws Exception {
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

        //FIXME .. need to do
    }


    @Test
    public void IT_processCommandForJob_shouldCorrectlyDisplayOutput() throws Exception{
        Simulation simulation = robotService.createSimulation(4,4);
        robotService.processCommandForJob("place 0,0,East", simulation);
        robotService.processCommandForJob("move", simulation);
        robotService.processCommandForJob("move", simulation);
        robotService.processCommandForJob("move", simulation);
        robotService.processCommandForJob("move", simulation);
        robotService.processCommandForJob("move", simulation);

        //FIXME .. need to validate

    }

    @Test(expected = NullPointerException.class)
    public void processCommandForJob_shouldThrowExceptionWhenSimulationIsNull() throws Exception{
        robotService.processCommandForJob("place 0,0,East", null);
    }

    @Test(expected = NullPointerException.class)
    public void processCommandForJob_shouldThrowExceptionWhenCommandIsNull() throws Exception{
        robotService.processCommandForJob(null, null);
    }

    @Test
    public void processCommandForJob_shouldReturnFalseWhenGivenUnknownCommand() throws Exception{
        Simulation simulation = robotService.createSimulation(4,4);
        boolean successfull = robotService.processCommandForJob("Fly", simulation);
        assertFalse(successfull);
    }




}