package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;

public class BorrarVehiculo extends Controlador {

	@FXML
    private TextField tfMatriculaBuscado;

    @FXML
    void ratonPulsaBorrar(MouseEvent event) {
    	tfMatriculaBuscado.clear();
    }

    @FXML
    void ratonPulsaCheck(MouseEvent event) {
    	try {
			VistaGrafica.getInstancia().getControlador().borrar(Vehiculo.getVehiculoConMatricula(tfMatriculaBuscado.getText()));
			Dialogos.mostrarDialogoAdvertencia("AVISO: Borrado vehículo", "El vehículo se ha borrado correctamente",getEscenario());
			getEscenario().close();
    	} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			Dialogos.mostrarDialogoError("ERROR: Excepcion borrar vehículo", e.getMessage(), getEscenario());
		}
    }
    
    @FXML
    void ratonPulsaCancelar(MouseEvent event) {
    	tfMatriculaBuscado.clear();
    	
    	getEscenario().close();
    }
    
    void ventanaCerrada(WindowEvent event) {
    	tfMatriculaBuscado.clear();
	}
}
