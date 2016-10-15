<%@ page contentType="text/html" pageEncoding="UTF-8" session = "false" %>
<%@ page import = "com.oracle.soccerleague.model.*, java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%  
    pageContext.setAttribute("leagueSvc", LeagueService.getInstance());
%>
<c:set var="pageTitle">Duke足球联赛: 添加新联赛</c:set>

<html>
    <head>
        <title>${pageTitle}</title>
    </head>
    <body>
        <!-- Page Heading -->
        <table border='1' cellpadding='5' cellspacing='0' width='400'>
            <tr bgcolor='#CCCCFF' align='center' valign='center' height='20'>
                <td><h3>${pageTitle}</h3></td>
            </tr>
        </table>
        
        <c:if test="${not empty errorMsgs}">
            <p>
                <font color='red'>请更正以下错误:
                    <ul>
                        <c:forEach var="msg" items="${errorMsgs}">
                            <li>${msg}</li>
                        </c:forEach>
                    </ul>
                </font>
            </p>
        </c:if>
                
        <p>本页面用于创建新联赛</p>
            
        <form action='<c:url value="AddLeague.action" />' method='POST'>
                              年 份：<input type='text' name='year' value='${param.year}' /> <br/><br/>
            <c:set var="seasonList" value="${leagueSvc.allSeasons}" />
                              季 节：<select name='season'>
                <c:if test="${(empty param.season) or (param.season eq 'UNKNOWN')}">
                    <option value='UNKNOWN'>select...</option>
                </c:if>
                <c:forEach var="season" items="${seasonList}">
                    <option value='${season}'
                        <c:if test="${season eq param.season}">
                            selected
                        </c:if>
                    >${season}</option>
                </c:forEach>
            </select> <br/><br/> 

                              标 题：<input type='text' name='title' value='${param.title}' /> <br/><br/>                                                
            <input type='submit' value='添加联赛' />
        </form>
         
    </body>
</html>

