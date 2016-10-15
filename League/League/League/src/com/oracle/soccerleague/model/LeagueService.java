package com.oracle.soccerleague.model;

import java.util.*;

public class LeagueService {
    private static final String[] SEASONS =  {"Spring", "Summer", "Fall", "Winter"};
    private static LeagueService leagueService = new LeagueService();
    private LeagueDAO leagueDAO;
    
    private LeagueService() {
        leagueDAO = LeagueDAO.getInstance();
    }
    
    public static LeagueService getInstance() {
        return leagueService;
    }
    
    public List<League> getAllLeagues() throws LeagueException {
        return leagueDAO.retrieveAll();
    }
    
    public League getLeague(int year, String season) 
            throws LeagueException {
        return leagueDAO.retrieve(year, season);
    }
    
    public League createLeague(int year, String season, String title) 
            throws LeagueException {
        League league = new League(-1, year, season, title);
        leagueDAO.insert(league);
        return league;
    }
    
    public String[] getAllSeasons() {
        return SEASONS;
    }
}
