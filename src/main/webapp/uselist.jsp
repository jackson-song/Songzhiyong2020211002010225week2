<%--
  Created by IntelliJ IDEA.
  User: 32312
  Date: 2022/4/5
  Time: 23:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: 86134
  Date: 2022-4-3
  Time: 23:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<table border=1>
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Password</th>
        <th>Email</th>
        <th>Gender</th>
        <th>Birthdate</th>
    </tr>
    <%
        ResultSet rs = (ResultSet) request.getAttribute("rsname");
        if (rs == null) {
    %>
    <tr>
        <td>No Date!!!</td>
    </tr>
    <%
        } else {
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("username") + "</td>");
                out.println("<td>" + rs.getString("password") + "</td>");
                out.println("<td>" + rs.getString("email") + "</td>");
                out.println("<td>" + rs.getString("gender") + "</td>");
                out.println("<td>" + rs.getDate("birthdate") + "</td>");
                out.println("</tr>");
            }
        }
    %>
</table>
