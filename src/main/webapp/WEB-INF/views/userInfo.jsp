<%@ page import="com.Songzhiyong.model.Users" %><%--
  Created by IntelliJ IDEA.
  User: 32312
  Date: 2022/4/5
  Time: 23:56
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<h1>User Info</h1>
<h1>User Info</h1>
<%
    Users user=(Users) request.getAttribute("user");
%>
<table>
    <tr>
        <td>Username:</td><td><%=user.getUsername()%></td>
    </tr><tr>
    <td>Password:</td><td><%=user.getPassword()%></td>
</tr><tr>
    <td>Email:</td><td><%=user.getEmail()%></td>
</tr><tr>
    <td>Gender:</td><td><%=user.getGender()%></td>
</tr><tr>
    <td>Birth Date:</td><td><%=user.getBirth()%></td>
</tr>
</table>
<%@include file="footer.jsp"%>