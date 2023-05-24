package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;

public class BorrarCliente extends Controlador {

    @FXML
    private TextField tfDniBuscado;

    @FXML
    void ratonPulsaBorrar(MouseEvent event) {
    	tfDniBuscado.clear();
    }

    @FXML
    void ratonPulsaCheck(MouseEvent event) {
    	try {
			VistaGrafica.getInstancia().getControlador().borrar(Cliente.getClienteConDni(tfDniBuscado.getText()));
			Dialogos.mostrarDialogoAdvertencia("AVISO: Borrado cliente", "El cliente se ha borrado correctamente",getEscenario());
			getEscenario().close();
    	} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			Dialogos.mostrarDialogoError("ERROR: Excepcion borrar cliente", e.getMessage(), getEscenario());
		}
    }
    
    @FXML
    void ratonPulsaCancelar(MouseEvent event) {
    	tfDniBuscado.clear();
    	
    	getEscenario().close();
    }

	void ventanaCerrada(WindowEvent event) {
    	tfDniBuscado.clear();
	}
}
