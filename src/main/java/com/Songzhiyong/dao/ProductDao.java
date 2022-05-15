package com.Songzhiyong.dao;



import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductDao implements IProductDao {
    @Override
    public int save(Product product, Connection con) throws SQLException {
        int n = 0;
        String sql = "insert into product(ProductName,ProductDescription,Picture,price,CategoryID) values(?,?,?,?,?)";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setString(1, product.getProductName());
        pt.setString(2, product.getProductDescription());
        if (product.getPicture() != null) {
            //for sql server
            pt.setBinaryStream(3, product.getPicture());
            //for mysql
            //   pt.setBlob(3, product.getPicture());
        }
        pt.setDouble(4, product.getPrice());
        pt.setInt(5, product.getCategoryId());
        n = pt.executeUpdate();
        if (n > 0) {
            return n;
        }
        return 0;
    }//end save

    @Override
    public int delete(Integer productId, Connection con) throws SQLException {
        String sql = "delete from product where productId=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, productId);
        int result = preparedStatement.executeUpdate();
        if (result > 0) {
            return result;
        }
        return 0;
    }

    @Override
    public int update(Product product, Connection con) throws SQLException {
        String sql = "update product set productName=?,productDescription=?,picture=?,price=?,categoryId=? where productId=?";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setString(1, product.getProductName());
        pt.setString(2, product.getProductDescription());
        if(product.getPicture()!=null) {
            pt.setBinaryStream(3, product.getPicture());
        }
        pt.setDouble(4, product.getPrice());
        pt.setInt(5, product.getCategoryId());
        pt.setInt(6,product.getProductId());
        int result = pt.executeUpdate();
        if (result > 0) {
            return result;
        }
        return 0;
    }

    @Override
    public Product findById(Integer productId, Connection con) throws SQLException {
        String sql="select * from product where productId=?";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setInt(1, productId);
        ResultSet resultSet = pt.executeQuery();
        Product product=null;
        if (resultSet.next()){
            product.setProductId(resultSet.getInt("productId"));
            product.setCategoryId(resultSet.getInt("categoryId"));
            product.setProductName(resultSet.getString("productName"));
            product.setProductDescription(resultSet.getString("productDescription"));
            if (resultSet.getBinaryStream("picture")!=null){
                product.setPicture(resultSet.getBinaryStream("picture"));
            }
            product.setPrice(resultSet.getDouble("price"));
        }
        return product;
    }

    @Override
    public List<Product> findByCategoryId(int categoryId, Connection con) throws SQLException {
        String sql="select * from product where categoryId=?";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setInt(1, categoryId);
        ResultSet resultSet = pt.executeQuery();
        List<Product> productList=null;
        Product product=null;
        while (resultSet.next()){
            product.setProductId(resultSet.getInt("productId"));
            product.setCategoryId(resultSet.getInt("categoryId"));
            product.setProductName(resultSet.getString("productName"));
            product.setProductDescription(resultSet.getString("productDescription"));
            if (resultSet.getBinaryStream("picture")!=null){
                product.setPicture(resultSet.getBinaryStream("picture"));
            }
            product.setPrice(resultSet.getDouble("price"));
            productList.add(product);
        }
        return productList;
    }

    @Override
    public List<Product> findByPrice(double minPrice, double maxPrice, Connection con) throws SQLException {
        String sql="select * from product where price<=? and price>=?";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setDouble(1, minPrice);
        pt.setDouble(2, maxPrice);
        ResultSet resultSet = pt.executeQuery();
        List<Product> productList=null;
        Product product=null;
        while (resultSet.next()){
            product.setProductId(resultSet.getInt("productId"));
            product.setCategoryId(resultSet.getInt("categoryId"));
            product.setProductName(resultSet.getString("productName"));
            product.setProductDescription(resultSet.getString("productDescription"));
            if (resultSet.getBinaryStream("picture")!=null){
                product.setPicture(resultSet.getBinaryStream("picture"));
            }
            product.setPrice(resultSet.getDouble("price"));
            productList.add(product);
        }
        return productList;
    }

    @Override
    public List<Product> findAll(Connection con) throws SQLException {
        String sql="select * from product";
        PreparedStatement pt = con.prepareStatement(sql);
        ResultSet resultSet = pt.executeQuery();
        List<Product> productList=null;
        Product product=null;
        while (resultSet.next()){
            product.setProductId(resultSet.getInt("productId"));
            product.setCategoryId(resultSet.getInt("categoryId"));
            product.setProductName(resultSet.getString("productName"));
            product.setProductDescription(resultSet.getString("productDescription"));
            if (resultSet.getBinaryStream("picture")!=null){
                product.setPicture(resultSet.getBinaryStream("picture"));
            }
            product.setPrice(resultSet.getDouble("price"));
            productList.add(product);
        }
        return productList;

    }

    @Override
    public List<Product> findByProductName(String productName, Connection con) throws SQLException {
        String sql="select * from product where productName=?";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setString(1, productName);
        ResultSet resultSet = pt.executeQuery();
        List<Product> productList=null;
        Product product=null;
        while (resultSet.next()){
            product.setProductId(resultSet.getInt("productId"));
            product.setCategoryId(resultSet.getInt("categoryId"));
            product.setProductName(resultSet.getString("productName"));
            product.setProductDescription(resultSet.getString("productDescription"));
            if (resultSet.getBinaryStream("picture")!=null){
                product.setPicture(resultSet.getBinaryStream("picture"));
            }
            product.setPrice(resultSet.getDouble("price"));
            productList.add(product);
        }
        return productList;
    }

    @Override
    public List<Product> getPicture(Integer productId, Connection con) throws SQLException {
        String sql="select * from product where productId=?";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setInt(1, productId);
        ResultSet resultSet = pt.executeQuery();
        List<Product> productList=null;
        Product product=null;
        while (resultSet.next()){
            product.setProductId(resultSet.getInt("productId"));
            product.setCategoryId(resultSet.getInt("categoryId"));
            product.setProductName(resultSet.getString("productName"));
            product.setProductDescription(resultSet.getString("productDescription"));
            if (resultSet.getBinaryStream("picture")!=null){
                product.setPicture(resultSet.getBinaryStream("picture"));
            }
            product.setPrice(resultSet.getDouble("price"));
            productList.add(product);
        }
        return productList;
    }
}