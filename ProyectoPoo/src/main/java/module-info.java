module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens org.example.demo to javafx.fxml;
    exports org.example.demo;
    exports org.example.demo.controller;
    opens org.example.demo.controller to javafx.fxml;
    exports org.example.demo.model;
    opens org.example.demo.model to javafx.fxml;
    exports org.example.demo.dao;
    opens org.example.demo.dao to javafx.fxml;
    exports org.example.demo.util;
    opens org.example.demo.util to javafx.fxml;
    exports org.example.demo.conexionDatos;
    opens org.example.demo.conexionDatos to javafx.fxml;
}