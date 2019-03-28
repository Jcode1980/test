package com.toyrobot.model;

import static com.google.common.base.Preconditions.checkArgument;

public class GridBoard {
    private int width;
    private int height;


    /**
     * Constructor of GridBoardGame
     *
     * @param width width of the board
     * @param height height of the board
     * @throws IllegalArgumentException  if either x or y passed in is a negative int.
     *
     */
    public GridBoard(int width, int height){
        checkArgument(width >= 0, "width must be a positive non zero integer: %s", width);
        checkArgument(height >= 0, "y height must be a positive non zero integer: %s", height);

        this.width=width;
        this.height=height;
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
