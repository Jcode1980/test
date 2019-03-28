package com.toyrobot.enums;

import java.util.Arrays;
import java.util.List;

public enum CardinalPoint {
    NORTH,EAST,SOUTH,WEST;

    public static List<CardinalPoint> cardinalPoints = Arrays.asList(NORTH, EAST, SOUTH, WEST);

    public static CardinalPoint cardinalPointForRotation(CardinalPoint currentDirection, RotationDirection rotationDirection){
        if(currentDirection == null){throw new NullPointerException("currentDirection must not be null");}
        if(rotationDirection == null){throw new NullPointerException("rotationDirection parameter must not be null");}

        int indexOf = cardinalPoints.indexOf(currentDirection);
        int indexOfNextDirection = 0;

        if(RotationDirection.LEFT.equals(rotationDirection))indexOfNextDirection = (indexOf != 0 ? (indexOf -1) : 3);

        if(RotationDirection.RIGHT.equals(rotationDirection)) indexOfNextDirection = (indexOf != 3 ? (indexOf + 1) : 0);

        return cardinalPoints.get(indexOfNextDirection);
    }

    public static CardinalPoint  cardinalPointForDirection(String direction){
        return cardinalPoints.stream().filter(cardinalPoint -> cardinalPoint.name()
                .equalsIgnoreCase(direction)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cardinal Point with name: " + direction + " not found"));
    }

}

