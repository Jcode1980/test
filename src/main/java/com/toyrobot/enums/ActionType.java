package com.toyrobot.enums;

import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


public enum ActionType{
    PLACE("(?i)PLACE\\s[0-9]+,[0-9]+,(NORTH|SOUTH|EAST|WEST)(?-i)"),MOVE("(?i)MOVE(?-i)"),RIGHT("(?i)RIGHT(?-i)"),LEFT("(?i)LEFT(?-i)"),
    REPORT("(?i)REPORT(?-i)");

    ActionType(String pattern){
        checkNotNull(pattern, "Pattern must not be null", pattern);
        this.pattern = pattern;}

    private final String pattern;

    public String pattern(){return pattern;}
    public static List<ActionType> actionTypes(){return Arrays.asList(PLACE, LEFT, RIGHT, MOVE, REPORT);}
}