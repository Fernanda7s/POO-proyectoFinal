package org.example.demo.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import java.util.Optional;

public class Alertas {

    //PRIMERA ALERTA.- error genérico(para campos vacios o errores generales)
    public static void mostrarError(String titulo, String contenido) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    //SEGUNDA ALERTA.- iformación (para exito al guardar o actualizar)
    public static void mostrarInformacion(String titulo, String contenido) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    //TERCERA ALERTA.- confirmación (obligatoria antes de eliminar segun el proyecto)
    public static boolean mostrarConfirmacion(String titulo, String contenido) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    //CUARTA ALERTA.- advertencia (para validaciones de formato o contraseñas cortas)
    public static void mostrarAdvertencia(String titulo, String contenido) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    //QUINTA ALERTA.- alerta especifica para campos vacios (ahorra codigo en los controladores)
    public static void alertaCampoVacio(String nombreCampo) {
        mostrarError("Campo Vacío", "El campo '" + nombreCampo + "' es obligatorio.");
    }
}
