package org.example.demo.util;

public class Validaciones {
    public static boolean validarCamposVacios(String texto) {
        return texto == null || texto.trim().isEmpty();
    }
    public static boolean soloLetras( String texto){
        return texto.matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+");
    }
    public static boolean soloNumeros(String texto) {

        return texto.matches("\\d+");

    }
    public static boolean longitudMinima(String texto, int longitud) {
        return texto.length() >= longitud;

    }
    public static boolean comboSeleccionado(Object valor){
        return valor != null;

    }
    //validar en general todo
    public static String validarEmpleado(String codigo,String nombre,String apellido, String cargo){
        if(validarCamposVacios(codigo))
        return "El codigo es obligatorio";

        if (validarCamposVacios(nombre))
            return "El nombre es obligatorio.";

        if (!soloLetras(nombre))
            return "El nombre solo puede contener letras.";

        if (validarCamposVacios(apellido))
            return "El apellido es obligatorio.";

        if (!soloLetras(apellido))
            return "El apellido solo puede contener letras.";

        if (cargo == null)
            return "Debe seleccionar un cargo.";

        return null;
    }
}
