module com.galleria_fotografica {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;

    opens com.galleria_fotografica to javafx.fxml;
    exports com.galleria_fotografica;
    exports com.galleria_fotografica.model;
    exports com.galleria_fotografica.controller;
    opens com.galleria_fotografica.controller to javafx.fxml;
}