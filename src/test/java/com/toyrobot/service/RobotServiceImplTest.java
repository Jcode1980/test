package com.toyrobot.service;

import com.toyrobot.model.GridBoard;
import com.toyrobot.model.Robot;
import com.toyrobot.model.Simulation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RobotServiceImplTest {
    private Simulation simulation;
    private Robot robot;
    private GridBoard gridBoard;

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createSimulation() {
        this.robot = new Robot();
        this.gridBoard = new GridBoard(4,4);
        Simulation simulation = new Simulation(this.gridBoard, this.robot);
        assertThat(simulation.robot(), is(this.robot));
        assertThat(simulation.gridBoard(), is(this.gridBoard));
    }

    @Test
    public void IT_processCommandForJob_shouldCorrectlyDisplayOutput() {
        this.robot = new Robot();
        this.gridBoard = new GridBoard(4,4);
        Simulation simulation = new Simulation(this.gridBoard, this.robot);
        simulation.


    }
}