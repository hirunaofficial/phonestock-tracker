package dev.hiruna.phone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddController {

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtColor;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtRam;

    @FXML
    private TextField txtStorage;

    @FXML
    void btnBack(MouseEvent event) throws IOException {
        Stage stage = (Stage) this.root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("assets/icon.png"))); // Set the icon of the application window
        stage.setResizable(false); // Disable window resize
        stage.setTitle("PhoneStock Tracker (Track Every Phone, Every Time)");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnAddPhone(ActionEvent event) {
        String brand = txtBrand.getText().trim();
        String model = txtModel.getText().trim();
        String color = txtColor.getText().trim();
        String qtyText = txtQty.getText().trim();
        String ramText = txtRam.getText().trim();
        String storageText = txtStorage.getText().trim();
        String priceText = txtPrice.getText().trim();

        if (brand.isEmpty() || model.isEmpty() || color.isEmpty() || qtyText.isEmpty() || ramText.isEmpty() || storageText.isEmpty() || priceText.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter all details");
            return;
        }

        int qty, ram, storage;
        double price;

        try {
            qty = Integer.parseInt(qtyText);
            ram = Integer.parseInt(ramText);
            storage = Integer.parseInt(storageText);
            price = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter valid numerical values for Quantity, RAM, Storage, and Price");
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO phones (brand, model, qty, color, ram, storage, price) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, brand);
            preparedStatement.setString(2, model);
            preparedStatement.setInt(3, qty);
            preparedStatement.setString(4, color);
            preparedStatement.setInt(5, ram);
            preparedStatement.setInt(6, storage);
            preparedStatement.setDouble(7, price);

            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Phone added successfully!");
                clearFields();
            } else {
                showAlert(Alert.AlertType.ERROR, "Failed", "Failed to add phone");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred while adding the phone");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        txtBrand.clear();
        txtModel.clear();
        txtColor.clear();
        txtQty.clear();
        txtRam.clear();
        txtStorage.clear();
        txtPrice.clear();
    }
}
