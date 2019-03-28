package com.toyrobot.enums;

import java.util.Arrays;
import java.util.List;

public enum CardinalPoint {
    NORTH(1),EAST(2),SOUTH(3),WEST(4);

    public static List<CardinalPoint> cardinalPoints = Arrays.asList(NORTH, EAST, SOUTH, WEST);

    CardinalPoint(int value){this.value = value; }

    private int value;

    public int getValue(){return value;}


    public static CardinalPoint cardinalPointForRotation(CardinalPoint currentDirection, RotationDirection rotationDirection){
        int indexOf = cardinalPoints.indexOf(currentDirection);
        int indexOfNextDirection = 0;

        if(RotationDirection.LEFT.equals(rotationDirection))indexOfNextDirection = (indexOf != 0 ? (indexOf -1) : 3);

        if(RotationDirection.RIGHT.equals(rotationDirection)) indexOfNextDirection = (indexOf != 3 ? (indexOf + 1) : 0);

        return cardinalPoints.get(indexOfNextDirection);
    }

    public static CardinalPoint cardinalPointForInt(int value){
        return cardinalPoints.stream().filter(currentDirection -> currentDirection.value == value).findFirst().orElseThrow(() ->
                new IllegalArgumentException("Cardinal Point with value: " + value + " not found"));
    }

    public static CardinalPoint cardinalDirectionForString(String value){
        return cardinalPoints.stream().filter(currentDirection -> currentDirection.name().equalsIgnoreCase(value)).findFirst().orElseThrow(() ->
                new IllegalArgumentException("Cardinal Point with name: " + value + " not found"));
    }
}
