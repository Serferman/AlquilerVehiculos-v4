package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controladores;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class BuscarCliente extends Controlador {

	@FXML
	private TextField tfDniBuscado;

    @FXML
    private Label lDni;

	@FXML
	private TextField tfNombre;

	@FXML
	private TextField tfTelefono;

	// Posibles mejoras 
	// ------ Actualizar en vivo los campos cuando se pulsa modificar ------
	
	private static final String SOMBRA_ROJA = "-fx-effect: dropshadow(Three-pass-box, red, 10, 0, 0, 0 );";
	private static final String SOMBRA_VERDE = "-fx-effect: dropshadow(Three-pass-box, green, 10, 0, 0, 0 );";
	private static final String OPACIDAD_MEDIA = "-fx-opacity: 0.5;";
	private static final String OPACIDAD_COMPLETA = "-fx-opacity: 1;";
	
	static Cliente cliente;
	
	@FXML
	void initialize() {
		tfDniBuscado.textProperty().addListener((ob, ov, nv) -> comprobarTextoDniBuscado(nv));
		tfNombre.textProperty().addListener((ob, ov, nv) -> comprobarTextoNombre(nv));
		tfTelefono.textProperty().addListener((ob, ov, nv) -> comprobarTextoTelefono(nv));
	}

	// ---------------------------------------------------------------------------------------------------
	
	void comprobarTextoDniBuscado(String nuevoValor) {
		if (!nuevoValor.matches("\\d{8}[A-Z]")) {
			tfDniBuscado.setStyle(SOMBRA_ROJA);
		} else {
			tfDniBuscado.setStyle(SOMBRA_VERDE);
		}
	}
	
	void comprobarTextoNombre(String nuevoValor) {
		if (!nuevoValor.matches("([A-Z][a-z]+)\\s?([A-Z]{1}[a-z]+\\s*)*")) {
			tfNombre.setStyle(SOMBRA_ROJA);
		} else {
			tfNombre.setStyle(SOMBRA_VERDE);
		} 
	}
	
	void comprobarTextoTelefono(String nuevoValor) {
		if (!nuevoValor.matches("\\d{9}")) {
			tfTelefono.setStyle(SOMBRA_ROJA);
		} else {
			tfTelefono.setStyle(SOMBRA_VERDE);
		} 
	}
	
	// ---------------------------------------------------------------------------------------------------
	
	@FXML
	void ratonPulsaCheck(MouseEvent event) {
		try {
			
			cliente = VistaGrafica.getInstancia().getControlador().buscar(Cliente.getClienteConDni(tfDniBuscado.getText()));

			lDni.setText(cliente.getDni());

			tfNombre.setText(cliente.getNombre());
			tfNombre.setDisable(false);
			tfNombre.setStyle(OPACIDAD_COMPLETA);

			tfTelefono.setText(cliente.getTelefono());
			tfTelefono.setDisable(false);
			tfTelefono.setStyle(OPACIDAD_COMPLETA);

			Dialogos.mostrarDialogoAdvertencia("AVISO: Busqueda cliente", "El cliente se ha buscado correctamente",
					getEscenario());

		} catch (NullPointerException | IllegalArgumentException e) {
			Dialogos.mostrarDialogoError("ERROR: Excepcion buscar cliente", e.getMessage(), getEscenario());
		}
	}

	@FXML
	void ratonPulsaBorrar(MouseEvent event) {
		tfDniBuscado.clear();

		lDni.setText(null);
		lDni.setDisable(true);
		lDni.setStyle(OPACIDAD_MEDIA);

		tfNombre.clear();
		tfNombre.setDisable(true);
		tfNombre.setStyle(OPACIDAD_MEDIA);

		tfTelefono.clear();
		tfTelefono.setDisable(true);
		tfTelefono.setStyle(OPACIDAD_MEDIA);
	}

	@FXML
	void ratonPulsaModificar(MouseEvent event) {
		try {
			VistaGrafica.getInstancia().getControlador().modificar(Cliente.getClienteConDni(tfDniBuscado.getText()),tfNombre.getText(), tfTelefono.getText());
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			Dialogos.mostrarDialogoError("ERROR: Excepcion modificar cliente", e.getMessage(), getEscenario());
		}
	}
	

    @FXML
    void ratonPulsaDevolver(MouseEvent event) {
    	try {
    		
    		if (cliente == null) {
    		 throw new NullPointerException("ERROR: No puedes devolver un alquiler de un cliente nulo.");	
    		}
    		
    		DevolverCliente ventanaDevolverCliente = (DevolverCliente) Controladores.get("vistas/devolverCliente.fxml", "Vista grafica de devolver cliente", getEscenario());
    		ventanaDevolverCliente.getEscenario().showAndWait();
    		
    	} catch ( NullPointerException | IllegalArgumentException e) {
			Dialogos.mostrarDialogoError("ERROR: Excepcion devolver cliente", e.getMessage(), getEscenario());
		}
    }

	@FXML
	void ratonPulsaCancelar(MouseEvent event) {
		getEscenario().close();
	}

}
