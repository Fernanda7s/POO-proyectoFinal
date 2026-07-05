package org.example.demo.model;
// el administrado se va encargar de asignar el usuario y la contraseña se va a crear automaticamete
// TAMBIEN EL USUARIO SE VA A CREAR AUTOMATICAMENTE

public class Administrador extends Persona {
    public Administrador(String id, String nombre, String apellido, String usuario, String contrasenia, String cargo) {
        super(id, nombre, apellido, usuario, contrasenia, cargo);
    }


}
