package com.oracle.soccerleague.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class LeagueDAO {
    private static final String ERROR = "数据库错误：";
    private static final String RETRIEVE_STMT = "SELECT * FROM League WHERE lyear=? AND season=?";
    private static final String RETRIEVE_ALL_STMT = "SELECT * FROM League";
    private static final String RETRIVE_MAX_LID = "SELECT max(lid) FROM League";
    private static final String INSERT_STMT = "INSERT INTO League VALUES (?, ?, ?, ?)";    
    
    private static LeagueDAO leagueDAO = new LeagueDAO();
    private DataSource ds;

    private LeagueDAO() {
        try {
        	Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/SoccerLeagueDS");
		} catch (NamingException e) {}
    }

    public static LeagueDAO getInstance() {
    	return leagueDAO; 
    }
    
    public List<League> retrieveAll() throws LeagueException{
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet results = null;

        try {
            connection = ds.getConnection();
            stmt = connection.prepareStatement(RETRIEVE_ALL_STMT);
            results = stmt.executeQuery();

            List<League> leagueList = new LinkedList<League>();
            while (results.next()) {
            	League league = new League(results.getInt("lid"), 
            			results.getInt("lyear"),
                        results.getString("season"), 
                        results.getString("title"));
                leagueList.add(league);
            }

            return leagueList;
        } catch (SQLException e) {
            throw new LeagueException(ERROR + e.getMessage());
        } finally {
            if (results != null) {
                try { results.close(); } catch (SQLException se) { }
            }
            if (stmt != null) {
                try { stmt.close(); } catch (SQLException se) { }
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException e) { }
            }
        } 
    } 
    
    public League retrieve(int year, String season) 
    		throws LeagueException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet results = null;

        try {
            connection = ds.getConnection();
            stmt = connection.prepareStatement(RETRIEVE_STMT);
            stmt.setInt(1, year);
            stmt.setString(2, season);
            results = stmt.executeQuery();

            if (!results.next()) 
            	throw new LeagueException("未找到指定联赛");
            
            if (!results.isLast())
            	throw new LeagueException("League记录重复");
            
            League league = new League(results.getInt("lid"),
            		results.getInt("lyear"),
                    results.getString("season"),
                    results.getString("title"));

            return league;
        } catch (SQLException se) {
            throw new LeagueException(ERROR + se.getMessage());
        } finally {
            if (results != null) {
                try { results.close(); } catch (SQLException se) { }
            }
            if (stmt != null) {
                try { stmt.close(); } catch (SQLException se) { }
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException e) { }
            }
        } 
    } 

    public void insert(League league) throws LeagueException {
        Connection connection = null;
        PreparedStatement insert_stmt = null;

        try {
            connection = ds.getConnection();
            insert_stmt = connection.prepareStatement(INSERT_STMT);
            
            int leagueID = getNextID(connection);
            insert_stmt.setInt(1, leagueID);
            insert_stmt.setInt(2, league.getYear());
            insert_stmt.setString(3, league.getSeason());
            insert_stmt.setString(4, league.getTitle());
            insert_stmt.executeUpdate();

            league.setLid(leagueID);
        } catch (SQLException se) {
            throw new LeagueException(ERROR + se.getMessage());
        } finally {
            if (insert_stmt != null) {
                try { insert_stmt.close(); } catch (SQLException se) { }
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException e) { }
            }
        }
    }
    
    private int getNextID(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet results = stmt.executeQuery(RETRIVE_MAX_LID);
        results.next();
        int nextId = results.getInt(1) + 1;
        
        results.close();
        stmt.close();
        
        return nextId;
    }
}
