package com.oracle.soccerleague.model;

public class LeagueException extends Exception {
    public LeagueException(Exception e) {
        super(e);
    }
    public LeagueException(String msg) {
        super(msg);
    }
}
