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
    void btnAddPhone(ActionEvent event) {

    }

    @FXML
    void btnDeletePhone(ActionEvent event) {

    }

    @FXML
    void btnSearch(ActionEvent event) throws IOException {
        Stage stage = (Stage) this.root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("search-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("assets/icon.png"))); // Set the icon of the application window
        stage.setResizable(false); // Disable window resize
        stage.setTitle("Search - PhoneStock Tracker (Track Every Phone, Every Time)");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnUpdatePhone(ActionEvent event) {

    }

}
