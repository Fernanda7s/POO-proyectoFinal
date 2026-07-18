package org.example.demo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.demo.util.Validaciones;

public class administradorEmpleadosController {
    @FXML
    private TextField txtCodigoEpd;
    @FXML
    private TextField txtNombreEpd;
    @FXML
    private TextField txtApellidoEpd;
    @FXML
    private ComboBox<String> cbxCargoEmp;
    @FXML
    private TableColumn  clmCodigoEmp;
    @FXML
    private TableColumn clmNombreEmp;
    @FXML
    private TableColumn clmApellidoEmp;
    @FXML
    private TableColumn clmCargoEmp;
    @FXML
    private TableColumn clmUsuarioEmp;
    @FXML
    private TableColumn clmClaveEmp;
    @FXML
    private Label lblMensaje;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;

    @FXML
    public void initialize () {
        cbxCargoEmp.getItems().addAll(
                "administrador",
                "empleado",
                "cliente");

    };
    @FXML
    public void onCrearEmp (){
        String codigoEpd = txtCodigoEpd.getText();
        String nombreEpd = txtNombreEpd.getText();
        String apellidoEpd = txtApellidoEpd.getText();
        String cargoEpd = cbxCargoEmp.getValue();

        String error =(Validaciones.validarEmpleado(codigoEpd,nombreEpd,apellidoEpd,cargoEpd));
        if(error != null){
            lblMensaje.setText(error);
            return;

        }


    };


    @FXML
    public void onBuscarEmp (){}
    @FXML
    public void onActualizarEmp(){}
    @FXML
    public void onEliminarEmp (){}
    @FXML
    public void btnSalirEmp (){}


    private void limpiarCampos(){
        txtCodigoEpd.setText("");
        txtNombreEpd.setText("");
        txtApellidoEpd.setText("");
        cbxCargoEmp.setValue(null);

    }

}
