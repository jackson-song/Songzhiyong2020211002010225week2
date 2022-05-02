package com.Songzhiyong.controller;

import com.Songzhiyong.dao.ProductDao;
import jdk.jfr.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AddProductServlet", value = "/admin/addProduct")
@MultipartConfig(maxFileSize =16177215)
public class AddProductServlet extends HttpServlet {
    Connection con = null;

    public void init() {
        con = (Connection) getServletContext().getAttribute("connection");
        System.out.println(con);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Category> categoryList = Category.findAllCateGory(con);
            request.setAttribute("categoryList", categoryList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String path = "/WEB-INF/views/admin/addProduct.jsp";
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected <Product> void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("productName");
        Double price = request.getParameter("price") != null ? Double.parseDouble(request.getParameter("price")) : 0.0;
        Integer categoryId = request.getParameter("categoryId") != null ? Integer.parseInt(request.getParameter("categoryId")) : 8;
        String productDescription = request.getParameter("productDescription");
        InputStream inputStream = null;
        Part fileParts = request.getPart("picture");
        if (fileParts != null) {
            inputStream = fileParts.getInputStream();
        }
        Product product = new Product();
        product.setProductName(productName);
        product.setProductDescription(productDescription);
        product.setPrice(price);
        product.setPicture(inputStream);
        product.setCategoryId(categoryId);
        ProductDao productDao = new ProductDao();
        try {
            int n = productDao.save(product, con);
            if (n > 0) {
                response.sendRedirect("productList");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}