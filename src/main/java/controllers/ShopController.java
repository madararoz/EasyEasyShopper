package controllers;

import entities.Product;
import entity.User;
import repository.DBHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ShopController {
    User user = new User();

    DBHandler dbHandler = new DBHandler();
    private Object userType;

    public static ArrayList<Product> getProducts() throws SQLException {
        DBHandler dbHandler = new DBHandler();
        ArrayList<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM products";
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);
        ResultSet result = preparedStatement.executeQuery();

        while(result.next()) {
            productList.add(new Product(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getDouble("price"),
                    result.getInt("quantity"),
                    result.getDouble("cost")
            ));
        }
        return productList;
    }


    public ArrayList<Product> getProductListFromDB() throws SQLException {
        ArrayList<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM products";
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);
        ResultSet result = preparedStatement.executeQuery();

        while(result.next()) {
            productList.add(new Product(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getDouble("price"),
                    result.getInt("quantity"),
                    result.getDouble("cost")
            ));
        }
        return productList;


    }

    public void addProductToDB(Product product) throws SQLException {
        Connection connection = dbHandler.getConnection();
        String query = "INSERT INTO products (name, price, quantity, cost)" + "VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setDouble(2, product.getPrice());
        preparedStatement.setInt(3,product.getQuantity());
        preparedStatement.setDouble(4,product.getCost());

        preparedStatement.execute();
    }


    public void addUserToDB(User user) throws SQLException {
        Connection connection = dbHandler.getConnection();
        String query = "INSERT INTO users (name, email, password, balance)" + "VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1,user.getName());
        preparedStatement.setString(2,user.getEmail());
        preparedStatement.setString(3,user.getPassword());
        preparedStatement.setDouble(4,user.getBalance());
        preparedStatement.execute();

    }


    public String checkPassword(String password) throws SQLException {
        Connection connection = dbHandler.getConnection();
        String query = "SELECT password FROM users WHERE password=" + "?";
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);
        preparedStatement.setString(1, password);
        ResultSet result = preparedStatement.executeQuery();
        if (result.next()) {
            password = result.getString("password");

        } else {

            return null;
        }
        return password;
    }


    public String checkEmail(String email) throws SQLException {
        Connection connection = dbHandler.getConnection();
        String query = "SELECT email FROM users WHERE email = ?";
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);
        preparedStatement.setString(1, email);
        ResultSet result = preparedStatement.executeQuery();
        if (result.next()) {
            email = result.getString("email");
        } else {
            return null;
        }
        return email;
    }

    public void deleteProduct(int productId) throws SQLException {
        Connection connection = dbHandler.getConnection();
        String query = "DELETE FROM products where id = ?";
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);
        preparedStatement.setInt(1, productId);

        preparedStatement.execute();
    }

//    public void updateProduct(int productId, int newQuantity) throws SQLException {
//        Connection connection = dbHandler.getConnection();
//        String query = "UPDATE products SET quantity = ? where id = ?";
//        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);
//        preparedStatement.setInt(1, newQuantity);
//        preparedStatement.setInt(2, productId);
//
//        preparedStatement.execute();
//    }

    public void updateProduct(int productId, int newQuantity) throws SQLException {
        Connection connection = dbHandler.getConnection();
        String query = "UPDATE products SET quantity = quantity - (SELECT (quantity) FROM products WHERE product_id = ?)";
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);
        preparedStatement.setInt(1, productId);

        preparedStatement.execute();
    }



    public double getBalance(String email) throws SQLException {
        double balance = 0;
        Connection connection = dbHandler.getConnection();
        String query = "SELECT balance from users where email = ?";
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);
        preparedStatement.setString(1, email);

        ResultSet result = preparedStatement.executeQuery();
        if (result.next()) {
            balance = result.getDouble("balance");
        } else {
            return 0;
        }
        return balance;
    }


    public void updateUserBalance(double newBalance, User activeUser) throws SQLException {
        Connection connection = dbHandler.getConnection();
        String query = "UPDATE users SET balance = ? where email = ?";
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);
        preparedStatement.setDouble(1, newBalance);
        preparedStatement.setString(2, activeUser.getEmail());
        preparedStatement.executeUpdate();
    }

    public int getQuantity(int id) throws SQLException {
       int quantity = 0;
       Connection connection = dbHandler.getConnection();
       String query = "SELECT quantity from products where id = ?";
       PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);
       preparedStatement.setInt(1, id);

       ResultSet result = preparedStatement.executeQuery();
       if (result.next()) {
          quantity = result.getInt("quantity");
       } else {
        return 0;
       }
        return quantity;
    }

    public String getProductName(int id) throws SQLException {
        String name = "";
        Connection connection = dbHandler.getConnection();
        String query = "SELECT name from products where id = ?";
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);
        preparedStatement.setInt(1, id);

        ResultSet result = preparedStatement.executeQuery();
        if (result.next()) {
            name = result.getString("name");
        } else {
            return null;
        }
        return name;
    }
}