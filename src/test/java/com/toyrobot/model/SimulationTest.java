package com.toyrobot.model;

import com.toyrobot.enums.CardinalPoint;
import com.toyrobot.enums.RotationDirection;
import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.awt.*;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SimulationTest {
    private Simulation simulation;
    private Robot robot;
    private GridBoard gridBoard;

    @Mock
    private Robot robotMock;

    @Mock
    private Appender mockAppender;
    @Captor
    private ArgumentCaptor<LoggingEvent> captorLoggingEvent;

    @Before
    public void setUp() throws Exception {
        this.gridBoard = new GridBoard(4,4);
        simulation = new Simulation(gridBoard, robotMock);
    }

    @Test
    public void robot() {
        assertThat(simulation, is(this.robot));

    }

    @Test
    public void gridBoard() {
        assertThat(gridBoard, is(this.gridBoard));
    }

    @Test
    public void placeRobot() {
        simulation.placeRobot(new Point(3, 2), CardinalPoint.NORTH);
        assertThat(robot.getX(), is(3));
        assertThat(robot.getY(), is(2));
        assertThat(robot.cardinalPoint(),is(CardinalPoint.NORTH));
    }

    //fix me ..
    @Test
    public void report() {
        Logger root =  Logger.getRootLogger();
        root.addAppender(mockAppender);
        root.setLevel(Level.INFO);
        robot.place(new Point(2,2), CardinalPoint.WEST);

        simulation.report();

        LoggingEvent loggingEvent = captorLoggingEvent.getAllValues().get(0);
        assertThat(loggingEvent.getMessage(), is("2,2,WEST"));
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
        assertFalse(robotMock.move());
    }

    @Test
    public void moveRobot_shouldReturnFalseWhenNextCoordinateIsNotValid() {
        when(robotMock.nextMoveCoordinates()).thenReturn(new Point(-1,0));
        assertFalse(robotMock.move());
    }

}