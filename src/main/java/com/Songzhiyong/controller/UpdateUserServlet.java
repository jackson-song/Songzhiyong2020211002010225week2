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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/updateUser")
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/updateUser.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("Email");
        String gender = req.getParameter("gender");
        String birthdate = req.getParameter("Birthdate");
        Users user = new Users();
        try {
            Users users;
            users.setId(Integer.parseInt(id));
            users.setUsername(username);
            users.setPassword(password);
            user.setEmail(email);
            user.setGender(gender);
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birthdate);
            user.setBirthDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        IUserDao userDao = new UserDao();
        try {
            userDao.updateUser(connection, user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("accountDetails").forward(req,resp);
    }
}