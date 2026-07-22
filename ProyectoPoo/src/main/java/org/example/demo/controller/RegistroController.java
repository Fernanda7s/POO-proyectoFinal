package org.example.demo.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.demo.dao.UsuariosDAO;
import org.example.demo.model.Persona;
import org.example.demo.util.Alertas;

import java.io.IOException;

public class RegistroController {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtUsuarioRegistro;
    @FXML
    private PasswordField txtContrasenia;
    @FXML
    private PasswordField txtRepContrasenia;
    @FXML
    private Label lblMensaje;
    @FXML
    private Button btnRegistrar;

    @FXML
    private void onRegistrar() {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String usuario = txtUsuarioRegistro.getText();
        String contrasena = txtContrasenia.getText();
        String repContrasena = txtRepContrasenia.getText();
        String cargo = "cliente";

        if (nombre.isEmpty() || apellido.isEmpty() || usuario.isEmpty()
                || contrasena.isEmpty() || repContrasena.isEmpty()) {
            Alertas.mostrarAdvertencia("Campos vacíos", "Todos los campos son obligatorios.");
            return;
        }

        if (!contrasena.equals(repContrasena)) {
            Alertas.mostrarError("Contraseñas no coinciden", "Las contraseñas ingresadas no son iguales.");
            return;
        }

        if (contrasena.length() < 4) {
            Alertas.mostrarAdvertencia("Contraseña corta", "La contraseña debe tener al menos 4 caracteres.");
            return;
        }

        String codigo = generarCodigo(cargo);

        Persona persona = new Persona() {
            @Override
            public String obtenerVista() { return ""; }
        };
        persona.setId(codigo);
        persona.setNombre(nombre);
        persona.setApellido(apellido);
        persona.setUsuario(usuario);
        persona.setContrasenia(contrasena);
        persona.setCargo(cargo);

        UsuariosDAO dao = new UsuariosDAO();
        boolean creado = dao.crear(persona);

        if (creado) {
            Alertas.mostrarInformacion("Registro exitoso", "Usuario registrado correctamente con cargo: " + cargo);
            volverLogin();
        } else {
            Alertas.mostrarError("Error", "No se pudo registrar el usuario. El usuario ya puede existir.");
        }
    }

    private String generarCodigo(String cargo) {
        int numero = (int) (Math.random() * 900) + 100;
        return "CLI" + numero;
    }

    @FXML
    private void onSalir() {
        volverLogin();
    }

    private void volverLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo/view/login.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("TecnoCelular - Login");
            stage.setScene(new Scene(root));
            stage.show();

            Stage stageActual = (Stage) btnRegistrar.getScene().getWindow();
            stageActual.close();
        } catch (IOException e) {
            Alertas.mostrarError("Error", "No se pudo abrir la ventana de login.");
            e.printStackTrace();
        }
    }
}