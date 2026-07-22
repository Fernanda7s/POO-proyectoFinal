package org.example.demo.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.demo.dao.ProductosDAO;
import org.example.demo.model.Productos;

public class BuscarProductoController {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtMarca;

    @FXML
    private TableView<Productos> tablaProductos;
    @FXML
    private TableColumn<Productos, String> colId;
    @FXML
    private TableColumn<Productos, String> colCodigo;
    @FXML
    private TableColumn<Productos, String> colNombre;
    @FXML
    private TableColumn<Productos, String> colMarca;
    @FXML
    private TableColumn<Productos, Double> colPrecio;
    @FXML
    private TableColumn<Productos, Integer> colStock;
    @FXML
    private TableColumn<Productos, String> colCatalogo;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(cellData -> new javafx.beans.binding.StringBinding() {
            @Override protected String computeValue() { return cellData.getValue().getCodigo(); }
        });
        colCodigo.setCellValueFactory(cellData -> new javafx.beans.binding.StringBinding() {
            @Override protected String computeValue() { return cellData.getValue().getCodigo(); }
        });
        colNombre.setCellValueFactory(cellData -> new javafx.beans.binding.StringBinding() {
            @Override protected String computeValue() { return cellData.getValue().getNombre(); }
        });
        colMarca.setCellValueFactory(cellData -> new javafx.beans.binding.StringBinding() {
            @Override protected String computeValue() { return cellData.getValue().getMarca(); }
        });
        colPrecio.setCellValueFactory(cellData -> new javafx.beans.binding.ObjectBinding<Double>() {
            @Override protected Double computeValue() { return cellData.getValue().getPrecio(); }
        });
        colStock.setCellValueFactory(cellData -> new javafx.beans.binding.ObjectBinding<Integer>() {
            @Override protected Integer computeValue() { return cellData.getValue().getStock(); }
        });
        colCatalogo.setCellValueFactory(cellData -> new javafx.beans.binding.StringBinding() {
            @Override protected String computeValue() { return cellData.getValue().getCatalogo(); }
        });

        cargaTabla();
    }

    @FXML
    private void onBuscarClick() {
        String nombre = txtNombre.getText().trim();
        String marca = txtMarca.getText().trim();

        if (nombre.isEmpty() && marca.isEmpty()) {
            cargaTabla();
            return;
        }

        ProductosDAO dao = new ProductosDAO();
        ObservableList<Productos> lista = FXCollections.observableArrayList();
        lista.addAll(dao.buscarProductos(null, nombre, marca));
        tablaProductos.setItems(lista);

        if (lista.isEmpty()) {
            mostrarAlerta("No se encontraron productos con esos datos.");
        }
    }

    private void cargaTabla() {
        ProductosDAO dao = new ProductosDAO();
        ObservableList<Productos> lista = FXCollections.observableArrayList();
        lista.addAll(dao.listar());
        tablaProductos.setItems(lista);
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
