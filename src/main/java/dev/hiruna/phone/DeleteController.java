package dev.hiruna.phone;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DeleteController {

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
    private TableColumn<TBLPhones, Void> colAction; // Define colAction

    @FXML
    private TextField txtSearch;

    private ObservableList<TBLPhones> phoneList;

    @FXML
    void initialize() {
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        colRam.setCellValueFactory(new PropertyValueFactory<>("ram"));
        colStorage.setCellValueFactory(new PropertyValueFactory<>("storage"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colAction.setCellFactory(param -> new TableCell<TBLPhones, Void>() {
            private final Button btn = new Button("Delete");

            {
                btn.setOnAction((ActionEvent event) -> {
                    TBLPhones data = getTableView().getItems().get(getIndex());
                    deletePhone(data.getId());
                    phoneList.remove(data);
                });
                StackPane.setAlignment(btn, Pos.CENTER);
            }

            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    StackPane pane = new StackPane();
                    pane.getChildren().add(btn);
                    setGraphic(pane);
                }
            }
        });

        phoneList = FXCollections.observableArrayList();
        tblPhones.setItems(phoneList);
        updateTable("");
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

    private void deletePhone(int id) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete this phone?");

        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeYes) {
            try (Connection connection = DatabaseConnection.getConnection()) {
                String query = "DELETE FROM phones WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                // If deletion is successful, remove the item from the list
                phoneList.removeIf(phone -> phone.getId() == id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    private void updateTable(String searchText) {
        phoneList.clear();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM phones WHERE LOWER(brand) LIKE ? OR LOWER(model) LIKE ? ORDER BY brand ASC, model ASC";
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
    }
}
