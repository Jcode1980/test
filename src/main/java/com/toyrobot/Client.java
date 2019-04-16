package com.toyrobot;

import com.toyrobot.model.Simulation;
import com.toyrobot.service.RobotService;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Client {
    private static final Logger log = Logger.getLogger(Client.class);
    private RobotService robotService;
    private Simulation simulation;
    private InputStream commandsStream;

    public Client(RobotService robotService, Integer mapHeight, Integer mapWidth,InputStream commandsStream) throws IOException{
        this.robotService = robotService;
        simulation  = robotService.createSimulation(mapHeight,mapWidth);
        this.commandsStream = commandsStream;
    }

    public void startSimulation(){

        Scanner scanner = new Scanner(commandsStream);
        String commandStr;

        log.info("left, right, move, place x,y,f: ");
        while (scanner.hasNext()) {
            commandStr = scanner.nextLine();
            robotService.processCommandForJob(commandStr, simulation);
        }
    }


}
