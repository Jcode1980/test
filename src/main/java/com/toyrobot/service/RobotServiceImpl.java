package com.toyrobot.service;

import com.toyrobot.enums.ActionType;
import com.toyrobot.enums.CardinalPoint;
import com.toyrobot.enums.RotationDirection;
import com.toyrobot.model.GridBoard;
import com.toyrobot.model.Robot;
import com.toyrobot.model.Simulation;
import org.apache.log4j.Logger;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class RobotServiceImpl implements RobotService{
    private static final Logger log = Logger.getLogger(RobotServiceImpl.class);
    private static final String COMMA = ",";
    private static final String SPACE = " ";
    private static final List<ActionType> actionTypes = ActionType.actionTypes();


    @Override
    public Simulation createSimulation(Integer width, Integer height) {
        return new Simulation(new GridBoard(width, height), new Robot());
    }

    @Override
    public boolean processCommandForJob(String command, Simulation simulation) {
        if (simulation == null) { throw new NullPointerException("simulation must not be null"); }
        if (command == null) { throw new NullPointerException("command parameter must not be null"); }

        ActionType actionType = actionTypes.stream().filter(
                currentActionType -> command.matches(currentActionType.pattern())).findFirst().orElse(null);

        if (actionType == null) {
            log.error("unkown command :" + command);
            return false;
        }

        boolean successfullyProcessed = true;

        switch (actionType) {
            case PLACE:
                successfullyProcessed = placeAction(command, simulation);
                break;
            case LEFT:
                successfullyProcessed = simulation.robot().rotate(RotationDirection.LEFT);
                break;
            case RIGHT:
                successfullyProcessed = simulation.robot().rotate(RotationDirection.RIGHT);
                break;
            case MOVE:
                successfullyProcessed = simulation.moveRobot();
                break;
            case REPORT:
                successfullyProcessed = simulation.report();
                break;
        }
        return successfullyProcessed;
    }

    private boolean placeAction(String command, Simulation simulation) {
        String parametersString = command.substring(command.indexOf(SPACE));
        List<String> commandList = Arrays.asList(parametersString.split(COMMA));
        int x = Integer.valueOf(commandList.get(0).trim());
        int y = Integer.valueOf(commandList.get(1).trim());
        String cDirection = commandList.get(2);

        CardinalPoint cardinalPoint = CardinalPoint.cardinalPointForDirection(cDirection);
        return simulation.placeRobot(new Point(x,y), cardinalPoint);
    }


}
