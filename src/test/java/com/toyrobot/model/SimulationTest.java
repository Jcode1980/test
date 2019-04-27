package com.toyrobot.model;

import com.toyrobot.enums.CardinalPoint;
import org.apache.log4j.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.awt.Point;
import java.io.ByteArrayOutputStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SimulationTest {
    private static final String APPENDER_NAME = "log4jRuleAppender";
    private static final Layout LAYOUT = new SimpleLayout();
    private Logger logger;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private Simulation simulation;
    @Mock
    private GridBoard gridBoardMock;
    @Mock
    private Robot robotMock;

    @Before
    public void setUp() {
        logger = Logger.getRootLogger();
        Appender appender = new WriterAppender(LAYOUT, outContent);
        appender.setName(APPENDER_NAME);
        logger.addAppender(appender);

        simulation = new Simulation(gridBoardMock, robotMock);

        when(robotMock.getX()).thenReturn(2);
        when(robotMock.getY()).thenReturn(2);
        when(robotMock.cardinalPoint()).thenReturn(CardinalPoint.NORTH);

        when(gridBoardMock.getWidth()).thenReturn(4);
        when(gridBoardMock.getHeight()).thenReturn(4);
    }

    @After
    public void tearDown() {
        logger.removeAppender(APPENDER_NAME);
    }

    @Test
    public void robot() {
        assertThat(simulation.robot(), is(this.robotMock));
    }

    @Test
    public void gridBoard() {
        assertThat(simulation.gridBoard(), is(gridBoardMock));
    }

    @Test
    public void placeRobot() {
        Point point = new Point(3, 2);
        simulation.placeRobot(point, CardinalPoint.NORTH);
        verify(robotMock).place(point, CardinalPoint.NORTH);
    }

    @Test
    public void placeRobot_shouldReturnFalseIfGivenInvalidPoint() {
        Point point = new Point(5, 5);
        assertFalse(simulation.placeRobot(point, CardinalPoint.NORTH));
    }

    @Test
    public void report() {
        System.out.println("placed robot:" + simulation.placeRobot(new Point(2,2), CardinalPoint.NORTH));
        when(robotMock.hasBeenPlaced()).thenReturn(true);
        simulation.report();
        assertThat(outContent.toString(), containsString("2,2,NORTH"));
    }

    @Test
    public void pointIsValid() {
        assertFalse(simulation.pointIsValid(new Point( 4,4)));
        assertTrue(simulation.pointIsValid(new Point( 3,3)));
    }

    @Test
    public void moveRobot(){
        when(robotMock.nextMoveCoordinates()).thenReturn(new Point(3,3));
        when(robotMock.hasBeenPlaced()).thenReturn(true);

        assertTrue(simulation.moveRobot());
        verify(robotMock).move();
    }

    @Test
    public void moveRobot_shouldReturnFalseRobotHasNotYetBeenPlacedOnBoard() {
        when(robotMock.hasBeenPlaced()).thenReturn(false);
        assertFalse(simulation.moveRobot());
    }

    @Test
    public void moveRobot_shouldReturnFalseWhenNextCoordinateIsNotValid() {
        when(robotMock.hasBeenPlaced()).thenReturn(true);
        when(robotMock.nextMoveCoordinates()).thenReturn(new Point(-1,0));
        assertFalse(simulation.moveRobot());
    }



}