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
<form>
    <tr>
        <td bgcolor="#ffe4c4">
    <input type="text" name="Username" value="Username" size="25"><br/>
    <input type="text" name="Password" value="Password" size="25"><br/>
    <input type="text" name="Email" value="Email" size="25"><br/>
    Gender:<input name="sex" type="radio" value="Male"/>male
    <input name="sex" type="radio" value="Female"/>Female<br/>
    <input type="text" name="Date of Brith(yyyy-mm-dd)" value="Date of Brith(yyyy-mm-dd)" size="25"><br/>
    <br/>
            <input type="submit" value="Register"/>
        </td>
    </tr>
</form>
</body>
</html>
