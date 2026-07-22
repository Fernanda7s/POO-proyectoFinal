package org.example.demo.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.demo.dao.UsuariosDAO;
import org.example.demo.model.Persona;
import org.example.demo.util.Validaciones;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.Random;

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
    private TableView<Persona> tblEmpleados;
    @FXML
    private Label lblPersonal;

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

        //crear objetos
        Persona personas = new Persona() {
            @Override
            public String obtenerVista() { return ""; }
        };
        personas.setId(codigoEpd);
        personas.setNombre(nombreEpd);
        personas.setApellido(apellidoEpd);
        personas.setCargo(cargoEpd);

        //generar usuario y contraseña automáticos
        String usuario = nombreEpd.substring(0, Math.min(3, nombreEpd.length())).toLowerCase();
        String contrasenia = String.format("%05d", new Random().nextInt(100000));
        personas.setUsuario(usuario);
        personas.setContrasenia(contrasenia);

        //se llama al dao
        UsuariosDAO dao = new UsuariosDAO();
        if (dao.crear(personas)) {
            lblMensaje.setText("Guardado correctamente. Usuario: " + usuario + " | Contraseña: " + contrasenia);
            limpiarCampos();
            cargaTable();

        }else {
            lblMensaje.setText("Error creando empleado");
        }



    };


    @FXML
    public void onBuscarEmp (){
        UsuariosDAO dao = new UsuariosDAO();
        String codigo = txtCodigoEpd.getText();
        String nombre = txtNombreEpd.getText();

        if (codigo.isEmpty() && nombre.isEmpty()) {
            cargaTable();
            return;
        }

        ObservableList<Persona> lista = FXCollections.observableArrayList();
        lista.addAll(dao.buscarEmpleados(codigo, nombre));
        tblEmpleados.setItems(lista);

        if (lista.isEmpty()) {
            lblMensaje.setText("No se encontraron empleados");
        } else {
            lblMensaje.setText("Se encontraron " + lista.size() + " empleados");
        }
    }
    @FXML
    public void onActualizarEmp(){
        Persona personas = new Persona() {
            @Override
            public String obtenerVista() { return ""; }
        };
        personas.setId(txtCodigoEpd.getText());
        personas.setNombre(txtNombreEpd.getText());
        personas.setApellido(txtApellidoEpd.getText());
        personas.setCargo(cbxCargoEmp.getValue());
        UsuariosDAO dao = new UsuariosDAO();
        if (dao.actualizar(personas)) {
            lblMensaje.setText("Actualizado correctamente");
            cargaTable();
        }
    }
    @FXML
    public void onEliminarEmp (){
        UsuariosDAO dao = new UsuariosDAO();
        if (dao.eliminar(txtCodigoEpd.getText())) {
            lblMensaje.setText("Eliminado correctamente");
            cargaTable();
        }
    }
    @FXML
    public void btnSalirEmp (){}


    private void limpiarCampos(){
        txtCodigoEpd.setText("");
        txtNombreEpd.setText("");
        txtApellidoEpd.setText("");
        cbxCargoEmp.setValue(null);

    }
    private void cargaTable(){
        UsuariosDAO dao = new UsuariosDAO();
        ObservableList<Persona> lista = FXCollections.observableArrayList();
        lista.addAll(dao.listar());
        tblEmpleados.setItems(lista);
    }

    @FXML
    public void abrirProductos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo/view/administracionProductos.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Productos");
            stage.show();

            Stage actual = (Stage)((Node)event.getSource()).getScene().getWindow();
            actual.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
