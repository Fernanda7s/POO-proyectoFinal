package org.example.demo.conexionDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String url = "jdbc:mysql://sakura.proxy.rlwy.net:40439/railway?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "esteban123";

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}

