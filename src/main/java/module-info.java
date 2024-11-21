module application {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens application to javafx.fxml;
    exports application;
}
