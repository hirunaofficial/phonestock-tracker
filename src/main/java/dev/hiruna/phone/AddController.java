package dev.hiruna.phone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

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

    }

}
