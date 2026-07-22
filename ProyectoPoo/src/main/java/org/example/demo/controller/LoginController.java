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
import org.example.demo.model.Persona;
import org.example.demo.dao.*;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField passfContrasena;
    @FXML
    private Button btnIngresar;
    @FXML
    private Label lblMensajeLogin;

    @FXML
    private void onIngresoClick(){
        String usuario = txtUsuario.getText();
        String pass = passfContrasena.getText();

        if (usuario.isEmpty() || pass.isEmpty()) {
            lblMensajeLogin.setText("Error campos vacios");
            return;
        }

        try {
            UsuariosDAO dao = new UsuariosDAO();
            Persona persona = dao.iniciarSesion(usuario, pass);

            if (persona == null) {
                lblMensajeLogin.setText("Usuario o contrasena incorrectos");
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/" + persona.obtenerVista()));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            Stage stageActual = (Stage) btnIngresar.getScene().getWindow();
            stageActual.close();
        } catch (IOException e) {
            lblMensajeLogin.setText("No se pudo cargar la pagina destino");
            e.printStackTrace();
        } catch (Exception e) {
            lblMensajeLogin.setText("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void onRegistroClienteClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo/view/registro.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("TecnoCelular - Registro");
            stage.setScene(new Scene(root));
            stage.show();

            Stage stageActual = (Stage) btnIngresar.getScene().getWindow();
            stageActual.close();
        } catch (IOException e) {
            lblMensajeLogin.setText("No se pudo abrir la ventana de registro");
            e.printStackTrace();
        }
    }
}