package org.example.demo.dao;

import org.example.demo.conexionDatos.Conexion;
import org.example.demo.model.Productos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductosDAO {

    public boolean crear(Productos producto) {
        String sql = """
        INSERT INTO productos
        (codigo,nombre,catalogo,marca,precio,stock)
        VALUES (?,?,?,?,?,?)
        """;

        try (
                Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, producto.getCodigo());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getCatalogo());
            ps.setString(4, producto.getMarca());
            ps.setDouble(5, producto.getPrecio());
            ps.setInt(6, producto.getStock());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Productos producto) {
        String sql = """
        UPDATE productos
        SET nombre=?,
            catalogo=?,
            marca=?,
            precio=?,
            stock=?
        WHERE codigo=?
        """;

        try (
                Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getCatalogo());
            ps.setString(3, producto.getMarca());
            ps.setDouble(4, producto.getPrecio());
            ps.setInt(5, producto.getStock());
            ps.setString(6, producto.getCodigo());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Productos buscar(String codigo) {
        String sql = "SELECT * FROM productos WHERE codigo=?";

        try (
                Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Productos(
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getString("catalogo"),
                        rs.getString("marca"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean eliminar(String codigo) {
        String sql = "DELETE FROM productos WHERE codigo=?";

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

    public List<Productos> listar() {
        List<Productos> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        try (
                Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Productos producto = new Productos(
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getString("catalogo"),
                        rs.getString("marca"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                );
                lista.add(producto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
