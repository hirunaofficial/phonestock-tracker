package dev.hiruna.phone;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchController {

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<TBLPhones> tblPhones;

    @FXML
    private TableColumn<TBLPhones, String> colBrand;

    @FXML
    private TableColumn<TBLPhones, String> colModel;

    @FXML
    private TableColumn<TBLPhones, Integer> colQty;

    @FXML
    private TableColumn<TBLPhones, String> colColor;

    @FXML
    private TableColumn<TBLPhones, Integer> colRam;

    @FXML
    private TableColumn<TBLPhones, Integer> colStorage;

    @FXML
    private TableColumn<TBLPhones, Double> colPrice;

    @FXML
    private TextField txtSearch;

    @FXML
    void initialize() {
        txtSearch.setOnKeyPressed(this::handleKeyPressed);
    }

    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            btnSearch(new ActionEvent());
        }
    }

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
    void btnSearch(ActionEvent event) {
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        colRam.setCellValueFactory(new PropertyValueFactory<>("ram"));
        colStorage.setCellValueFactory(new PropertyValueFactory<>("storage"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        tblPhones.setItems(getPhoneData(txtSearch.getText().toLowerCase()));
    }

    private ObservableList<TBLPhones> getPhoneData(String searchText) {
        ObservableList<TBLPhones> phoneList = FXCollections.observableArrayList();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM phones WHERE LOWER(brand) LIKE ? OR LOWER(model) LIKE ? ORDER BY brand ASC";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + searchText + "%");
            preparedStatement.setString(2, "%" + searchText + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                TBLPhones phone = new TBLPhones(
                        resultSet.getInt("id"),
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        resultSet.getInt("qty"),
                        resultSet.getString("color"),
                        resultSet.getInt("ram"),
                        resultSet.getInt("storage"),
                        resultSet.getDouble("price")
                );
                phoneList.add(phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return phoneList;
    }
}
