package com.Songzhiyong.controller;

import com.Songzhiyong.dao.UserDao;
import com.Songzhiyong.model.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    Connection con = null;
    @Override
    public void init() throws ServletException {
        super.init();
        /*ServletContext context = getServletContext();
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
        }*/
        con = (Connection) getServletContext().getAttribute("con");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDao userDao = new UserDao();
        try {
            Users users = userDao.findByUsernamePassword(con,username,password);
            if (users != null) {
                //use cookie for session
                /*Cookie c = new Cookie("sessionId",""+users.getId());
                c.setMaxAge(10*60);
                response.addCookie(c);*/
                //add code for remember me
                String rememberMe = request.getParameter("rememberMe");
                if (rememberMe!=null && rememberMe.equals("1")) {
                    Cookie usernameCookie = new Cookie("cUsername",users.getUsername());
                    Cookie passwordCookie = new Cookie("cPassword",users.getPassword());
                    Cookie rememberMeCookie = new Cookie("cRememberMe",rememberMe);
                    usernameCookie.setMaxAge(20);//only for test --- otherwise set 60*60*24*15
                    passwordCookie.setMaxAge(20);
                    rememberMeCookie.setMaxAge(20);
                    response.addCookie(usernameCookie);
                    response.addCookie(passwordCookie);
                    response.addCookie(rememberMeCookie);
                }
                //use httpSession for session
                HttpSession session = request.getSession();//create session if session not exist -- otherwise return existing session
                System.out.println("sessionId --->"+session.getId());//check
                session.setMaxInactiveInterval(10);
                //change request(one page) to session -- can get session attribute in many jsp
                session.setAttribute("user",users);
                //request.setAttribute("user", users);
                request.getRequestDispatcher("WEB-INF/views/userInfo.jsp").forward(request,response);
            } else {
                request.setAttribute("message","Username or Password Error!");
                request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*PrintWriter writer = response.getWriter();
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
                *//*writer.println("<h1>Login Success!!!</h1>");
                writer.println("<h1>Welcome&nbsp" + username+"</h1>");*//*
                request.setAttribute("id",rs.getInt("id"));
                request.setAttribute("username",rs.getString("username"));
                request.setAttribute("password",rs.getString("password"));
                request.setAttribute("email",rs.getString("email"));
                request.setAttribute("gender",rs.getString("gender"));
                request.setAttribute("birth",rs.getString("birth"));
                request.getRequestDispatcher("userInfo.jsp").forward(request,response);
                return;
            }
            else {
               *//* writer.println("<h1>Login Fail!!!</h1>");
                writer.println("<h1>username or password error!</h1>");*//*
               request.setAttribute("message","Username or Password Error!");
               request.getRequestDispatcher("login.jsp").forward(request,response);
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
        */
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);
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