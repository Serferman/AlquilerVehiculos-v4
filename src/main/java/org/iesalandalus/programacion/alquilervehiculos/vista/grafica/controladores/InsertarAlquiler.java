package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class InsertarAlquiler extends Controlador {

    @FXML
    private DatePicker dpFechaAlquiler;

    @FXML
    private TextField tfDni;

    @FXML
    private TextField tfMatricula;

    // Crea un Alquiler
    public Alquiler getAlquiler() {
    	Cliente dni = Cliente.getClienteConDni(tfDni.getText());
    	Vehiculo matricula = Vehiculo.getVehiculoConMatricula(tfMatricula.getText()) ;
    	LocalDate fechaAlquiler = dpFechaAlquiler.getValue();
    			
    	return new Alquiler(dni, matricula, fechaAlquiler);
    }
    
    @FXML
    void ratonPulsaAceptar(MouseEvent event) {
    	try {
			VistaGrafica.getInstancia().getControlador().insertar(getAlquiler());
			Dialogos.mostrarDialogoAdvertencia("AVISO: Insercion alquiler", "El alquiler se ha insertado correctamente", getEscenario());
			getEscenario().close();
			
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			Dialogos.mostrarDialogoError("ERROR: Excepcion insertar alquiler", e.getMessage(), getEscenario());
		}
    }

    @FXML
    void ratonPulsaCancelar(MouseEvent event) {
    	getEscenario().close();
    	
    	tfDni.clear();
    	tfMatricula.clear();
    	dpFechaAlquiler.setValue(null);
    }
    
    @FXML
    void ratonPulsaBorrar(MouseEvent event) {
    	tfDni.clear();
    	tfMatricula.clear();
    	dpFechaAlquiler.setValue(null);
    }
}