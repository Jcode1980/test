package com.toyrobot.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Simulation {
    private GridBoard gridBoard;
    private Robot robot;

    public Simulation(GridBoard gridBoard, Robot robot){
        this.gridBoard = gridBoard;
        this.robot = robot;
    }

    public Robot robot() {
        return robot;
    }


    public GridBoard gridBoard() {
        return gridBoard;
    }

}
