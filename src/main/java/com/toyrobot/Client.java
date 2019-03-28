package com.toyrobot;

import com.toyrobot.model.Simulation;
import com.toyrobot.service.RobotService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Client {
    private RobotService robotService;
    private Simulation simulation;
    private InputStream commandsStream;
    private OutputStream outputStream;

    public Client(RobotService robotService, InputStream commandsStream, OutputStream outputStream) throws IOException{
        this.robotService = robotService;
        simulation  = robotService.createSimulation(4,4, outputStream);
        this.commandsStream = commandsStream;
        this.outputStream = outputStream;
    }

    private void wellcomPhase(){

        Scanner scanner = new Scanner(commandsStream);
        String commandStr;

        boolean continueProgram = true;
        while (continueProgram) {
            System.out.print("(l)eft, (r)ight, (a)dvance <n>, (q)uit: ");
            commandStr = scanner.nextLine();
            continueProgram = robotService.processCommandForJob(commandStr, simulation);
        }
    }


}
