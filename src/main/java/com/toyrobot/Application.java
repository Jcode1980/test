package com.toyrobot;

import com.toyrobot.service.RobotService;
import com.toyrobot.service.RobotServiceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Paths;

public class Application {
    public static final String DEMO_SITE_MAP = "src/main/resources/SiteMap.txt";

    static public void main(String args[]){
        initializeClient(args);
    }


    static private void initializeClient(String[] args){
        try{

            String mapsFile = args.length > 0 ? args[0] : Paths.get(DEMO_SITE_MAP).toString();

            InputStream instructionsStream = args.length > 1 ? new FileInputStream(new File(args[1])) : System.in;
            Client client = new Client(mapsFile, instructionsStream, new RobotServiceImpl());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
