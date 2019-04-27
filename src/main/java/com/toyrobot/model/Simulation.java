package com.toyrobot.model;
import com.toyrobot.enums.CardinalPoint;
import org.apache.log4j.Logger;

import java.awt.Point;
import static com.google.common.base.Preconditions.checkNotNull;

public class Simulation {
    private static final Logger log = Logger.getLogger(Simulation.class);
    private static final String COMMA = ",";

    private GridBoard gridBoard;
    private Robot robot;

    public Simulation(GridBoard gridBoard, Robot robot){
        this.gridBoard = gridBoard;
        this.robot = robot;
    }

    public Robot robot() {
        return robot;
    }

    public GridBoard gridBoard() {
        return gridBoard;
    }

    public boolean placeRobot(Point point, CardinalPoint cp) {
        checkNotNull(cp, "cp Cardinal Poinst must not be null");

        if (pointIsValid(point)) {
            robot().place(point, cp);
            return true;
        } else {
            log.info("Invalid coordinates. Robot not placed on board: x: " + point.getX() + " y: " + point.getY());
            return false;
        }
    }

    public boolean report(){
        boolean success = false;
        if(robot().hasBeenPlaced()){
            log.info(robot().getX() + COMMA + robot().getY() + COMMA + robot().cardinalPoint().name());
            success = true;
        }
        else{
            log.error("Robot not yet placed. Please place robot before running report command");
            success = false;
        }
        return success;
    }

    public boolean pointIsValid(Point point) {
        //check if coordinates are withing booundary
        return coordinatesAreWithinBoundary((int)point.getX(), (int)point.getY());
    }

    private boolean coordinatesAreWithinBoundary(int x, int y) {
        return x >= 0 && y >= 0 && gridBoard.getWidth() > x && gridBoard.getHeight() > y;
    }

    public boolean moveRobot(){
        boolean successfull;

        if(!robot().hasBeenPlaced()){
            log.error("Robot has not yet been placed");
            successfull = false;
        }else if(!pointIsValid(robot().nextMoveCoordinates())){
            log.error("Next move is not valid: " + robot().nextMoveCoordinates());
            successfull = false;
        }
        else{
            robot().move();
            successfull = true;
        }

        return successfull;



    }

}
