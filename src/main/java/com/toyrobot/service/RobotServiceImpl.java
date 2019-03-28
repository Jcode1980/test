package com.toyrobot.service;

import com.toyrobot.enums.ActionType;
import com.toyrobot.enums.RotationDirection;
import com.toyrobot.model.Simulation;

import java.io.IOException;
import java.util.List;

public class RobotServiceImpl implements  RobotService{

    @Override
    public Simulation createSimulation(String filePath) throws IOException {
        return null;
    }

    @Override
    public boolean processCommandForJob(String command, Simulation simulation) {
        if(simulation == null){throw new NullPointerException("simulation must not be null");}
        if(command == null){throw new NullPointerException("command parameter must not be null");}

        boolean continueJob = true;
        try {

            List<ActionType> actionTypes = ActionType.actionTypes();
            ActionType actionType =  actionTypes.stream().filter(
                    currentAction -> command.matches(currentAction.pattern())).findFirst()
                    .orElseThrow(()-> new IllegalArgumentException("Incorrect command passed in " + command));

            switch (actionType) {
                case LEFT:
                    simulation.robot().rotate(RotationDirection.LEFT);
                    break;
                case RIGHT:
                    simulation.robot().rotate(RotationDirection.RIGHT);
                    break;
                case MOVE:
                    simulation.robot().move();
                    break;
                case REPORT:
                    resultingInfo = simulation.report();
                    break;

            }
        } catch (IllegalArgumentException e) {
            System.out.println("Unknown command given. Please enter valid command");
            //e.printStackTrace();
        }

        return continueJob;
    }

}
