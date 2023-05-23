package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;

public class DevolverCliente extends Controlador{
	
    @FXML
    private DatePicker dpFechaAlquiler;

    @FXML
    void ratonPulsaBorrar(MouseEvent event) {
    	dpFechaAlquiler.setValue(null);
    }

    @FXML
    void ratonPulsaCheck(MouseEvent event) {
    	try {
			VistaGrafica.getInstancia().getControlador().devolver(BuscarCliente.cliente, dpFechaAlquiler.getValue());
		} catch ( NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			Dialogos.mostrarDialogoError("ERROR: Excepcion devolver alquiler del cliente", e.getMessage(), getEscenario());
		}
    	getEscenario().close();
    }
}
