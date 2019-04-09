package com.toyrobot;

import com.toyrobot.model.Simulation;
import com.toyrobot.service.RobotService;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Client {
    private static final Logger log = Logger.getLogger(Client.class);
    private RobotService robotService;
    private Simulation simulation;
    private InputStream commandsStream;
    private OutputStream outputStream;

    public Client(RobotService robotService, InputStream commandsStream) throws IOException{
        this.robotService = robotService;
        simulation  = robotService.createSimulation(4,4);
        this.commandsStream = commandsStream;
        this.outputStream = outputStream;
    }

    public void startSimulation(){

        Scanner scanner = new Scanner(commandsStream);
        String commandStr;


        while (true) {
            log.info("(l)eft, (r)ight, (m)ove, (p)lace x y f: ");
            commandStr = scanner.nextLine();
            robotService.processCommandForJob(commandStr, simulation);
        }
    }


}
