package com.toyrobot.service;

import com.toyrobot.model.Simulation;

import java.io.IOException;
import java.util.ArrayList;

public interface RobotService {
    /**
     * @param filePath <code>String</code> representation of the site map
     * @return <code>IExcavationJob</code> the job
     * @throws IOException
     */
    Simulation createSimulation(String filePath) throws IOException;


    /**
     * Processes the command for the given <code>IExcavationJob</code>
     *
     * @param command The command to be applied to the job
     * @param simulation the simulation
     * @return <code>boolean</code>
     */
    boolean processCommandForJob(String command, Simulation simulation);


}
