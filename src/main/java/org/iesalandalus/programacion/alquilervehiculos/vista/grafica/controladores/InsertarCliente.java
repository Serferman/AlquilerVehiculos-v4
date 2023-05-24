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

public class InsertarCliente extends Controlador {

	@FXML
	private TextField tfNombre;
	
	@FXML
	private TextField tfDni;
	
	@FXML
	private TextField tfTelefono;

    // Crea un Cliente
	public Cliente getCliente() {
		String nombre = tfNombre.getText();
		String dni = tfDni.getText();
		String telefono = tfTelefono.getText();
		
		return new Cliente(nombre, dni, telefono);
	}

	@FXML
	void ratonPulsaAceptar(MouseEvent event) {
		try {
			VistaGrafica.getInstancia().getControlador().insertar(getCliente());
			Dialogos.mostrarDialogoAdvertencia("AVISO: Insercion cliente", "El cliente se ha insertado correctamente", getEscenario());
			getEscenario().close();
			
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			Dialogos.mostrarDialogoError("ERROR: Excepcion insertar cliente", e.getMessage(), getEscenario());	
		}
	}

	@FXML
	void ratonPulsaCancelar(MouseEvent event) {
		getEscenario().close();
		
    	tfNombre.clear();
    	tfDni.clear();
    	tfTelefono.clear();
	}


    @FXML
    void ratonPulsaBorrar(MouseEvent event) {
    	tfNombre.clear();
    	tfDni.clear();
    	tfTelefono.clear();
    }
    
    void ventanaCerrada(WindowEvent event) {
    	tfNombre.clear();
    	tfDni.clear();
    	tfTelefono.clear();
	}
}
