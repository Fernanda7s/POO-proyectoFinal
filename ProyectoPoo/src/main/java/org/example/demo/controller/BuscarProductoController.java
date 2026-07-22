package org.example.demo.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class BuscarProductoController {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtMarca;

    @FXML
    private TableView<Producto> tablaProductos;
    @FXML
    private TableColumn<Producto, String> colId;
    @FXML
    private TableColumn<Producto, String> colCodigo;
    @FXML
    private TableColumn<Producto, String> colNombre;
    @FXML
    private TableColumn<Producto, String> colMarca;
    @FXML
    private TableColumn<Producto, Double> colPrecio;
    @FXML
    private TableColumn<Producto, Integer> colStock;
    @FXML
    private TableColumn<Producto, String> colCatalogo;

    //productos de prueba, despues se conecta con la bd
    private final ObservableList<Producto> listaProductos = FXCollections.observableArrayList(
            new Producto("1", "P001", "iPhone 13", "Apple", 850.0, 10, "Gama Alta"),
            new Producto("2", "P002", "Galaxy A54", "Samsung", 320.0, 15, "Gama Media"),
            new Producto("3", "P003", "Redmi Note 12", "Xiaomi", 210.0, 20, "Gama Media"),
            new Producto("4", "P004", "iPhone SE", "Apple", 430.0, 8, "Gama Media")
    );

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colCatalogo.setCellValueFactory(new PropertyValueFactory<>("catalogo"));

        tablaProductos.setItems(listaProductos);
    }

    @FXML
    private void onBuscarClick() {
        String nombre = txtNombre.getText().trim();
        String marca = txtMarca.getText().trim();

        //si no puso nada, muestro todos otra vez
        if (nombre.isEmpty() && marca.isEmpty()) {
            tablaProductos.setItems(listaProductos);
            return;
        }

        ObservableList<Producto> resultado = FXCollections.observableArrayList();
        for (Producto p : listaProductos) {
            boolean coincideNombre = nombre.isEmpty() || p.getNombre().toLowerCase().contains(nombre.toLowerCase());
            boolean coincideMarca = marca.isEmpty() || p.getMarca().equalsIgnoreCase(marca);

            if (coincideNombre && coincideMarca) {
                resultado.add(p);
            }
        }

        if (resultado.isEmpty()) {
            mostrarAlerta("No se encontraron productos con esos datos.");
        }
        tablaProductos.setItems(resultado);
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    //clase para armar los productos de prueba, cuando este la bd se cambia por el modelo real
    public static class Producto {
        private final String id;
        private final String codigo;
        private final String nombre;
        private final String marca;
        private final double precio;
        private final int stock;
        private final String catalogo;

        public Producto(String id, String codigo, String nombre, String marca, double precio, int stock, String catalogo) {
            this.id = id;
            this.codigo = codigo;
            this.nombre = nombre;
            this.marca = marca;
            this.precio = precio;
            this.stock = stock;
            this.catalogo = catalogo;
        }

        public String getId() { return id; }
        public String getCodigo() { return codigo; }
        public String getNombre() { return nombre; }
        public String getMarca() { return marca; }
        public double getPrecio() { return precio; }
        public int getStock() { return stock; }
        public String getCatalogo() { return catalogo; }
    }
}