package com.oracle.soccerleague.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.soccerleague.model.League;
import com.oracle.soccerleague.model.LeagueException;
import com.oracle.soccerleague.model.LeagueService;

@WebServlet(name="AddLeagueServlet", urlPatterns="/admin/AddLeague.action")
public class AddLeagueServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		
		// Retrieve form parameters.
        String yearStr = request.getParameter("year").trim();
        String season = request.getParameter("season").trim();
        String title = request.getParameter("title").trim();
            
        // Perform data conversions.
        List<String> errorMsgs = new LinkedList<String>();
        int year = -1;
        try {
            year = Integer.parseInt(yearStr);
        } catch (NumberFormatException e) {
            errorMsgs.add("年份字段必须为四位正整数");
        } catch (NullPointerException e) {
        	errorMsgs.add("未发现年份字段");
        }
            
        // Verify form parameters
        if ( (year != -1) && ((year < 2008) || (year > 2020)) ) {
            errorMsgs.add("年份字段的值必须在2008到2020之间");
        }
        if ( season == null || season.equals("UNKNOWN") ) {
            errorMsgs.add("请选择联赛季节");
        }
        if ( title == null || title.length() == 0 ) {
            errorMsgs.add("请输入联赛标题");
        }
            
        if ( !errorMsgs.isEmpty() ) {
        	request.setAttribute("errorMsgs", errorMsgs);
            RequestDispatcher view
                    = request.getRequestDispatcher("AddLeague.jsp");
            view.forward(request, response);
            return;
        }
            
        // Perform business logic
        LeagueService leagueSvc = LeagueService.getInstance();
        League league = null;
		try {
			league = leagueSvc.createLeague(year, season, title);
		} catch (LeagueException e) {
			errorMsgs.add(e.getMessage());
			request.setAttribute("errorMsgs", errorMsgs);
			
            RequestDispatcher view
                    = request.getRequestDispatcher("AddLeague.jsp");
            view.forward(request, response);
            return;
		}
        request.setAttribute("league", league);
            
        // Send the Success view
        RequestDispatcher view
                = request.getRequestDispatcher("AddLeagueSucc.jsp");
        view.forward(request, response);
    }
}
