<%--<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>


<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>This is my page</h1>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet-week1</a>
<br/>
<a href="hello">Student Info Servlet-week2</a>
<br/>
<a href="life">Life Cycle Servlet-week3</a>
<br/>
<a href="register.jsp">Register-getParameter-week3</a>
<br/>
<a href="config">Config Parameter-week4</a>
<br/>
<a href="register.jsp">Register JDBC-week4</a>
<br/>
<a href="index.jsp">Include-week5</a>
<br/>
<a href="login.jsp">Login-week5</a>
<form method="post" action="register">

    username<input type="text" name="username" ><br/>
    password<input type="password" name="password" ><br/>
    Email<input type="text" name="email"><br/>
    Gender:<input name="gender" type="radio" value="male">male<input name="gender" type="radio"value="Female">Female<br/>
    Date of Birth<input type="text" name="birthDate"><br/>
    <input type="submit" value="Register"/>
</form>
</body>
</html>
<%@ include file="footer.jsp"%>
