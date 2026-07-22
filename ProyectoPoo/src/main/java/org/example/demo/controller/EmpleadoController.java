package org.example.demo.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmpleadoController {
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNombreProducto;


    @FXML
    private TextField txtIdCliente;
    @FXML
    private TextField txtNombreCliente;


    @FXML
    private TableView<Producto> tablaProductos;
    @FXML
    private TableColumn<Producto, String> colId;
    @FXML
    private TableColumn<Producto, String> colCodigo;
    @FXML
    private TableColumn<Producto, String> colNombre;
    @FXML
    private TableColumn<Producto, String> colSerie;
    @FXML
    private TableColumn<Producto, String> colMarca;
    @FXML
    private TableColumn<Producto, Integer> colStock;
    @FXML
    private TableColumn<Producto, Double> colPrecio;
    @FXML
    private TableColumn<Producto, String> colCatalogo;

    //datos de prueba para despues conectar a la base de datos
    private final ObservableList<Producto> listaProductos = FXCollections.observableArrayList(
            new Producto("1", "P001", "iPhone 13", "SN12345", "Apple", 10, 850.0, "Gama Alta"),
            new Producto("2", "P002", "Galaxy A54", "SN67890", "Samsung", 15, 320.0, "Gama Media"),
            new Producto("3", "P003", "Redmi Note 12", "SN11223", "Xiaomi", 20, 210.0, "Gama Media")
    );

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colSerie.setCellValueFactory(new PropertyValueFactory<>("serie"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colCatalogo.setCellValueFactory(new PropertyValueFactory<>("catalogo"));

        tablaProductos.setItems(listaProductos);
    }

    @FXML
    private void onBuscarProductoClick() {
        String codigo = txtCodigo.getText();
        String nombre = txtNombreProducto.getText();

        for (Producto p : listaProductos) {
            boolean coincideCodigo = !codigo.isEmpty() && p.getCodigo().equalsIgnoreCase(codigo);
            boolean coincideNombre = !nombre.isEmpty() && p.getNombre().equalsIgnoreCase(nombre);

            if (coincideCodigo || coincideNombre) {
                tablaProductos.getSelectionModel().select(p);
                tablaProductos.scrollTo(p);
                return;
            }
        }
        mostrarAlerta("No se encontró ningún producto con esos datos.");
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    //clase auxiliar
    public static class Producto {
        private final String id;
        private final String codigo;
        private final String nombre;
        private final String serie;
        private final String marca;
        private final int stock;
        private final double precio;
        private final String catalogo;

        public Producto(String id, String codigo, String nombre, String serie, String marca, int stock, double precio, String catalogo) {
            this.id = id;
            this.codigo = codigo;
            this.nombre = nombre;
            this.serie = serie;
            this.marca = marca;
            this.stock = stock;
            this.precio = precio;
            this.catalogo = catalogo;
        }

        public String getId() { return id; }
        public String getCodigo() { return codigo; }
        public String getNombre() { return nombre; }
        public String getSerie() { return serie; }
        public String getMarca() { return marca; }
        public int getStock() { return stock; }
        public double getPrecio() { return precio; }
        public String getCatalogo() { return catalogo; }
    }
}
