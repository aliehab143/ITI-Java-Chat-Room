module com.example.server {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.server to javafx.fxml;
    exports com.example.server;
}