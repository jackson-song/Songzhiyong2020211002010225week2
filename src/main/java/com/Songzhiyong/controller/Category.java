package com.Songzhiyong.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Category {
    private int categoryID;
    private String categoryName;
    private String description;
    private boolean active;

    public Category() {}

    public Category(int categoryID, String categoryName, String description, boolean active) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.description = description;
        this.active = active;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryID=" + categoryID +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                '}';
    }
    //methods
    public static List<Category> findAllCategory(Connection con) {
        List<Category> list = new ArrayList<Category>();
        String queryString = "select * from Category";
        try{
            PreparedStatement ps = con.prepareStatement(queryString);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Category c = new Category();
                c.setCategoryID(rs.getInt("CategoryID"));
                c.setCategoryName(rs.getString("CategoryName"));
                c.setDescription(rs.getString("Description"));
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static String findByCategoryID(Connection con,int categoryID){
        String categoryName = null;
        try{
            String queryString = "select CategoryName from Category where CategoryID=?";
            PreparedStatement ps = con.prepareStatement(queryString);
            ps.setInt(1,categoryID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                categoryName = rs.getString("CategoryName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryName;
    }
}