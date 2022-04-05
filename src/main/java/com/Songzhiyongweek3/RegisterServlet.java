package com.Songzhiyongweek3;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//@WebServlet(urlPatterns = {"/register"},loadOnStartup =1)
public class RegisterServlet extends HttpServlet {
    Connection con=null;
    @Override
    public void init() throws ServletException{
        super.init();
    }
//     con=(Connection) getSerletContext().getAttribute("con")
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username =request.getParameter("username");
        String password =request.getParameter("password");
        String email =request.getParameter("email");
        String gender =request.getParameter("gender");
        String birthDate =request.getParameter("birthDate");

        PrintWriter writer = response.getWriter();
        writer.println("<br>username:"+username);
        writer.println("<br>password:"+password);
        writer.println("<br>email:"+email);
        writer.println("<br>gender:"+gender);
        writer.println("<br>birthDate:"+birthDate);
        writer.close();
        String sql="insert into student values(?,?,?,?,?)";
        try {
            //insert data
            PreparedStatement preparedStatement= null;
            try {
                preparedStatement = con.prepareStatement(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,email);
            preparedStatement.setString(4,gender);
            preparedStatement.setString(5,birthDate);
            preparedStatement.execute();
            //get data and print data
//            PrintWriter out=response.getWriter();
//            sql="select * from student";
//            stmt = con.createStatement();
//            rs = stmt.executeQuery(sql);
//            out.println("id\t\tusername\t\tpassword\t\temail\t\tgender\t\tbirthdate");
//            out.println("<html><head><title></title></head><table border=1><tr>");
//            out.println("<th>id</th><th>username</th><th>password</th><th>Email</th><th>gender</th><th>birthdate</th></tr>");
//            while (rs.next()) {
//                int ID = rs.getInt("id");
//                String Username = rs.getString("username");
//                String Password = rs.getString("password");
//                String Email = rs.getString("email");
//                String Gender = rs.getString("gender");
//                Date Birthdate = rs.getDate("birthdate");
//                PrintWriter writer = response.getWriter();
//                out.println(ID + "\t\t" + Username + "\t\t" + Password + "\t\t" + Email + "\t\t" + Gender + "\t\t" + Birthdate);
//                out.println("<tr>");
//                out.println("<td>" + rs.getInt("id") + "</td>");
//                out.println("<td>" + rs.getString("username") + "</td>");
//                out.println("<td>" + rs.getString("password") + "</td>");
//                out.println("<td>" + rs.getString("email") + "</td>");
//                out.println("<td>" + rs.getString("gender") + "</td>");
//                out.println("<td>" + rs.getDate("birthdate") + "</td>");
//                out.println("</tr>");
//            }
//            out.println("</table></body></html>");
//            stmt.close();
//            con.close();
            //request.setAttribute("rsname",rs);
            //request.getRequestDispatcher("userlist.jsp").forward(request,response);
            //System.out.println("I am in RegisterServlet-->do post()-->after forward()");
            response.sendRedirect("login.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}