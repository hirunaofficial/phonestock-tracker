package dev.hiruna.phone;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PSTController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}