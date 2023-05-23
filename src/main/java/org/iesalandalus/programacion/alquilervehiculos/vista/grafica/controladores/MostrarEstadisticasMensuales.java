package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;

public class MostrarEstadisticasMensuales extends Controlador {

	@FXML
	private DatePicker dpFechaAlquiler;

	@FXML
	private PieChart pcEstadisticasMensuales;
	
	private static final String TURISMO = "Turismo";
	private static final String AUTOBUS = "Autobus";
	private static final String FURGONETA = "Furgoneta";
	
	@FXML
	private void initialize() {

		PieChart.Data turismo = new PieChart.Data(TURISMO, 1);
		PieChart.Data autobus = new PieChart.Data(AUTOBUS, 1);
		PieChart.Data furgoneta = new PieChart.Data(FURGONETA, 1);

		ObservableList<Data> listaGraficaQuesito = FXCollections.observableArrayList();
		listaGraficaQuesito.addAll(turismo, autobus, furgoneta);
		pcEstadisticasMensuales.setData(listaGraficaQuesito);
		
	}

	@FXML
	void ratonPulsaBorrar(MouseEvent event) {
		dpFechaAlquiler.setValue(null);
		pcEstadisticasMensuales.getData().clear();
	}

	@FXML
	void ratonPulsaCheck(MouseEvent event) {
		ObservableList<Data> pieChartlista = pcEstadisticasMensuales.getData();
		
		for(Data datos : pieChartlista) {
			datos.setPieValue(0);
		}
		
		for (Alquiler alquiler : VistaGrafica.getInstancia().getControlador().getAlquileres()) {
			if (alquiler.getFechaAlquiler().getYear() == dpFechaAlquiler.getValue().getYear() && alquiler.getFechaAlquiler().getMonth().equals(dpFechaAlquiler.getValue().getMonth())) {
				String tipoVehiculo = get(alquiler.getVehiculo());
				
				switch (tipoVehiculo) {
					case TURISMO: {
						pieChartlista.get(0).setPieValue(pieChartlista.get(0).getPieValue() + 1);
						break;
					}
					case AUTOBUS: {
						pieChartlista.get(1).setPieValue(pieChartlista.get(1).getPieValue() + 1);
						break;
					}
					case FURGONETA: {
						pieChartlista.get(2).setPieValue(pieChartlista.get(2).getPieValue() + 1);
						break;
					}
				}
			}
		}
	}

	String get(Vehiculo vehiculo) {
		String tipoVehiculoDevolver = null;

		if (vehiculo instanceof Turismo) {
			tipoVehiculoDevolver = TURISMO;
		} else if (vehiculo instanceof Autobus) {
			tipoVehiculoDevolver = AUTOBUS;
		} else if (vehiculo instanceof Furgoneta) {
			tipoVehiculoDevolver = FURGONETA;
		}

		return tipoVehiculoDevolver;

	}

}
