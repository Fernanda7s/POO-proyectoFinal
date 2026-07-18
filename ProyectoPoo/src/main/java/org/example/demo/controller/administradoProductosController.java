package org.example.demo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;


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
    private TableColumn clmCodigo;
    @FXML
    private TableColumn clmNombre;
    @FXML
    private TableColumn clmCatalogo;
    @FXML
    private TableColumn clmMarca;
    @FXML
    private TableColumn clmStock;
    @FXML
    private TableColumn clmPrecio;
    @FXML
    private TableView tableProductos;
    @FXML
    public void initialize(){
        cbxCatalogo.getItems().addAll(
            "cedulares",
                "audifonos",
                "cargadores"
        );

    }
    @FXML
    public void onCrearClick(){

    }
    @FXML
    public void onActualizarClick(){}
    @FXML
    public void onBuscarClick(){}
    @FXML
    public void onEliminarClick(){}
    @FXML
    public void onSalirClick(){}



}
