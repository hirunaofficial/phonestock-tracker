module dev.hiruna.phone {
    requires javafx.controls;
    requires javafx.fxml;


    opens dev.hiruna.phone to javafx.fxml;
    exports dev.hiruna.phone;
}