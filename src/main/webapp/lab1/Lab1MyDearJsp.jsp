<%--
  Created by IntelliJ IDEA.
  User: 32312
  Date: 2022/5/18
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String name=request.getParameter("name");
    String submit=request.getParameter("submit");
    String myClass = request.getParameter("class");
    String id=request.getParameter("id");
%>
<h2>
    <h1>This is lab1MyDearJsp.jsp</h1><br>
    <h2>name: <%=name%></h2><br>
    <h2>submit: <%=submit%></h2><br>
    <h2>class: <%=myClass%></h2><br>
    <h2>ID: <%=id%></h2><br>
    <h2>2020211002010225</h2><br>
</h2>
</body>
</html>
