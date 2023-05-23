package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controladores;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class BuscarVehiculo extends Controlador {

	@FXML
	private Label lCIlindradas;

	@FXML
	private Label lMarca;

	@FXML
	private Label lMatricula;

	@FXML
	private Label lModelo;

	@FXML
	private Label lPMA;

	@FXML
	private Label lPlazas;

	@FXML
	private TextField tfMatriculaBuscado;

	static Vehiculo vehiculo;
	
	@FXML
	void initialize() {
		tfMatriculaBuscado.textProperty().addListener((ob, ov, nv) -> comprobarTextoMatriculaBuscada(nv));
	}

	void comprobarTextoMatriculaBuscada(String nuevoValor) {
		if (!nuevoValor.matches("\\d{4}[BCDFGHJKLMNPRSTVWXYZ]{3}")) {
			tfMatriculaBuscado.setStyle("-fx-effect: dropshadow(Three-pass-box, red, 10, 0, 0, 0 );");
		} else {
			tfMatriculaBuscado.setStyle("-fx-effect: dropshadow(Three-pass-box, green, 10, 0, 0, 0 );");
		}
	}

	@FXML
	void ratonPulsaBorrar(MouseEvent event) {
		lMarca.setText(null);
		lMarca.setDisable(true);

		lModelo.setText(null);
		lModelo.setDisable(true);

		lMatricula.setText(null);
		lMatricula.setDisable(true);

		lCIlindradas.setText(null);
		lCIlindradas.setDisable(true);

		lPlazas.setText(null);
		lPlazas.setDisable(true);

		lPMA.setText(null);
		lPMA.setDisable(true);

		tfMatriculaBuscado.clear();
		tfMatriculaBuscado.setStyle(null);

	}

	@FXML
	void ratonPulsaCancelar(MouseEvent event) {
		getEscenario().close();
	}

	@FXML
	void ratonPulsaCheck(MouseEvent event) {
		try {

			vehiculo = VistaGrafica.getInstancia().getControlador().buscar(Vehiculo.getVehiculoConMatricula(tfMatriculaBuscado.getText()));

			lMarca.setText(vehiculo.getMarca());
			lMatricula.setText(vehiculo.getMatricula());
			lModelo.setText(vehiculo.getModelo());

			if (vehiculo instanceof Turismo turismo) {
				lCIlindradas.setText(String.format("%s", turismo.getCilindrada()));
				lPlazas.setText(null);
				lPMA.setText(null);
			} else if (vehiculo instanceof Furgoneta furgoneta) {
				lPMA.setText(String.format("%s", furgoneta.getPma()));
				lPlazas.setText(String.format("%s", furgoneta.getPlazas()));
				lCIlindradas.setText(null);
			} else if (vehiculo instanceof Autobus autobus) {
				lPlazas.setText(String.format("%s", autobus.getPlazas()));
				lCIlindradas.setText(null);
				lPMA.setText(null);
			}

			Dialogos.mostrarDialogoAdvertencia("AVISO: Busqueda vehículo", "El vehículo se ha buscado correctamente",
					getEscenario());

		} catch (NullPointerException | IllegalArgumentException e) {
			Dialogos.mostrarDialogoError("ERROR: Excepcion buscar vehículo", e.getMessage(), getEscenario());
		}
	}

	@FXML
	void ratonPulsaDevolver(MouseEvent event) {
		try {

			if (vehiculo == null) {
				throw new NullPointerException("ERROR: No puedes devolver un alquiler de un vehiculo nulo.");
			}

			DevolverVehiculo ventanaDevolverVehiculo = (DevolverVehiculo) Controladores.get("vistas/devolverVehiculo.fxml", "Vista grafica de devolver vehiculo", getEscenario());
			ventanaDevolverVehiculo.getEscenario().showAndWait();

		} catch (NullPointerException | IllegalArgumentException e) {
			Dialogos.mostrarDialogoError("ERROR: Excepcion devolver vehiculo", e.getMessage(), getEscenario());
		}
	}

}
