package com.Songzhiyong.controller;
/*
 * coding: UTF-8
 * @Author: 宋智勇
 * @File: ${NAME}.java
 * @Date: 2022/4/20 0:14
 * @Software: IDEA
 */

import com.Songzhiyong.dao.IUserDao;
import com.Songzhiyong.dao.UserDao;
import com.Songzhiyong.model.Users;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "UpdateUserServlet", value = "/UpdateUser")
public class UpdateUserServlet extends HttpServlet {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    @Override
    public void init() throws ServletException {
        connection = (Connection) getServletContext().getAttribute("connection");
        System.out.println(connection);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/updateUser.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("Email");
        String gender = request.getParameter("gender");
        String birthdate = request.getParameter("Birthdate");
        Users user = new Users();
        try {
            user.setId(Integer.parseInt(id));
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setGender(gender);
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birthdate);
            user.setBirth(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        IUserDao userDao = new UserDao();
        try {
            userDao.updateUser(connection, user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("WEB-INF/views/userInfo.jsp").forward(request,response);
    }
}
