package com.Songzhiyongweek5.demo;
import com.Songzhiyong.dao.UserDao;
import com.Songzhiyong.model.Users;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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
        request.getRequestDispatcher("WEB-INF/views/Login.jsp").forward(request, response);
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        this.doGet(request, response);
//                TODO 3:GET REQUEST PARAMETER -USERNAME,PASSWORD
//        TODO 4:VALIDATE USER- SELECT* FORM USERTABLE  WHERE USERNAME ="XXX"
//         AND PASSWORD ="YYY"
//        TODO 5: CHECK IF(USER IS VALID){
//        OUT.PRINTLN("LOGIN SUCCESS")


//        this.doGet(request, response);
//    }
//    @Override
//    public void destroy() {
//        super.destroy();
//        try {
//            con.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        PrintWriter writer = response.getWriter();
//        String sql = "Select * from student where username = ? and password = ?";
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            try {
//                ps = con.prepareStatement(sql);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try {
//                ps.setString(1, username);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try {
//                ps.setString(2, password);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try {
//                rs = ps.executeQuery();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try {
//                if (rs.next()) {
//                    try {
//                        request.setAttribute("id", rs.getInt("id"));
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                    request.setAttribute("username", username);
//                    request.setAttribute("password", password);
//                    try {
//                        request.setAttribute("email", rs.getString("email"));
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        request.setAttribute("gender", rs.getString("gender"));
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        request.setAttribute("birthdate", rs.getString("birthdate"));
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                    request.getRequestDispatcher("userInfo.jsp").forward(request, response);
//                } else {
//                    request.setAttribute("message", "Username or Password Error!");
//                    request.getRequestDispatcher("login.jsp").forward(request, response);
//                }
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            } catch (ServletException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } finally {
//            try {
//                if(rs!=null){
//
//                    rs.close();
//
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try {
//                if(ps!=null){
//
//                    ps.close();
//
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        PrintWriter writer = response.getWriter();
        UserDao userdao = new UserDao();

        try {
            Users user = userdao.findByUsernamePassword(con, username, password);
            if (user != null) {
                System.out.println("rememberMe===>"+request.getParameter("rememberMe"));
                if (request.getParameter("rememberMe")!=null){
                    Cookie usernameCookie = new Cookie("cUsername", user.getUsername());
                    Cookie passwordCookie = new Cookie("cPassword", user.getPassword());
                    Cookie rememberMeCookie = new Cookie("cRememberMe", request.getParameter("rememberMe"));
                    usernameCookie.setMaxAge(60);
                    passwordCookie.setMaxAge(60);
                    rememberMeCookie.setMaxAge(60);
                    response.addCookie(usernameCookie);
                    response.addCookie(passwordCookie);
                    response.addCookie(rememberMeCookie);
                }
                HttpSession session = request.getSession();
                System.out.println("session id-->"+session.getId());
                session.setMaxInactiveInterval(10);
                session.setAttribute("user",user);
                request.setAttribute("user", user);
                Cookie c = new Cookie("sessioned",""+user.getId());
                c.setMaxAge(10*60);
                response.addCookie(c);
                request.getRequestDispatcher("WEB-INF/views/userInfo.jsp").forward(request, response);

            } else {
                request.setAttribute("message", "Username or Password error!!!");
                request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
