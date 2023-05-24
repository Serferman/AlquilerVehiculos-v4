package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.util.ArrayList;
import java.util.List;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.WindowEvent;

public class ListarVehiculos extends Controlador {

	@FXML
	private TableView<Vehiculo> tvVehiculos;

	@FXML
	private RadioButton rdAutobuses;

	@FXML
	private RadioButton rdFurgoneta;

	@FXML
	private RadioButton rdTurismos;
	
	private ToggleGroup grupoRadioButtons;

	private static final String ESTILO_CENTRADO_4_COLUMNAS = "-fx-alignment: CENTER; -fx-max-width: 100;";
	private static final String ESTILO_CENTRADO_5_COLUMNAS = "-fx-alignment: CENTER; -fx-max-width: 80;";
	
	private static final String DATO_MARCA = "marca";
	private static final String DATO_MODELO = "modelo";
	private static final String DATO_MATRICULA = "matricula";
	private static final String DATO_CILINDRADA = "cilindrada";
	private static final String DATO_PMA = "pma";
	private static final String DATO_PLAZAS = "plazas";

	private static final String NOMBRE_COLUMNA_MARCA = "Marca";
	private static final String NOMBRE_COLUMNA_MODELO = "Modelo";
	private static final String NOMBRE_COLUMNA_MATRICULA = "Matricula";
	private static final String NOMBRE_COLUMNA_CILINDRADA = "Cilindrada";
	private static final String NOMBRE_COLUMNA_PMA = "PMA";
	private static final String NOMBRE_COLUMNA_PLAZAS = "Plazas";
	
	@FXML
	void initialize() {

		grupoRadioButtons = new ToggleGroup();
	
		rdAutobuses.selectedProperty().addListener(e -> construirTablaAutobuses());
		rdFurgoneta.selectedProperty().addListener(e -> construirTablaFurgoneta());
		rdTurismos.selectedProperty().addListener(e -> construirTablaTurismos());

		rdAutobuses.setToggleGroup(grupoRadioButtons);
		rdFurgoneta.setToggleGroup(grupoRadioButtons);
		rdTurismos.setToggleGroup(grupoRadioButtons);
		
	}

	void construirTablaTurismos() {

		if (tvVehiculos.getColumns().isEmpty()) {
			TableColumn<Vehiculo, String> tcMarca = new TableColumn<>(NOMBRE_COLUMNA_MARCA);
			TableColumn<Vehiculo, String> tcModelo = new TableColumn<>(NOMBRE_COLUMNA_MODELO);
			TableColumn<Vehiculo, String> tcMatricula = new TableColumn<>(NOMBRE_COLUMNA_MATRICULA);
			TableColumn<Vehiculo, String> tcCilindrada = new TableColumn<>(NOMBRE_COLUMNA_CILINDRADA);

			tvVehiculos.getColumns().add(tcMarca);
			tvVehiculos.getColumns().add(tcModelo);
			tvVehiculos.getColumns().add(tcMatricula);
			tvVehiculos.getColumns().add(tcCilindrada);

			tcMarca.setCellValueFactory(new PropertyValueFactory<>(DATO_MARCA));
			tcModelo.setCellValueFactory(new PropertyValueFactory<>(DATO_MODELO));
			tcMatricula.setCellValueFactory(new PropertyValueFactory<>(DATO_MATRICULA));
			tcCilindrada.setCellValueFactory(new PropertyValueFactory<>(DATO_CILINDRADA));

			tcMarca.setStyle(ESTILO_CENTRADO_4_COLUMNAS);
			tcModelo.setStyle(ESTILO_CENTRADO_4_COLUMNAS);
			tcMatricula.setStyle(ESTILO_CENTRADO_4_COLUMNAS);
			tcCilindrada.setStyle(ESTILO_CENTRADO_4_COLUMNAS);
			
			List<Vehiculo> listaTurismos = new ArrayList<>();

			for (Vehiculo vehiculo : VistaGrafica.getInstancia().getControlador().getVehiculos()) {
				if (vehiculo instanceof Turismo) {
					listaTurismos.add(vehiculo);
				}
			}

			tvVehiculos.setItems(FXCollections.observableList(listaTurismos));
		} else {
			tvVehiculos.getColumns().clear();
		}
	}

	void construirTablaFurgoneta() {

		if (tvVehiculos.getColumns().isEmpty()) {
			TableColumn<Vehiculo, String> tcMarca = new TableColumn<>(NOMBRE_COLUMNA_MARCA);
			TableColumn<Vehiculo, String> tcModelo = new TableColumn<>(NOMBRE_COLUMNA_MODELO);
			TableColumn<Vehiculo, String> tcMatricula = new TableColumn<>(NOMBRE_COLUMNA_MATRICULA);
			TableColumn<Vehiculo, String> tcPMA = new TableColumn<>(NOMBRE_COLUMNA_PMA);
			TableColumn<Vehiculo, String> tcPlazas = new TableColumn<>(NOMBRE_COLUMNA_PLAZAS);

			tcMarca.setStyle(ESTILO_CENTRADO_5_COLUMNAS);
			tcModelo.setStyle(ESTILO_CENTRADO_5_COLUMNAS);
			tcMatricula.setStyle(ESTILO_CENTRADO_5_COLUMNAS);
			tcPMA.setStyle(ESTILO_CENTRADO_5_COLUMNAS);
			tcPlazas.setStyle(ESTILO_CENTRADO_5_COLUMNAS);
			
			tvVehiculos.getColumns().add(tcMarca);
			tvVehiculos.getColumns().add(tcModelo);
			tvVehiculos.getColumns().add(tcMatricula);
			tvVehiculos.getColumns().add(tcPMA);
			tvVehiculos.getColumns().add(tcPlazas);

			tcMarca.setCellValueFactory(new PropertyValueFactory<>(DATO_MARCA));
			tcModelo.setCellValueFactory(new PropertyValueFactory<>(DATO_MODELO));
			tcMatricula.setCellValueFactory(new PropertyValueFactory<>(DATO_MATRICULA));
			tcPMA.setCellValueFactory(new PropertyValueFactory<>(DATO_PMA));
			tcPlazas.setCellValueFactory(new PropertyValueFactory<>(DATO_PLAZAS));
			
			List<Vehiculo> listaFurgoneta = new ArrayList<>();

			for (Vehiculo vehiculo : VistaGrafica.getInstancia().getControlador().getVehiculos()) {
				if (vehiculo instanceof Furgoneta) {
					listaFurgoneta.add(vehiculo);
				}
			}

			tvVehiculos.setItems(FXCollections.observableList(listaFurgoneta));
		} else {
			tvVehiculos.getColumns().clear();
		}
	}

	void construirTablaAutobuses() {
		
		if (tvVehiculos.getColumns().isEmpty()) {
			TableColumn<Vehiculo, String> tcMarca = new TableColumn<>(NOMBRE_COLUMNA_MARCA);
			TableColumn<Vehiculo, String> tcModelo = new TableColumn<>(NOMBRE_COLUMNA_MODELO);
			TableColumn<Vehiculo, String> tcMatricula = new TableColumn<>(NOMBRE_COLUMNA_MATRICULA);
			TableColumn<Vehiculo, String> tcPlazas = new TableColumn<>(NOMBRE_COLUMNA_PLAZAS);

			tvVehiculos.getColumns().add(tcMarca);
			tvVehiculos.getColumns().add(tcModelo);
			tvVehiculos.getColumns().add(tcMatricula);
			tvVehiculos.getColumns().add(tcPlazas);

			tcMarca.setCellValueFactory(new PropertyValueFactory<>(DATO_MARCA));
			tcModelo.setCellValueFactory(new PropertyValueFactory<>(DATO_MODELO));
			tcMatricula.setCellValueFactory(new PropertyValueFactory<>(DATO_MATRICULA));
			tcPlazas.setCellValueFactory(new PropertyValueFactory<>(DATO_PLAZAS));
			
			tcMarca.setStyle(ESTILO_CENTRADO_4_COLUMNAS);
			tcModelo.setStyle(ESTILO_CENTRADO_4_COLUMNAS);
			tcMatricula.setStyle(ESTILO_CENTRADO_4_COLUMNAS);
			tcPlazas.setStyle(ESTILO_CENTRADO_4_COLUMNAS);

			List<Vehiculo> listaAutobus = new ArrayList<>();

			for (Vehiculo vehiculo : VistaGrafica.getInstancia().getControlador().getVehiculos()) {
				if (vehiculo instanceof Autobus) {
					listaAutobus.add(vehiculo);
				}
			}

			tvVehiculos.setItems(FXCollections.observableList(listaAutobus));
		} else {
			tvVehiculos.getColumns().clear();
		}
	}
	
	void ventanaCerrada(WindowEvent event) {
		grupoRadioButtons.selectToggle(null);
	}
	
}
