package org.example.demo.conexionDatos;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final  String url="jdbc:mysql://localhost:3306/empresa";
    private static final String user="root";
    private static final String password="root";
    public static Conexion getConexion(){
        try {
            return (Conexion) DriverManager.getConnection(url,user,password);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

/*no se crea otra base de datos ya que en la misma base pueden ir disntintas tablas*/

