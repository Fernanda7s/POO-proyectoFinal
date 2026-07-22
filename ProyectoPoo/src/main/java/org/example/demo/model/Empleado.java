package org.example.demo.model;

//el codigo esta simplificado porque el empleado puede: registrar ventas, buscar celulares, actualizar stock y consultar clientes
//y no puede: eliminar usuarios, crear administradores ni ver reportes financieros

public class Empleado extends Persona {
    public Empleado(String id, String nombre, String apellido, String usuario, String contrasenia, String cargo) {
        super(id, nombre, apellido, usuario, contrasenia, cargo);
    }

    @Override
    public String obtenerVista() {
        return "org/example/demo/view/empleado.fxml";
    }
}
