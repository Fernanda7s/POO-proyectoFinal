package org.example.demo.dao;

import org.example.demo.conexionDatos.Conexion;
import org.example.demo.interfaces.AccionesCrud;
import org.example.demo.model.Administrador;
import org.example.demo.model.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO implements AccionesCrud {

    public Persona iniciarSesion(String usuario, String clave) {
        String sql = "SELECT * FROM empleado WHERE usuario=? AND clave=?";
        try {
            Connection con = Conexion.getConexion();
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
                if (cargo.equals("administrador")) {
                    return new Administrador(codigo, nombre, apellido, users, password, cargo);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean crear(Persona personas) {
        String sql = """
        INSERT INTO empleado
        (codigo,nombre,apellido,usuario,clave,cargo)
        VALUES (?,?,?,?,?,?)
        """;

        try (
                Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, personas.getId());
            ps.setString(2, personas.getNombre());
            ps.setString(3, personas.getApellido());
            ps.setString(4, personas.getUsuario());
            ps.setString(5, personas.getContrasenia());
            ps.setString(6, personas.getCargo());

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
        WHERE codigo=?
        """;

        try (
                Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, personas.getNombre());
            ps.setString(2, personas.getApellido());
            ps.setString(3, personas.getCargo());
            ps.setString(4, personas.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Persona buscar(String codigo) {
        String sql = "SELECT * FROM empleado WHERE codigo=?";

        try (
                Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Persona personas = new Persona() {
                    @Override
                    public String obtenerVista() { return ""; }
                };
                personas.setId(rs.getString("codigo"));
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
    public boolean eliminar(Persona people) {
        String sql = "DELETE FROM empleado WHERE codigo=?";

        try (
                Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, people.getId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(String codigo) {
        String sql = "DELETE FROM empleado WHERE codigo=?";

        try (
                Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, codigo);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Persona> listar() {
        List<Persona> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleado";

        try (
                Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Persona personas = new Persona() {
                    @Override
                    public String obtenerVista() { return ""; }
                };
                personas.setId(rs.getString("codigo"));
                personas.setNombre(rs.getString("nombre"));
                personas.setApellido(rs.getString("apellido"));
                personas.setUsuario(rs.getString("usuario"));
                personas.setContrasenia(rs.getString("clave"));
                personas.setCargo(rs.getString("cargo"));
                lista.add(personas);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

}
