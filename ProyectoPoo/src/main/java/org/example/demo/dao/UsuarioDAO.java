import org.example.demo.conexionDatos.ConexionEmpleados;
import org.example.demo.model.Administrador;
import org.example.demo.model.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuariosDAO {
    public Persona iniciarSesion(String usuario, String clave) {
        Persona user = null;
        String sql = "SELECT * FROM empleado WHERE usuario=? AND clave=?";
        try {
            Connection con = (Connection) ConexionEmpleados.getConexionEmpleados();
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
}


void main() {
}



