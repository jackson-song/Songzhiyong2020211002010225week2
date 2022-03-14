<%--
  Created by IntelliJ IDEA.
  User: 32312
  Date: 2022/3/8
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--<a href="http://www.ecjtu.jx.cn/">go to ecjtu</a>--%>
<p>New User Registration!</p>
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
