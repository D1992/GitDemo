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
            errorMsgs.add("����ֶα���Ϊ��λ������");
        } catch (NullPointerException e) {
        	errorMsgs.add("δ��������ֶ�");
        }
            
        // Verify form parameters
        if ( (year != -1) && ((year < 2008) || (year > 2020)) ) {
            errorMsgs.add("����ֶε�ֵ������2008��2020֮��");
        }
        if ( season == null || season.equals("UNKNOWN") ) {
            errorMsgs.add("��ѡ����������");
        }
        if ( title == null || title.length() == 0 ) {
            errorMsgs.add("��������������");
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
