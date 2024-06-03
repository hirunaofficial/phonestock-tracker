package dev.hiruna.phone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateFormController {

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

    private TBLPhones phone;

    private UpdateController updateController;

    public void setPhone(TBLPhones phone) {
        this.phone = phone;
        populateFields();
    }

    public void setUpdateController(UpdateController updateController) {
        this.updateController = updateController;
    }

    private void populateFields() {
        if (phone != null) {
            txtBrand.setText(phone.getBrand());
            txtModel.setText(phone.getModel());
            txtQty.setText(String.valueOf(phone.getQty()));
            txtColor.setText(phone.getColor());
            txtRam.setText(String.valueOf(phone.getRam()));
            txtStorage.setText(String.valueOf(phone.getStorage()));
            txtPrice.setText(String.valueOf(phone.getPrice()));
        }
    }

    @FXML
    void btnUpdatePhone(ActionEvent event) {
        String brand = txtBrand.getText().trim();
        String model = txtModel.getText().trim();
        String color = txtColor.getText().trim();
        String qtyText = txtQty.getText().trim();
        String ramText = txtRam.getText().trim();
        String storageText = txtStorage.getText().trim();
        String priceText = txtPrice.getText().trim();

        if (brand.isEmpty() || model.isEmpty() || color.isEmpty() || qtyText.isEmpty() || ramText.isEmpty() || storageText.isEmpty() || priceText.isEmpty()) {
            showAlert("Form Error!", "Please enter all details");
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
            showAlert("Form Error!", "Please enter valid numerical values for Quantity, RAM, Storage, and Price");
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "UPDATE phones SET brand = ?, model = ?, qty = ?, color = ?, ram = ?, storage = ?, price = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, brand);
            preparedStatement.setString(2, model);
            preparedStatement.setInt(3, qty);
            preparedStatement.setString(4, color);
            preparedStatement.setInt(5, ram);
            preparedStatement.setInt(6, storage);
            preparedStatement.setDouble(7, price);
            preparedStatement.setInt(8, phone.getId());

            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                showAlert("Success", "Phone updated successfully!");
                Stage stage = (Stage) root.getScene().getWindow();
                stage.close();
                updateController.updateTable("");
            } else {
                showAlert("Failed", "Failed to update phone");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "An error occurred while updating the phone");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
