package com.toyrobot.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GridBoardTest {
    private GridBoard gridBoard;

    @Before
    public void setUp() throws Exception {
        gridBoard = new GridBoard(4,4);

    }

    @Test
    public void getWidth() {
        assertThat(gridBoard.getWidth(), is(4));
    }

    @Test
    public void getHeight() {
        assertThat(gridBoard.getWidth(), is(4));
    }
}