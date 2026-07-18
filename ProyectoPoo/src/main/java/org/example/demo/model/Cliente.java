package org.example.demo.model;

public class Cliente extends Persona {
    public Cliente(String id, String nombre, String apellido, String usuario, String contrasenia, String cargo) {
        super(id, nombre, apellido, usuario, contrasenia, cargo);
    }

    @Override
    public String obtenerVista() {
        return "org/example/demo/view/cliente.fxml";
    }
}
