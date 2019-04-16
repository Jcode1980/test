package com.toyrobot.service;

import com.toyrobot.model.Simulation;

import java.io.IOException;

public interface RobotService {
    /**
     * @param width <code>String</code> width of the board to be created
     * @param height <code>String</code> height of the board to be created
     * @return <code>Simulation</code> simulation the holds the board and robot
     * @throws IOException
     */
    Simulation createSimulation(Integer height, Integer width) throws IOException;


    /**
     * Processes the command for the given <code>IExcavationJob</code>
     *
     * @param command The command to be run
     * @param simulation the simulation that will run the coommand
     * @return <code>boolean</code>
     */
    boolean processCommandForJob(String command, Simulation simulation);


}
