module dev.hiruna.phone {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens dev.hiruna.phone to javafx.fxml;
    exports dev.hiruna.phone;
}