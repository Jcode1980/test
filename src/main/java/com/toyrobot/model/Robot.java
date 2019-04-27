package com.toyrobot.model;

import com.toyrobot.enums.CardinalPoint;
import com.toyrobot.enums.RotationDirection;
import org.apache.log4j.Logger;

import java.awt.Point;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class Robot {
    private static final Logger log = Logger.getLogger(Robot.class);
    private Point point;
    private CardinalPoint cardinalPoint;

    public boolean placedOnBoard() {
        return getX() >= 0 && getY() >= 0;
    }

    public int getX() {
        return (int) point.getX();
    }

    public int getY() {
        return (int) point.getY();
    }

    public CardinalPoint cardinalPoint() {
        return cardinalPoint;
    }

    public Point nextMoveCoordinates() {
        CardinalPoint direction = cardinalPoint();
        int newX = getX();
        int newY = getY();

        switch (direction) {
            case NORTH:
                newY = newY + 1;
                break;
            case EAST:
                newX = newX + 1;
                break;
            case SOUTH:
                newY = newY - 1;
                break;
            case WEST:
                newX = newX - 1;
                break;
        }
        return new Point(newX, newY);
    }

    public boolean rotate(RotationDirection rotationDirection) {
        checkNotNull(rotationDirection, "Rotation Direction must not be null");
        boolean successfull = false;

        if(hasBeenPlaced()){
            cardinalPoint = CardinalPoint.cardinalPointForRotation(cardinalPoint(), rotationDirection);
            successfull = true;
        }
        else{
            log.error("Please place robot before trying to rotate.");
        }
        return successfull;

    }

    public boolean move() {
        boolean successful;

        if (hasBeenPlaced()) {
            this.point = nextMoveCoordinates();
            if (log.isDebugEnabled()) {log.debug("Robot place with point: " + this.point);}
            successful = true;
        } else {
            log.info("Robot must be placed before being moved.");
            successful=  false;
        }
        return successful;
    }

    public void place(Point point, CardinalPoint cp) {
        checkNotNull(point, "point must not be null");
        checkNotNull(cp, "cp Cardinal Points must not be null");
        checkArgument(point.getX() >= 0, "x coordinate must be a positive non zero integer: %s", point.getX());
        checkArgument(point.getY() >= 0, "y coordinate must be a positive non zero integer: %s", point.getY());

        this.point = point;
        this.cardinalPoint = cp;
    }

    public boolean hasBeenPlaced() {
        return point != null;
    }
}