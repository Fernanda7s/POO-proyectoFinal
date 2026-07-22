package org.example.demo.model;

public class Empleado extends Persona {

    public Empleado(String id, String nombre, String apellido, String usuario, String contrasenia, String cargo) {
        super(id, nombre, apellido, usuario, contrasenia, cargo);
    }

    @Override
    public String obtenerVista() {
        return "org/example/demo/view/empleado.fxml";
    }
}
