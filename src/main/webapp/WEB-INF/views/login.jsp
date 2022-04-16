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
<%
    if (!(request.getAttribute("mesage") ==null)){
        out.print(request.getAttribute("message"));
    }
%>
<%
    Cookie[] cookies = request.getCookies();
    String username="";
    String password="";
    String rememberValue="";
    if (cookies!=null){
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("cUsername")){
                username=cookie.getValue();
            }
            if (cookie.getName().equals("cPassword")){
                password=cookie.getValue();
            }
            if (cookie.getName().equals("cRememberMe")){
                rememberValue=cookie.getValue();
            }
            System.out.println(cookie);
            System.out.println(cookie.getName());
        }
    }
%>
<br/>
<%--<form method="post" action="Login">--%>

<%--    Username<input type="text" name="Username" ><br/>--%>
<%--    Password<input type="password" name="Password" ><br/> --%>
<%--    <input type="submit" value="Login"/>--%>
<%--</form>--%>
<form method="post" action="Login">
    <%--<form method="post" action="${pageContext.request.contextPath}/login">--%>
    <div>
        <span>Username：</span><input type="text" name="username" value="<%=username%>">
    </div>
    <div>
        <span>Password：</span><input type="password" name="password"  value="<%=password%>">
    </div>
    <div><input type="checkbox" name="rememberMe" value="1" <%=rememberValue.equals("1") ? "checked" : ""%> />rememberMe</div>
    <div>
        <input type="submit" value="login">
    </div>
</form>
</div>
</body>
</html>
<%@ include file="footer.jsp"%>