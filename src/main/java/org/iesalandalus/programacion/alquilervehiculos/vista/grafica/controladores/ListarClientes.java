package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListarClientes extends Controlador {

    @FXML
    private TableColumn<Cliente, String> tcDni;

    @FXML
    private TableColumn<Cliente, String> tcNombre;

    @FXML
    private TableColumn<Cliente, String> tcTelefono;

    @FXML
    private TableView<Cliente> tvClientes;
	
    private static final String ESTILO_CENTRADO = "-fx-alignment: CENTER; -fx-max-width: 146";
    
    @FXML
    void initialize() {
    	tcDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
    	tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	tcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
    	
    	tcDni.setStyle(ESTILO_CENTRADO);
		tcNombre.setStyle(ESTILO_CENTRADO);
		tcTelefono.setStyle(ESTILO_CENTRADO);
		
    	tvClientes.setItems(FXCollections.observableList(VistaGrafica.getInstancia().getControlador().getClientes()));
    }
    	
}
