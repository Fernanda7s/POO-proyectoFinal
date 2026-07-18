package org.example.demo.dao;

import org.example.demo.conexionDatos.Conexion;
import org.example.demo.interfaces.AccionesCrud;
import org.example.demo.model.Administrador;
import org.example.demo.model.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuariosDAO implements AccionesCrud<Persona> {
    public Persona iniciarSesion(String usuario, String clave) {
        Persona user = null;
        String sql = "SELECT * FROM empleado WHERE usuario=? AND clave=?";
        try {
            Connection con = (Connection) Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, clave);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String codigo = rs.getString("codigo");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String users = rs.getString("usuario");
                String password = rs.getString("clave");
                String cargo = rs.getString("cargo");
                //para identificar el cargo
                if (cargo.equals("administracdor")) {
                    return new Administrador(codigo, nombre, apellido, users, password, clave);
                    //completar las clases para que deje de aparecer el error de missigin return , necesita retornar algo
                }


            }
            //
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean crear(Persona people) {
        return false;
    }

    @Override
    public boolean crear(Persona personas) {
        String sql = """
        INSERT INTO empleado
        (nombre,apellido,usuario,clave,cargo)
        VALUES (?,?,?,?,?)
        """;

        try (
                Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, personas.getId());
            ps.setString(2, personas.getNombre());
            ps.setString(3, personas.getApellido());
            ps.setString(4, personas.getCargo());
            ps.setString(5, personas.getUsuario());
            ps.setString(6,personas.getContrasenia());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizar(Persona personas) {

        String sql = """
        UPDATE empleado
        SET nombre=?,
            apellido=?,
            cargo=?
        WHERE id=?
        """;

        try (
                Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, personas.getId());
            ps.setString(2, personas.getNombre());
            ps.setString(3, personas.getApellido());
            ps.setString(4, personas.getCargo());
            ps.setString(5, personas.getUsuario());
            ps.setString(6,personas.getContrasenia());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Persona buscar(String codigo) {
        String sql = "SELECT * FROM empleado WHERE id=?";

        try (
                Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

               Persona personas = new Persona();

                personas.setId(("codigo"));
                personas.setNombre(rs.getString("nombre"));
                personas.setApellido(rs.getString("apellido"));
                personas.setUsuario(rs.getString("usuario"));
                personas.setContrasenia(rs.getString("clave"));
                personas.setCargo(rs.getString("cargo"));

                return personas;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }



    @Override
    public boolean eliminar(String codigo) {
        String sql = "DELETE FROM empleado WHERE id=?";

        try (
                Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
