<%--
  Created by IntelliJ IDEA.
  User: 32312
  Date: 2022/3/29
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>
This is my page

<html>
<head>
    <title>Login Page</title>
</head>
<body>
<h1>Login</h1>
<form method="post" action="Login">

    Username<input type="text" name="Username" ><br/>
    Password<input type="password" name="Password" ><br/>
    <input type="submit" value="Login"/>
</form>
</body>
</html>
<%@ include file="footer.jsp"%>