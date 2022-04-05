package com.Songzhiyongweek6.demo;
/*
 * coding: UTF-8
 * @Author: 宋智勇
 * @File: ${NAME}.java
 * @Date: 2022/4/5 22:15
 * @Software: IDEA
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RedirectServlet", value = "/Redirect")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("before Redirect");
        //http://localhost:8080/demo_war_exploded/
        //http://localhost:8080/demo_war_exploded/index.jsp
        //http://localhost:8080/demo_war_exploded/Redirect
        response.sendRedirect("index.jsp");
        System.out.println("after Redirect");
//        response.sendRedirect("/index.jsp");

        response.sendRedirect("https://www.baidu.com/");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }
}
