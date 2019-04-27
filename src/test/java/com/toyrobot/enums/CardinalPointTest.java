package com.toyrobot.enums;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CardinalPointTest {

    @Test
    public void cardinalPointForRotation() {
        assertThat(CardinalPoint.cardinalPointForRotation(CardinalPoint.EAST, RotationDirection.LEFT),
                is(CardinalPoint.NORTH));

        assertThat(CardinalPoint.cardinalPointForRotation(CardinalPoint.WEST, RotationDirection.RIGHT),
                is(CardinalPoint.NORTH));
    }

    @Test(expected = NullPointerException.class)
    public void cardinalPointForRotation_shouldThrowNullPointerExceptionWhenNullPointPassedIn(){
        CardinalPoint.cardinalPointForRotation(null, RotationDirection.LEFT);
    }

    @Test(expected = NullPointerException.class)
    public void cardinalPointForRotation_shouldThrowNullPointerExceptionWhenNullDirectionPassedIn(){
        CardinalPoint.cardinalPointForRotation(CardinalPoint.WEST, null);
    }

    @Test
    public void cardinalPointForDirection() {
        assertThat(CardinalPoint.cardinalPointForDirection("North"),is(CardinalPoint.NORTH));
        assertThat(CardinalPoint.cardinalPointForDirection("East"),is(CardinalPoint.EAST));
        assertThat(CardinalPoint.cardinalPointForDirection("South"),is(CardinalPoint.SOUTH));
        assertThat(CardinalPoint.cardinalPointForDirection("West"),is(CardinalPoint.WEST));
    }
}