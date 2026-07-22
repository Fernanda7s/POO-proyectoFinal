package org.example.demo.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.demo.model.Cliente;

import java.io.IOException;

public class ClienteController {
    @FXML
    private TextField txtBuscarId;
    @FXML
    private TextField txtBuscarNombre;

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtDireccion;
    @FXML
    private DatePicker dpFechaRegistro;

    @FXML
    private TableView<Cliente> tablaClientes;
    @FXML
    private TableColumn<Cliente, String> colId;
    @FXML
    private TableColumn<Cliente, String> colNombre;
    @FXML
    private TableColumn<Cliente, String> colCorreo;
    @FXML
    private TableColumn<Cliente, String> colTelefono;
    @FXML
    private TableColumn<Cliente, String> colDireccion;
    @FXML
    private TableColumn<Cliente, String> colFecha;

    //por el momento guardare en memoria, mas luego se conecta a la base de datos.
    private final ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaRegistro"));
        tablaClientes.setItems(listaClientes);
    }

    //111111lo de las alertas ya reviso
    @FXML
    private void onGuardarClick() {
        if (txtId.getText().isEmpty() || txtNombre.getText().isEmpty()) {
            mostrarAlerta("Debe ingresar al menos el ID y el nombre del cliente.");
            return;
        }

        String fecha = dpFechaRegistro.getValue() != null ? dpFechaRegistro.getValue().toString() : "";

        Cliente cliente = Cliente.crearCliente(txtId.getText(), txtNombre.getText(), txtCorreo.getText(), txtTelefono.getText(),
                txtDireccion.getText(), fecha);

        listaClientes.add(cliente);
        onLimpiarClick();

        // una vez guardado el cliente, se abre la ventana donde solo puede ver los productos
        abrirVentanaProductos();
    }

    private void abrirVentanaProductos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo/view/buscarproducto.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Catálogo de productos");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            mostrarAlerta("No se pudo abrir la ventana de productos.");
            e.printStackTrace();
        }
    }

    @FXML
    private void onLimpiarClick() {
        txtId.clear();
        txtNombre.clear();
        txtCorreo.clear();
        txtTelefono.clear();
        txtDireccion.clear();
        dpFechaRegistro.setValue(null);
    }

    @FXML
    private void onBuscarClick() {
        String id = txtBuscarId.getText();
        String nombre = txtBuscarNombre.getText();
        for (Cliente c : listaClientes) {
            boolean coincideId = !id.isEmpty() && c.getId().equalsIgnoreCase(id);
            boolean coincideNombre = !nombre.isEmpty() && c.getNombre().equalsIgnoreCase(nombre);

            if (coincideId || coincideNombre) {
                tablaClientes.getSelectionModel().select(c);
                tablaClientes.scrollTo(c);
                return;
            }
        }
        mostrarAlerta("No se encontró ningún cliente con esos datos.");
    }
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}