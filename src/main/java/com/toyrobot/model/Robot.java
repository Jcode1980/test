package com.toyrobot.model;

import com.toyrobot.enums.CardinalPoint;
import com.toyrobot.enums.RotationDirection;

import java.awt.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class Robot {
        private Point point;
        private CardinalPoint cardinalPoint;

        public boolean placedOnBoard() { return getX() >= 0 && getY() >=0; }

        public int getX() {return (int)point.getX(); }

        public int getY() {return (int)point.getY(); }

        public CardinalPoint cardinalPoint(){ return cardinalPoint; }

        public Point nextMoveCoordinates(){
            CardinalPoint direction = cardinalPoint();
            int newX = getX();
            int newY = getY();

            switch(direction) {
                case NORTH:
                    newY=newY+ 1;
                    break;
                case EAST:
                    newX =newX + 1;
                    break;
                case SOUTH:
                    newY = newY - 1;
                    break;
                case WEST:
                    newX = newX - 1;
                    break;
            }
            return new Point(newX, newY);
        }

        public void rotate(RotationDirection rotationDirection) {
            checkNotNull(rotationDirection, "Rotation Direction must not be null");
            cardinalPoint = CardinalPoint.cardinalPointForRotation(cardinalPoint(), rotationDirection);
        }

        public void move(){
            this.point = nextMoveCoordinates();
        }

        public void place(Point point, CardinalPoint cp){
            checkNotNull(point, "point must not be null");
            checkNotNull(cp, "cp Cardinal Points must not be null");
            checkArgument(point.getX() >= 0, "x coordinate must be a positive non zero integer: %s", point.getX());
            checkArgument(point.getY() >= 0, "y coordinate must be a positive non zero integer: %s", point.getY());

            this.point = point;
            this.cardinalPoint = cp;
        }
    }