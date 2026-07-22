package org.example.demo.model;

public class Cliente extends Persona{
    private String correo;
    private String telefono;
    private String direccion;
    private String fechaRegistro;

    public Cliente(String id, String nombre, String apellido, String usuario, String contrasenia, String cargo){
        super(id, nombre, apellido, usuario, contrasenia, cargo);
    }

    public static Cliente crearCliente(String id, String nombre, String correo, String telefono, String direccion, String fechaRegistro){
        Cliente cliente = new Cliente(id, nombre, "", "", "", "cliente");
        cliente.correo = correo;
        cliente.telefono = telefono;
        cliente.direccion = direccion;
        cliente.fechaRegistro = fechaRegistro;
        return cliente;
    }

    //ahora implementamos getters y setters
    public String getCorreo(){
        return correo;
    }
    public void setCorreo(String correo){
        this.correo = correo;
    }

    public String getTelefono(){
        return telefono;
    }
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }

    public String getDireccion(){
        return direccion;
    }
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }

    public String getFechaRegistro(){
        return fechaRegistro;
    }
    public void setFechaRegistro(String fechaRegistro){
        this.fechaRegistro = fechaRegistro;
    }

    //implemetamos el override
    @Override
    public String obtenerVista(){
        return"org/example/demo/view/cliente.fxml";
    }




}
