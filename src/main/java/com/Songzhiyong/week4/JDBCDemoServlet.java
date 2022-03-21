package com.Songzhiyong.week4;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*@WebServlet(name ="JDBCemoServlet",urlPatterns = {"/jdbc"}
,initParams = {
        @WebInitParam(name="driver",value = "com.mysql.cj.jdbc.Driver"),
        @WebInitParam(name="url",value = "jdbc:mysql://localhost:3306/userdb"),
        @WebInitParam(name="username",value = "root"),
        @WebInitParam(name="password",value = "root"),
},
        loadOnStartup = 1
)*/
@WebServlet(urlPatterns = {"/jdbc"},loadOnStartup = 1)
public class JDBCDemoServlet extends HttpServlet {
    Connection con= null;
    @Override
    public void init() throws ServletException {
        super.init();

//        String driver="com.mysql.cj.jdbc.Driver";
//        String url="jdbc:mysql://localhost:3306/userdb";
//        String username="root";
//        String password="root";
//        ServletConfig config=getServletConfig();
//        String driver = config.getInitParameter("driver");
//        String url = config.getInitParameter("url");
//        String username = config.getInitParameter("username");
//        String password = config.getInitParameter("password");
        ServletContext context=getServletContext();
        String driver=context.getInitParameter("driver");
        String url=context.getInitParameter("url");
        String username=context.getInitParameter("username");
        String password=context.getInitParameter("password");

        try {
            Class.forName(driver);
            con=DriverManager.getConnection(url,username,password);
            System.out.println("Connection -->in JDBCDemoServlet"+con);
        } catch (ClassNotFoundException  | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
