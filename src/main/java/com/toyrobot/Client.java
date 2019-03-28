package com.toyrobot;

import com.toyrobot.model.Simulation;
import com.toyrobot.service.RobotService;

import java.io.IOException;
import java.io.InputStream;

public class Client {
    private RobotService robotService;
    private Simulation simulation;
    private InputStream commandsStream;

    public Client(String filePath, InputStream commandsStream, RobotService robotService) throws IOException{
        this.robotService = robotService;
        simulation  = robotService.createSimulation(filePath);
        this.commandsStream = commandsStream;
    }



}
