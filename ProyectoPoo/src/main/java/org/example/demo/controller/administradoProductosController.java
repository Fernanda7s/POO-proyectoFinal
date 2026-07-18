package org.example.demo.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.demo.dao.ProductosDAO;
import org.example.demo.model.Productos;

public class administradoProductosController {
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtSerie;
    @FXML
    private TextField txtNombre;
    @FXML
    private ComboBox<String> cbxCatalogo;
    @FXML
    private TextField txtMarca;
    @FXML
    private Spinner<Integer> cbxStock;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TableColumn<Productos, String> clmCodigo;
    @FXML
    private TableColumn<Productos, String> clmNombre;
    @FXML
    private TableColumn<Productos, String> clmCatalogo;
    @FXML
    private TableColumn<Productos, String> clmMarca;
    @FXML
    private TableColumn<Productos, Integer> clmStock;
    @FXML
    private TableColumn<Productos, Double> clmPrecio;
    @FXML
    private TableView<Productos> tableProductos;
    @FXML
    private Label lblMensaje;

    @FXML
    public void initialize() {
        cbxCatalogo.getItems().addAll(
                "cedulares",
                "audifonos",
                "cargadores"
        );

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0);
        cbxStock.setValueFactory(valueFactory);

        clmCodigo.setCellValueFactory(cellData -> new javafx.beans.binding.StringBinding() {
            @Override protected String computeValue() { return cellData.getValue().getCodigo(); }
        });
        clmNombre.setCellValueFactory(cellData -> new javafx.beans.binding.StringBinding() {
            @Override protected String computeValue() { return cellData.getValue().getNombre(); }
        });
        clmCatalogo.setCellValueFactory(cellData -> new javafx.beans.binding.StringBinding() {
            @Override protected String computeValue() { return cellData.getValue().getCatalogo(); }
        });
        clmMarca.setCellValueFactory(cellData -> new javafx.beans.binding.StringBinding() {
            @Override protected String computeValue() { return cellData.getValue().getMarca(); }
        });
        clmStock.setCellValueFactory(cellData -> new javafx.beans.binding.ObjectBinding<Integer>() {
            @Override protected Integer computeValue() { return cellData.getValue().getStock(); }
        });
        clmPrecio.setCellValueFactory(cellData -> new javafx.beans.binding.ObjectBinding<Double>() {
            @Override protected Double computeValue() { return cellData.getValue().getPrecio(); }
        });

        cargaTable();
    }

    @FXML
    public void onCrearClick() {
        String codigo = txtCodigo.getText();
        String nombre = txtNombre.getText();
        String catalogo = cbxCatalogo.getValue();
        String marca = txtMarca.getText();
        String precioText = txtPrecio.getText();
        Integer stock = cbxStock.getValue();

        if (codigo == null || codigo.trim().isEmpty()) {
            lblMensaje.setText("El codigo es obligatorio");
            return;
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            lblMensaje.setText("El nombre es obligatorio");
            return;
        }
        if (catalogo == null) {
            lblMensaje.setText("Debe seleccionar un catalogo");
            return;
        }
        if (marca == null || marca.trim().isEmpty()) {
            lblMensaje.setText("La marca es obligatoria");
            return;
        }
        if (precioText == null || precioText.trim().isEmpty()) {
            lblMensaje.setText("El precio es obligatorio");
            return;
        }
        if (!precioText.matches("\\d+(\\.\\d+)?")) {
            lblMensaje.setText("El precio debe ser un numero");
            return;
        }

        double precio = Double.parseDouble(precioText);
        Productos producto = new Productos(codigo, nombre, catalogo, marca, precio, stock);

        ProductosDAO dao = new ProductosDAO();
        if (dao.crear(producto)) {
            lblMensaje.setText("Producto guardado correctamente");
            limpiarCampos();
            cargaTable();
        } else {
            lblMensaje.setText("Error creando producto");
        }
    }

    @FXML
    public void onActualizarClick() {
        String codigo = txtCodigo.getText();
        String nombre = txtNombre.getText();
        String catalogo = cbxCatalogo.getValue();
        String marca = txtMarca.getText();
        String precioText = txtPrecio.getText();
        Integer stock = cbxStock.getValue();

        if (codigo == null || codigo.trim().isEmpty()) {
            lblMensaje.setText("El codigo es obligatorio para actualizar");
            return;
        }

        double precio = 0;
        if (precioText != null && !precioText.trim().isEmpty()) {
            if (!precioText.matches("\\d+(\\.\\d+)?")) {
                lblMensaje.setText("El precio debe ser un numero");
                return;
            }
            precio = Double.parseDouble(precioText);
        }

        Productos producto = new Productos(codigo, nombre, catalogo, marca, precio, stock);

        ProductosDAO dao = new ProductosDAO();
        if (dao.actualizar(producto)) {
            lblMensaje.setText("Producto actualizado correctamente");
            limpiarCampos();
            cargaTable();
        } else {
            lblMensaje.setText("Error actualizando producto");
        }
    }

    @FXML
    public void onBuscarClick() {
        ProductosDAO dao = new ProductosDAO();
        Productos producto = dao.buscar(txtCodigo.getText());
        if (producto != null) {
            txtCodigo.setText(producto.getCodigo());
            txtNombre.setText(producto.getNombre());
            cbxCatalogo.setValue(producto.getCatalogo());
            txtMarca.setText(producto.getMarca());
            txtPrecio.setText(String.valueOf(producto.getPrecio()));
            cbxStock.getValueFactory().setValue(producto.getStock());
            lblMensaje.setText("");
        } else {
            lblMensaje.setText("No existe el producto");
        }
    }

    @FXML
    public void onEliminarClick() {
        ProductosDAO dao = new ProductosDAO();
        if (dao.eliminar(txtCodigo.getText())) {
            lblMensaje.setText("Producto eliminado correctamente");
            limpiarCampos();
            cargaTable();
        } else {
            lblMensaje.setText("Error eliminando producto");
        }
    }

    @FXML
    public void onSalirClick() {
        Stage actual = (Stage) tableProductos.getScene().getWindow();
        actual.close();
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtSerie.setText("");
        txtNombre.setText("");
        cbxCatalogo.setValue(null);
        txtMarca.setText("");
        txtPrecio.setText("");
        cbxStock.getValueFactory().setValue(0);
    }

    private void cargaTable() {
        ProductosDAO dao = new ProductosDAO();
        ObservableList<Productos> lista = FXCollections.observableArrayList();
        lista.addAll(dao.listar());
        tableProductos.setItems(lista);
    }
}
