package org.example.demo.conexionDatos;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionEmpleados {
    private static final  String url="jdbc:mysql://localhost:3306/empresa";
    private static final String user="root";
    private static final String password="root";
    public static ConexionEmpleados getConexionEmpleados(){
        try {
            return (ConexionEmpleados) DriverManager.getConnection(url,user,password);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
