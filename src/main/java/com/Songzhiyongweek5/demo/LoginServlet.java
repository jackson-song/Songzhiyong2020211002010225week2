package com.Songzhiyongweek5.demo;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    Connection con = null;
    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        String driver = context.getInitParameter("driver");
        String url = context.getInitParameter("url");
        String username = context.getInitParameter("username");
        String password = context.getInitParameter("password");
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,username,password);
            System.out.println("Connection -->"+con);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ///TODO 1:GET 4 CONTEXT PARAM -DRIVER ,URL,USERNAME,PASSWORD
        //TODO 2:GET JDBC CONNECTION
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        PrintWriter writer = response.getWriter();
        StringBuilder sql = new StringBuilder();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            sql.append("select * from user where username = ? and password = ?");
            ps = con.prepareStatement(sql.toString());
            ps.setString(1,username);
            ps.setString(2,password);
            rs = ps.executeQuery();
            if (rs.next()) {
                writer.println("<h1>Login Success!!!</h1>");
                writer.println("<h1>Welcome&nbsp" + username+"</h1>");
                return;
            }
            else {
                writer.println("<h1>Login Fail!!!</h1>");
                writer.println("<h1>username or password error!</h1>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(rs!=null){

                    rs.close();

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(ps!=null){

                    ps.close();

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
        //        TODO 3:GET REQUEST PARAMETER -USERNAME,PASSWORD
//        TODO 4:VALIDATE USER- SELECT* FORM USERTABLE  WHERE USERNAME ="XXX"
//         AND PASSWORD ="YYY"
//        TODO 5: CHECK IF(USER IS VALID){
//        OUT.PRINTLN("LOGIN SUCCESS")
        this.doGet(request, response);
    }
    @Override
    public void destroy() {
        super.destroy();
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}