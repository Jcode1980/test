package com.toyrobot;
import com.toyrobot.service.RobotServiceImpl;
import java.io.*;

public class Application {
    private static final Integer DEFAULT_MAP_HEIGHT = 4;
    private static final Integer DEFAULT_MAP_WIDTH = 4;

    static public void main(String[] args) throws IOException {
        initializeClient(args);
    }


    static private void initializeClient(String[] args) throws IOException{
            InputStream instructionsStream = args.length > 0 ? new FileInputStream(new File(args[0])) : System.in;
            Client client = new Client(new RobotServiceImpl(), DEFAULT_MAP_HEIGHT, DEFAULT_MAP_WIDTH, instructionsStream);
            client.startSimulation();
    }
}
