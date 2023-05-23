package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class InsertarVehiculo extends Controlador {
    
	@FXML
    private ChoiceBox<String> chbTipoVehiculo;
	
	@FXML
	private TextField tfMarca;

	@FXML
	private TextField tfModelo;
	    
    @FXML
    private TextField tfMatricula;
	
	@FXML
    private TextField tfCilindradas;

    @FXML
    private TextField tfPMA;

    @FXML
    private TextField tfPlazas;
    
    private static final String TURISMO = "Turismo";
    private static final String AUTOBUS = "Autobus";
    private static final String FURGONETA = "Furgoneta";
    
	private static final String OPACIDAD_MEDIA = "-fx-opacity: 0.5;";
	private static final String OPACIDAD_COMPLETA = "-fx-opacity: 1;";
    
    @FXML
    void initialize() {
		chbTipoVehiculo.setItems(FXCollections.observableArrayList(TURISMO, AUTOBUS, FURGONETA));
		chbTipoVehiculo.valueProperty().addListener(nv -> comprobarTipoVehiculo());
    }
    
    void comprobarTipoVehiculo() {
    	
		String opcionSeleccionada = chbTipoVehiculo.valueProperty().getValue();
		
    	if (opcionSeleccionada != null) {
	    	if (opcionSeleccionada.equals(TURISMO)) {
				tfPlazas.setDisable(true);
				tfPlazas.setStyle(OPACIDAD_MEDIA);
				
				tfPMA.setDisable(true);
				tfPMA.setStyle(OPACIDAD_MEDIA);
				
				tfCilindradas.setDisable(false);
				tfCilindradas.setStyle(OPACIDAD_COMPLETA);
				
			} else if (opcionSeleccionada.equals(AUTOBUS)) {
				tfPMA.setDisable(true);
				tfPMA.setStyle(OPACIDAD_MEDIA);
				
				tfCilindradas.setDisable(true);
				tfCilindradas.setStyle(OPACIDAD_MEDIA);
				
				tfPlazas.setDisable(false);
				tfPlazas.setStyle(OPACIDAD_COMPLETA);
				
			} else if (opcionSeleccionada.equals(FURGONETA)) {
				tfCilindradas.setDisable(true);
				tfCilindradas.setStyle(OPACIDAD_MEDIA);
				
				tfPlazas.setDisable(false);
				tfPlazas.setStyle(OPACIDAD_COMPLETA);
				
				tfPMA.setDisable(false);
				tfPMA.setStyle(OPACIDAD_COMPLETA);
				
			}
    	}
    }
    
    // Crea un vehiculo
    public Vehiculo getVehiculo() {
		String marca = tfMarca.getText();
		String modelo = tfModelo.getText();
		String matricula = tfMatricula.getText();
		
		Vehiculo vehiculo = null;
		
		String opcionSeleccionada = chbTipoVehiculo.getSelectionModel().getSelectedItem();
		
		if (opcionSeleccionada.equals(TURISMO)) {
			vehiculo = new Turismo(marca,modelo,Integer.parseInt(tfCilindradas.getText()),matricula);
		} else if (opcionSeleccionada.equals(AUTOBUS)) {
			vehiculo = new Autobus(marca, modelo, Integer.parseInt(tfPlazas.getText()),matricula);
		} else if (opcionSeleccionada.equals(FURGONETA)) {
			vehiculo = new Furgoneta(marca, modelo, Integer.parseInt(tfPMA.getText()),Integer.parseInt(tfPlazas.getText()),matricula);
		}
		
		return Vehiculo.copiar(vehiculo);
	}

    @FXML
    void ratonPulsaAceptar(MouseEvent event) {
    	try {
			VistaGrafica.getInstancia().getControlador().insertar(getVehiculo());
			Dialogos.mostrarDialogoAdvertencia("AVISO: Insercion vehículo", "El vehículo se ha insertado correctamente", getEscenario());
			getEscenario().close();
			
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			Dialogos.mostrarDialogoError("ERROR: Excepcion insertar vehículo", e.getMessage(), getEscenario());
		}
    }

    @FXML
    void ratonPulsaCancelar(MouseEvent event) {
    	getEscenario().close();
    	
    	chbTipoVehiculo.valueProperty().setValue(null);
    	
    	tfMarca.clear();
    	tfModelo.clear();
    	tfMatricula.clear();
    	tfCilindradas.clear();
    	tfPlazas.clear();
    	tfPMA.clear();
    }   
    
    @FXML
    void ratonPulsaBorrar(MouseEvent event) {
    	chbTipoVehiculo.valueProperty().set(null);
    
    	tfMarca.clear();
    	tfModelo.clear();
    	tfMatricula.clear();
    	tfCilindradas.clear();
    	tfPlazas.clear();
    	tfPMA.clear();
    }
}
