package com.Songzhiyongweek5.demo.demo;

/*
 * coding: UTF-8
 * @Author: 宋智勇
 * @File: JDBCServletContextLister.java
 * @Date: 2022/4/5 22:57
 * @Software: IDEA
 */




import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCServletContextLister implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String driver = context.getInitParameter("driver");
        String url = context.getInitParameter("url");
        String username = context.getInitParameter("username");
        String password = context.getInitParameter("password");
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url,username,password);
            System.out.println("i am in contextInitialized"+con);
            context.setAttribute("con",con);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println(" i am in contextDestroyed()");
        servletContextEvent.getServletContext().removeAttribute("con");
    }
}