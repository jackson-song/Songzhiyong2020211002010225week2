package com.Songzhiyong.controller;

import com.Songzhiyong.dao.ProductDao;
import com.Songzhiyong.model.Category;
import com.Songzhiyong.model.Product;



import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/shop")
public class ShopServlet extends HttpServlet {
    Connection con = null;

    @Override
    public void init() throws ServletException {
        con = (Connection) getServletContext().getAttribute("connection");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Category category = new Category();
        List<Category> categoryList = null;
        try {
            categoryList = category.findAllCateGory(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("categoryList", categoryList);
        ProductDao productDao = new ProductDao();
        List<Product> productList = null;
        if (request.getParameter("categoryId") == null) {
            try {
                productList = productDao.findAll(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            try {
                productList = productDao.findByCategoryId(categoryId, con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("productList", productList);
        String path = "/WEB-INF/views/shop.jsp";
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}