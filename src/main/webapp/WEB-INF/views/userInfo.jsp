
<%--

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
<%@include file="footer.jsp"%>--%>
<%@ page import="com.Songzhiyong.model.Users" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Title</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<%@include file="header.jsp"%>--%>
<%--<h1>User Info</h1>--%>
<%--<%--%>
<%--    Cookie[] allCookies = request.getCookies();--%>
<%--    for (int i = 0; i < allCookies.length; i++) {--%>
<%--        for (Cookie cookie : allCookies) {--%>
<%--            out.println("<br/>"+cookie.getName()+"===>"+cookie.getValue());--%>
<%--        }--%>
<%--    }--%>
<%--%>--%>
<%--<%--%>
<%--    Users user= (Users) session.getAttribute("user");--%>
<%--%>--%>
<%--<table>--%>
<%--    <tr><td>Username:</td><td><%=user.getUsername()%></td></tr>--%>
<%--    <tr><td>Password:</td><td><%=user.getPassword()%></td></tr>--%>
<%--    <tr><td>Email:</td><td><%=user.getEmail()%></td></tr>--%>
<%--    <tr><td>Gender:</td><td><%=user.getGender()%></td></tr>--%>
<%--    <tr><td>BirthDate:</td><td><%=user.getBirth()%></td></tr>--%>
<%--    <tr><td><a href="updateUser">Update User</a></td></tr>--%>
<%--</table>--%>
<%--<%@include file="footer.jsp"%>--%>
<%--</body>--%>
<%--</html>--%>

<html>
<head>
    <title>Title</title>
</head>
<body>



<%@include file="header.jsp"%>

<h1>User Info</h1>
<%
    Cookie[] allCookies = request.getCookies();
    for (int i = 0; i < allCookies.length; i++) {
        for (Cookie cookie : allCookies) {
            out.println("<br/>"+cookie.getName()+"===>"+cookie.getValue());
        }
    }
%>
<%
    Users user= (Users) session.getAttribute("user");
%>
<table>
    <tr><td>Username:</td><td><%=user.getUsername()%></td></tr>
    <tr><td>Password:</td><td><%=user.getPassword()%></td></tr>
    <tr><td>Email:</td><td><%=user.getEmail()%></td></tr>
    <tr><td>Gender:</td><td><%=user.getGender()%></td></tr>
    <tr><td>BirthDate:</td><td><%=user.getBirth()%></td></tr>
    <tr><td><a href="updateUser">Update User</a></td></tr>
</table>
<%@include file="footer.jsp"%>
</body>
</html>