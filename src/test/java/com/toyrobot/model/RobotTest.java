package com.toyrobot.model;

import com.toyrobot.enums.CardinalPoint;
import com.toyrobot.enums.RotationDirection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RobotTest {
    private Robot robot;

    @Before
    public void setUp() throws Exception {
        robot = new Robot();
    }

    @After
    public void tearDown() {
        robot = null;
    }

    @Test
    public void placedOnBoard() {
        robot.place(new Point(1,1), CardinalPoint.NORTH);
        assertThat(robot.placedOnBoard(), is(true));
    }

    @Test
    public void getX() {
        robot.place(new Point(1,1), CardinalPoint.NORTH);
        assertThat(robot.getX(), is(1));
    }

    @Test
    public void getY() {
        robot.place(new Point(1,1), CardinalPoint.NORTH);
        assertThat(robot.getY(), is(1));
    }

    @Test
    public void cardinalPoint() {
        robot.place(new Point(1,1), CardinalPoint.NORTH);
        assertThat(robot.cardinalPoint(), is(CardinalPoint.NORTH));
    }

    @Test
    public void nextMoveCoordinates() {
        robot.place(new Point(1,1), CardinalPoint.NORTH);
        assertThat(robot.nextMoveCoordinates(), is(new Point(1,2)));
    }

    @Test
    public void rotate() {
        robot.place(new Point(1,1), CardinalPoint.NORTH);
        robot.rotate(RotationDirection.LEFT);
        assertThat(robot.cardinalPoint(), is(CardinalPoint.WEST));

        robot.rotate(RotationDirection.RIGHT);
        assertThat(robot.cardinalPoint(), is(CardinalPoint.NORTH));
    }

    @Test
    public void move() {
        robot.place(new Point(1,1), CardinalPoint.NORTH);
        robot.move();
        assertThat(robot.getX(), is(1));
        assertThat(robot.getY(), is(2));
    }

    @Test
    public void move_shoulReturnFalseIfRobotHasNotYetBeenPlaced(){
        assertFalse(robot.move());
    }

    @Test
    public void place() {
        robot.place(new Point(2, 2), CardinalPoint.SOUTH);
        assertThat(robot.getX(),  is(2));
        assertThat(robot.getY(),  is(2));
        assertThat(robot.cardinalPoint(), is(CardinalPoint.SOUTH));

    }

    @Test
    public void hasBeenPlaced() {
        robot.place(new Point(1,1), CardinalPoint.NORTH);
        assertTrue(robot.hasBeenPlaced());
    }
}