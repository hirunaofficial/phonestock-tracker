package dev.hiruna.phone;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class PSTApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PSTApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        // Set the icon of the application window
        stage.getIcons().add(new Image(PSTApplication.class.getResourceAsStream("assets/icon.png")));

        // Disable window resize
        stage.setResizable(false);

        stage.setTitle("PhoneStock Tracker (Track Every Phone, Every Time)");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}