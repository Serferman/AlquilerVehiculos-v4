package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;

public class ListarAlquileres extends Controlador {

	@FXML
	private TextField tfClientes;

	@FXML
	private TextField tfVehiculos;

	@FXML
	private TableView<Alquiler> tvAlquileres;

	@FXML
	private TableColumn<Alquiler, String> tcNombre;

	@FXML
	private TableColumn<Alquiler, String> tcDni;

	@FXML
	private TableColumn<Alquiler, String> tcTelefono;

	@FXML
	private TableColumn<Alquiler, String> tcMarca;

	@FXML
	private TableColumn<Alquiler, String> tcModelo;

	@FXML
	private TableColumn<Alquiler, String> tcMatricula;

	@FXML
	private TableColumn<Alquiler, String> tcCilindrada;

	@FXML
	private TableColumn<Alquiler, String> tcPMA;

	@FXML
	private TableColumn<Alquiler, String> tcPlazas;

	@FXML
	private TableColumn<Alquiler, String> tcFechaAlquiler;

	@FXML
	private TableColumn<Alquiler, String> tcFechaDevolucion;

	private static final String ESTILO_CENTRADO_9_COLUMNAS = "-fx-alignment: CENTER;";
	private static final String OPACIDAD_MEDIA = "-fx-opacity: 0.5;";
	private static final String OPACIDAD_COMPLETA = "-fx-opacity: 1;";
	
	private static final String NOMBRE_COLUMNA_FECHA_ALQUILER = "fechaAlquiler";
	private static final String NOMBRE_COLUMNA_FECHA_DEVOLUCION = "fechaDevolucion";
	
	private static final String SOMBRA_ROJA = "-fx-effect: dropshadow(Three-pass-box, red, 10, 0, 0, 0 );";
	private static final String SOMBRA_VERDE = "-fx-effect: dropshadow(Three-pass-box, green, 10, 0, 0, 0 );";
	
	@FXML
	void initialize() {
		tfClientes.focusedProperty().addListener(e -> comprobarSeleccionClientes());
		tfVehiculos.focusedProperty().addListener(e -> comprobarSeleccionVehiculos());

		tcDni.setStyle(ESTILO_CENTRADO_9_COLUMNAS);
		tcNombre.setStyle(ESTILO_CENTRADO_9_COLUMNAS);
		tcTelefono.setStyle(ESTILO_CENTRADO_9_COLUMNAS);

		tcMarca.setStyle(ESTILO_CENTRADO_9_COLUMNAS);
		tcModelo.setStyle(ESTILO_CENTRADO_9_COLUMNAS);
		tcMatricula.setStyle(ESTILO_CENTRADO_9_COLUMNAS);
		tcPMA.setStyle(ESTILO_CENTRADO_9_COLUMNAS);
		tcPlazas.setStyle(ESTILO_CENTRADO_9_COLUMNAS);

		tcFechaAlquiler.setStyle(ESTILO_CENTRADO_9_COLUMNAS);
		tcFechaDevolucion.setStyle(ESTILO_CENTRADO_9_COLUMNAS);

		tcDni.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getCliente().getDni()));
		tcNombre.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getCliente().getNombre()));
		tcTelefono.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getCliente().getTelefono()));
		tcMarca.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getVehiculo().getMarca()));
		tcModelo.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getVehiculo().getModelo()));
		tcMatricula.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getVehiculo().getMatricula()));
		tcCilindrada.setCellValueFactory(
				fila -> new SimpleStringProperty(comprobarCilindradas(fila.getValue().getVehiculo())));
		tcPMA.setCellValueFactory(fila -> new SimpleStringProperty(comprobarPma(fila.getValue().getVehiculo())));
		tcPlazas.setCellValueFactory(fila -> new SimpleStringProperty(comprobarPlazas(fila.getValue().getVehiculo())));
		tcFechaAlquiler.setCellValueFactory(new PropertyValueFactory<>(NOMBRE_COLUMNA_FECHA_ALQUILER));
		tcFechaDevolucion.setCellValueFactory(new PropertyValueFactory<>(NOMBRE_COLUMNA_FECHA_DEVOLUCION));

		tvAlquileres.setItems(FXCollections.observableList(VistaGrafica.getInstancia().getControlador().getAlquileres()));
		
		tfClientes.textProperty().addListener((ob, ov, nv) -> comprobarTextoDniBuscado(nv));
		tfVehiculos.textProperty().addListener((ob, ov, nv) -> comprobarTextoMatriculaBuscada(nv));
	}

	// -------------------------------------------------------------------------------------------------------------------

	void comprobarTextoDniBuscado(String nuevoValor) {
		if (!nuevoValor.matches("\\d{8}[A-Z]")) {
			tfClientes.setStyle(SOMBRA_ROJA);
		} else {
			tfClientes.setStyle(SOMBRA_VERDE);
		}
	}
	
	void comprobarTextoMatriculaBuscada(String nuevoValor) {
		if (!nuevoValor.matches("\\d{4}[BCDFGHJKLMNPRSTVWXYZ]{3}")) {
			tfVehiculos.setStyle(SOMBRA_ROJA);
		} else {
			tfVehiculos.setStyle(SOMBRA_VERDE);
		}
	}
	
	// -------------------------------------------------------------------------------------------------------------------

	String comprobarCilindradas(Vehiculo vehiculo) {
		return (vehiculo instanceof Turismo turismo) ? String.format("%s", turismo.getCilindrada()) : "";
	}

	String comprobarPma(Vehiculo vehiculo) {
		return (vehiculo instanceof Furgoneta furgoneta) ? String.format("%s", furgoneta.getPma()) : "";
	}

	String comprobarPlazas(Vehiculo vehiculo) {
		String plazasTipoVehiculo = "";

		if (vehiculo instanceof Autobus autobus) {
			plazasTipoVehiculo = String.format("%s", autobus.getPlazas());
		} else if (vehiculo instanceof Furgoneta furgoneta) {
			plazasTipoVehiculo = String.format("%s", furgoneta.getPlazas());
		}

		return plazasTipoVehiculo;
	}

	// -------------------------------------------------------------------------------------------------------------------

	void comprobarSeleccionClientes() {
		tfClientes.setStyle(OPACIDAD_COMPLETA);

		tfVehiculos.clear();
		tfVehiculos.setStyle(OPACIDAD_MEDIA);
	}

	void comprobarSeleccionVehiculos() {
		tfVehiculos.setStyle(OPACIDAD_COMPLETA);

		tfClientes.clear();
		tfClientes.setStyle(OPACIDAD_MEDIA);
	}

	// -------------------------------------------------------------------------------------------------------------------

	@FXML
	void ratonPulsaCheck(MouseEvent event) {
		try {
			if (!tfClientes.getText().isEmpty()) {
				if (!tvAlquileres.getColumns().isEmpty()) {
					tvAlquileres.getColumns().clear();
	
					construirTablaListarClientes();
				} else {
					construirTablaListarClientes();
				}
			} else if (!tfVehiculos.getText().isEmpty()) {
				if (!tvAlquileres.getColumns().isEmpty()) {
					tvAlquileres.getColumns().clear();
	
					construirTablaListarVehiculos();
				} else {
					construirTablaListarVehiculos();
				}
			}
		} catch(NullPointerException | IllegalArgumentException e) {
			Dialogos.mostrarDialogoError("ERROR: Excepcion listar alquileres", e.getMessage(), getEscenario());
		}
	}

	void construirTablaListarClientes() {
		tvAlquileres.getColumns().add(tcNombre);
		tvAlquileres.getColumns().add(tcDni);
		tvAlquileres.getColumns().add(tcTelefono);

		tvAlquileres.getColumns().add(tcMarca);
		tvAlquileres.getColumns().add(tcModelo);
		tvAlquileres.getColumns().add(tcMatricula);
		tvAlquileres.getColumns().add(tcCilindrada);
		tvAlquileres.getColumns().add(tcPMA);
		tvAlquileres.getColumns().add(tcPlazas);

		tvAlquileres.getColumns().add(tcFechaAlquiler);
		tvAlquileres.getColumns().add(tcFechaDevolucion);

		tcDni.setStyle(ESTILO_CENTRADO_9_COLUMNAS);
		tcNombre.setStyle(ESTILO_CENTRADO_9_COLUMNAS);
		tcTelefono.setStyle(ESTILO_CENTRADO_9_COLUMNAS);

		tcMarca.setStyle(ESTILO_CENTRADO_9_COLUMNAS);
		tcModelo.setStyle(ESTILO_CENTRADO_9_COLUMNAS);
		tcMatricula.setStyle(ESTILO_CENTRADO_9_COLUMNAS);
		tcPMA.setStyle(ESTILO_CENTRADO_9_COLUMNAS);
		tcPlazas.setStyle(ESTILO_CENTRADO_9_COLUMNAS);

		tcFechaAlquiler.setStyle(ESTILO_CENTRADO_9_COLUMNAS);
		tcFechaDevolucion.setStyle(ESTILO_CENTRADO_9_COLUMNAS);

		tcDni.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getCliente().getDni()));
		tcNombre.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getCliente().getNombre()));
		tcTelefono.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getCliente().getTelefono()));

		tcMarca.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getVehiculo().getMarca()));
		tcModelo.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getVehiculo().getModelo()));
		tcMatricula.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getVehiculo().getMatricula()));
		tcCilindrada.setCellValueFactory(fila -> new SimpleStringProperty(comprobarCilindradas(fila.getValue().getVehiculo())));
		tcPMA.setCellValueFactory(fila -> new SimpleStringProperty(comprobarPma(fila.getValue().getVehiculo())));
		tcPlazas.setCellValueFactory(fila -> new SimpleStringProperty(comprobarPlazas(fila.getValue().getVehiculo())));

		tcFechaAlquiler.setCellValueFactory(new PropertyValueFactory<>(NOMBRE_COLUMNA_FECHA_ALQUILER));
		tcFechaDevolucion.setCellValueFactory(new PropertyValueFactory<>(NOMBRE_COLUMNA_FECHA_DEVOLUCION));

		tvAlquileres.setItems(FXCollections.observableList(VistaGrafica.getInstancia().getControlador().getAlquileres(Cliente.getClienteConDni(tfClientes.getText()))));
	}

	void construirTablaListarVehiculos() {

		tvAlquileres.getColumns().add(tcNombre);
		tvAlquileres.getColumns().add(tcDni);
		tvAlquileres.getColumns().add(tcTelefono);

		tvAlquileres.getColumns().add(tcMarca);
		tvAlquileres.getColumns().add(tcModelo);
		tvAlquileres.getColumns().add(tcMatricula);
		tvAlquileres.getColumns().add(tcCilindrada);
		tvAlquileres.getColumns().add(tcPMA);
		tvAlquileres.getColumns().add(tcPlazas);

		tvAlquileres.getColumns().add(tcFechaAlquiler);
		tvAlquileres.getColumns().add(tcFechaDevolucion);

		tcDni.setStyle(ESTILO_CENTRADO_9_COLUMNAS);
		tcNombre.setStyle(ESTILO_CENTRADO_9_COLUMNAS);
		tcTelefono.setStyle(ESTILO_CENTRADO_9_COLUMNAS);

		tcMarca.setStyle(ESTILO_CENTRADO_9_COLUMNAS);
		tcModelo.setStyle(ESTILO_CENTRADO_9_COLUMNAS);
		tcMatricula.setStyle(ESTILO_CENTRADO_9_COLUMNAS);
		tcPMA.setStyle(ESTILO_CENTRADO_9_COLUMNAS);
		tcPlazas.setStyle(ESTILO_CENTRADO_9_COLUMNAS);

		tcFechaAlquiler.setStyle(ESTILO_CENTRADO_9_COLUMNAS);
		tcFechaDevolucion.setStyle(ESTILO_CENTRADO_9_COLUMNAS);

		tcDni.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getCliente().getDni()));
		tcNombre.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getCliente().getNombre()));
		tcTelefono.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getCliente().getTelefono()));

		tcMarca.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getVehiculo().getMarca()));
		tcModelo.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getVehiculo().getModelo()));
		tcMatricula.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getVehiculo().getMatricula()));
		tcCilindrada.setCellValueFactory(fila -> new SimpleStringProperty(comprobarCilindradas(fila.getValue().getVehiculo())));
		tcPMA.setCellValueFactory(fila -> new SimpleStringProperty(comprobarPma(fila.getValue().getVehiculo())));
		tcPlazas.setCellValueFactory(fila -> new SimpleStringProperty(comprobarPlazas(fila.getValue().getVehiculo())));

		tcFechaAlquiler.setCellValueFactory(new PropertyValueFactory<>(NOMBRE_COLUMNA_FECHA_ALQUILER));
		tcFechaDevolucion.setCellValueFactory(new PropertyValueFactory<>(NOMBRE_COLUMNA_FECHA_DEVOLUCION));

		tvAlquileres.setItems(FXCollections.observableList(VistaGrafica.getInstancia().getControlador().getAlquileres(Vehiculo.getVehiculoConMatricula(tfVehiculos.getText()))));

	}
	
	void ventanaCerrada(WindowEvent event) {
		tfClientes.clear();
		tfClientes.setStyle(null);
		tfVehiculos.clear();
		tfVehiculos.setStyle(null);
	}
}
