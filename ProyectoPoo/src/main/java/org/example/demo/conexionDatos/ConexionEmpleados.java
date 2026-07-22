package org.example.demo.conexionDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionEmpleados {
    private static final String url = "jdbc:mysql://sakura.proxy.rlwy.net:40439/railway";
    private static final String user = "root";
    private static final String password = "tBhgFDgDvjQOQTfLURZsJOfZIqFdkzfM";

    public static Connection getConexionEmpleados() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}