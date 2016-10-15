<%@ page contentType="text/html" pageEncoding="UTF-8" session = "false" %>
<%@ page import = "com.oracle.soccerleague.model.*, java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%  
    pageContext.setAttribute("leagueSvc", LeagueService.getInstance());
%>
<c:set var="leagueList" value="${leagueSvc.allLeagues}" />
<c:set var="pageTitle">Duke足球联赛: 列出所有联赛</c:set>

<html>
    <head>
        <title>${pageScope.pageTitle}</title>
    </head>
    <body bgcolor='white'>
        <!-- Page Heading -->
        <table border='1' cellpadding='5' cellspacing='0' width='400'>
            <tr bgcolor='#CCCCFF' align='center' valign='center' height='20'>
                <td><h3>${pageTitle}</h3></td>
            </tr>
        </table>
        <c:if test="${not empty pageScope.leagueList}">
            <p>现有以下足球联赛:</p>
            <ul>
                <c:forEach var="league" items="${leagueList}">
                    <li>${league.title}</li>
                </c:forEach>
            </ul>
        </c:if>
        <a href='.'>回首页</a>
    </body>
</html>