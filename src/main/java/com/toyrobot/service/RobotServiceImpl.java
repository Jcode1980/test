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
        if (simulation == null) {
            throw new NullPointerException("simulation must not be null");
        }
        if (command == null) {
            throw new NullPointerException("command parameter must not be null");
        }


        System.out.println("this incoming command: " + command);

        ActionType actionType = actionTypes.stream().filter(
                currentActionType -> command.matches(currentActionType.pattern())).findFirst().orElse(null);

        if (actionType == null) {
            log.error("unkown command :" + command);
            return false;
        }

        try{
            switch (actionType) {
                case PLACE:
                    placeAction(command, simulation);
                    break;
                case LEFT:
                    simulation.robot().rotate(RotationDirection.LEFT);
                    break;
                case RIGHT:
                    simulation.robot().rotate(RotationDirection.RIGHT);
                    break;
                case MOVE:
                    simulation.moveRobot();
                    break;
                case REPORT:
                    simulation.report();
                    break;

            }
        }catch (NullPointerException e){
            log.error(e.getMessage());
        }

        return true;
    }

    private void placeAction(String command, Simulation simulation) {
        String parametersString = command.substring(command.indexOf(SPACE), command.length());
        List<String> commandList = Arrays.asList(parametersString.split(COMMA));
        Integer x = Integer.valueOf(commandList.get(0).trim());
        Integer y = Integer.valueOf(commandList.get(1).trim());
        String cDirection = commandList.get(2);

        CardinalPoint cardinalPoint = CardinalPoint.cardinalPointForDirection(cDirection);
        simulation.placeRobot(new Point(x,y), cardinalPoint);
    }


}
