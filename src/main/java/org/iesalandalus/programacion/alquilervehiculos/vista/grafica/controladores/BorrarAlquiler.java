package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class BorrarAlquiler extends Controlador {

	@FXML
	private DatePicker dpFechaAlquiler;

	@FXML
	private ListView<Cliente> lvClientes;

	@FXML
	private ListView<Vehiculo> lvVehiculos;

	@FXML
	void initialize() {
		lvClientes.setItems(FXCollections.observableList(VistaGrafica.getInstancia().getControlador().getClientes()));
		lvVehiculos.setItems(FXCollections.observableList(VistaGrafica.getInstancia().getControlador().getVehiculos()));
	}

	@FXML
    void ratonPulsaAceptar(MouseEvent event) {
    
		try {
	    
			if ((lvClientes.getSelectionModel() == null) || (lvVehiculos.getSelectionModel() == null) || (dpFechaAlquiler.getValue() == null)) {
	    		Dialogos.mostrarDialogoAdvertencia("ERROR: Excepcion borrar alquiler", "Tienes que seleccionar un vehículo, un cliente y una fecha", getEscenario());
	    	} else {
	    		Dialogos.mostrarDialogoInformacion("AVISO: Borrado alquiler", VistaGrafica.getInstancia().getControlador().buscar(new Alquiler(lvClientes.getSelectionModel().getSelectedItem(),lvVehiculos.getSelectionModel().getSelectedItem(),dpFechaAlquiler.getValue())).toString(), getEscenario());
	    		VistaGrafica.getInstancia().getControlador().borrar(new Alquiler(lvClientes.getSelectionModel().getSelectedItem(),lvVehiculos.getSelectionModel().getSelectedItem(),dpFechaAlquiler.getValue()));
	    		getEscenario().close();
	    	}	
		
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			Dialogos.mostrarDialogoError("ERROR: Excepcion borrar alquiler", e.getMessage(), getEscenario());
		}
    }

	@FXML
	void ratonPulsaBorrar(MouseEvent event) {
		lvClientes.getSelectionModel().clearSelection();
		lvVehiculos.getSelectionModel().clearSelection();
		dpFechaAlquiler.setValue(null);
	}

	@FXML
	void ratonPulsaCancelar(MouseEvent event) {
		getEscenario().close();
	}
	
}
