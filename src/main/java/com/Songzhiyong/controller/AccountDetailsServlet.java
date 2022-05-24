package com.Songzhiyong.controller;


import com.Songzhiyong.dao.UserDao;
import com.Songzhiyong.model.Users;

import javax.persistence.criteria.Order;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/accountDetails")
public class AccountDetailsServlet extends HttpServlet {
    private Connection con = null;

    @Override
    public void init() throws ServletException {
        con = (Connection) getServletContext().getAttribute("connection");
    }

    @Override
    protected <OrderDao> void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            Users user = (Users) session.getAttribute("user");
            int id=user.getId();
            UserDao userDao = new UserDao();
            try {
                userDao.findById(con,id);
                request.setAttribute("user",user);
                OrderDao orderDao = new OrderDao();
                List<Order> orderList = orderDao.findByUserId(con, id);
                request.setAttribute("orderList",orderList);
                request.getRequestDispatcher("WEB-INF/views/admin/addProduct.jsp").forward(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            response.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}