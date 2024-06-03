package dev.hiruna.phone;

import java.sql.*;

public class DatabaseConnection {

    public static Connection getConnection() {
        Connection connection = null;
        try {
            String url = "jdbc:mysql://localhost:3306/phonestock_tracker";
            String user = "root";
            String password = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static int getTotalBrands() {
        String query = "SELECT COUNT(DISTINCT brand) AS totalBrands FROM phones";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("totalBrands");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getTotalModels() {
        String query = "SELECT COUNT(DISTINCT model) AS totalModels FROM phones";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("totalModels");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getTotalStock() {
        String query = "SELECT SUM(qty) AS totalStock FROM phones";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("totalStock");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static ResultSet getLowestStockPhones() {
        String query = "SELECT brand, model, qty FROM phones ORDER BY qty ASC LIMIT 4";
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
