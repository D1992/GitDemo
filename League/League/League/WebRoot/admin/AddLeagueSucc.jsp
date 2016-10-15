<%@ page contentType="text/html" pageEncoding="UTF-8" session = "false" %>
<%@ page import = "com.oracle.soccerleague.model.League, java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<c:set var="pageTitle">Duke足球联赛: 添加联赛成功</c:set>

<html>
    <head>
        <title>${pageTitle}</title>
    </head>
    <body bgcolor='white'>
        <!-- Page Heading -->
        <table border='1' cellpadding='5' cellspacing='0' width='400'>
            <tr bgcolor='#CCCCFF' align='center' valign='center' height='20'>
                <td><h3>${pageTitle}</h3></td>
            </tr>
        </table>
        
        <p>您的添加 <i>${league.title}</i> 的请求已成功。</p>
        <a href='../'>回首页</a>
    </body>
</html>