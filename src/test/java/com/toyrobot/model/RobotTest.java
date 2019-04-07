package com.toyrobot.model;

import com.toyrobot.enums.CardinalPoint;
import com.toyrobot.enums.RotationDirection;
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
        robot.place(new Point(1,1), CardinalPoint.NORTH);
    }

    @Test
    public void placedOnBoard() {
        assertThat(robot.placedOnBoard(), is(true));
    }

    @Test
    public void getX() {
        assertThat(robot.getX(), is(1));
    }

    @Test
    public void getY() {
        assertThat(robot.getY(), is(1));
    }

    @Test
    public void cardinalPoint() {
        assertThat(robot.cardinalPoint(), is(CardinalPoint.NORTH));
    }

    @Test
    public void nextMoveCoordinates() {
        assertThat(robot.nextMoveCoordinates(), is(new Point(1,2)));
    }

    @Test
    public void rotate() {
        robot.rotate(RotationDirection.LEFT);
        assertThat(robot.cardinalPoint(), is(CardinalPoint.WEST));

        robot.rotate(RotationDirection.RIGHT);
        assertThat(robot.cardinalPoint(), is(CardinalPoint.NORTH));
    }

    @Test
    public void move() {
        robot.move();
        assertThat(robot.getX(), is(1));
        assertThat(robot.getY(), is(2));
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
        assertTrue(robot.hasBeenPlaced());
    }
}