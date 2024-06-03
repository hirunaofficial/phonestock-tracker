package dev.hiruna.phone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class MainController {

    @FXML
    private AnchorPane root;

    @FXML
    private Label txtBrands;

    @FXML
    private Label txtModels;

    @FXML
    private Label txtStock;

    @FXML
    private Label txtStockAlerts;

    @FXML
    void initialize() {
        // Set the initial text values from the database
        txtBrands.setText(String.valueOf(DatabaseConnection.getTotalBrands()));
        txtModels.setText(String.valueOf(DatabaseConnection.getTotalModels()));
        txtStock.setText(String.valueOf(DatabaseConnection.getTotalStock()));

        // Get and display the details of phones with the lowest stock
        try (ResultSet lowestStockPhones = DatabaseConnection.getLowestStockPhones()) {
            if (lowestStockPhones != null) {
                StringBuilder stockAlerts = new StringBuilder();
                while (lowestStockPhones.next()) {
                    String brand = lowestStockPhones.getString("brand");
                    String model = lowestStockPhones.getString("model");
                    int qty = lowestStockPhones.getInt("qty");
                    stockAlerts.append("- ").append(brand).append(" ").append(model).append(" stock is running low!\n");
                    stockAlerts.append("   (Only ").append(qty).append(" units remaining in stock)\n\n");
                }
                txtStockAlerts.setText(stockAlerts.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnAddPhone(ActionEvent event) throws IOException {
        Stage stage = (Stage) this.root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("add-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.getIcons().add(new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("assets/icon.png")))); // Set the icon of the application window
        stage.setResizable(false); // Disable window resize
        stage.setTitle("Add New Phone - PhoneStock Tracker (Track Every Phone, Every Time)");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnDeletePhone(ActionEvent event) throws IOException {
        Stage stage = (Stage) this.root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("delete-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.getIcons().add(new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("assets/icon.png")))); // Set the icon of the application window
        stage.setResizable(false); // Disable window resize
        stage.setTitle("Delete Phone - PhoneStock Tracker (Track Every Phone, Every Time)");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnSearch(ActionEvent event) throws IOException {
        Stage stage = (Stage) this.root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("search-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.getIcons().add(new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("assets/icon.png")))); // Set the icon of the application window
        stage.setResizable(false); // Disable window resize
        stage.setTitle("Smart Search - PhoneStock Tracker (Track Every Phone, Every Time)");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnUpdatePhone(ActionEvent event) {

    }

}