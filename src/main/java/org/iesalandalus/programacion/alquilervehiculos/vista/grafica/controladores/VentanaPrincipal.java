package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controladores;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

	
public class VentanaPrincipal extends Controlador {

	@FXML
	private StackPane stackPaneMenuDesplegable;

	@FXML
	private void initialize() {
		System.out.println("Método initialize de VentanaPrincipal");
	}

	// ------------------------------------------------------------------------ BORRAR --------------------------------------------------------------------------------
	
	@FXML
	void btBorrarAlquiler(ActionEvent event) {
		BorrarAlquiler ventanaBorrarAlquiler = (BorrarAlquiler) Controladores.get("vistas/borrarAlquiler.fxml", "Vista grafica de borrar alquiler", getEscenario());
		ventanaBorrarAlquiler.getEscenario().showAndWait();
	}

	@FXML
	void btBorrarCliente(ActionEvent event) {
		BorrarCliente ventanaBorrarCliente = (BorrarCliente) Controladores.get("vistas/borrarCliente.fxml", "Vista grafica de borrar cliente", getEscenario());
		ventanaBorrarCliente.getEscenario().showAndWait();
	}

	@FXML
	void btBorrarVehiculo(ActionEvent event) {
		BorrarVehiculo ventanaBorrarVehiculo = (BorrarVehiculo) Controladores.get("vistas/borrarVehiculo.fxml", "Vista grafica de borrar vehiculo", getEscenario());
		ventanaBorrarVehiculo.getEscenario().showAndWait();
	}

	// ------------------------------------------------------------------------ BUSCAR --------------------------------------------------------------------------------

	@FXML
	void btBuscarAlquiler(ActionEvent event) {
		BuscarAlquiler ventanaBuscarAlquiler = (BuscarAlquiler) Controladores.get("vistas/buscarAlquiler.fxml", "Vista grafica de borrar alquiler", getEscenario());
		ventanaBuscarAlquiler.getEscenario().showAndWait();
	}

	@FXML
	void btBuscarCliente(ActionEvent event) {
		BuscarCliente ventanaBuscarCliente = (BuscarCliente) Controladores.get("vistas/buscarCliente.fxml", "Vista grafica de borrar cliente", getEscenario());
		ventanaBuscarCliente.getEscenario().showAndWait();
	}

	@FXML
	void btBuscarVehiculo(ActionEvent event) {
		BuscarVehiculo ventanaBuscarVehiculo = (BuscarVehiculo) Controladores.get("vistas/buscarVehiculo.fxml", "Vista grafica de borrar vehiculo", getEscenario());
		ventanaBuscarVehiculo.getEscenario().showAndWait();
	}

	// ----------------------------------------------------------------------- INSERTAR -------------------------------------------------------------------------------

	@FXML
	void btInsertarAlquiler(ActionEvent event) {
		InsertarAlquiler ventanaInsertarAlquiler = (InsertarAlquiler) Controladores.get("vistas/insertarAlquiler.fxml", "Vista gráfica de insertar alquiler", getEscenario());
		ventanaInsertarAlquiler.getEscenario().showAndWait();
	}

	@FXML
	void btInsertarCliente(ActionEvent event) {
		InsertarCliente ventanaInsertarCliente = (InsertarCliente) Controladores.get("vistas/insertarCliente.fxml", "Vista gráfica de insertar cliente", getEscenario());
		ventanaInsertarCliente.getEscenario().showAndWait();
	}

	@FXML
	void btInsertarVehiculo(ActionEvent event) {
		InsertarVehiculo ventanaInsertarVehiculo = (InsertarVehiculo) Controladores.get("vistas/insertarVehiculo.fxml", "Vista gráfica de insertar vehículo", getEscenario());
		ventanaInsertarVehiculo.getEscenario().showAndWait();
	}

	// ------------------------------------------------------------------------ LISTAR --------------------------------------------------------------------------------

	@FXML
	void btListarAlquiler(ActionEvent event) {
		ListarAlquileres ventanaListarAlquileres = (ListarAlquileres) Controladores.get("vistas/listarAlquileres.fxml", "Vista grafica de listar alquileres", getEscenario());
		ventanaListarAlquileres.getEscenario().showAndWait();
	}

	@FXML
	void btListarCliente(ActionEvent event) {
		ListarClientes ventanaListarClientes = (ListarClientes) Controladores.get("vistas/listarClientes.fxml", "Vista grafica de listar clientes", getEscenario());
		ventanaListarClientes.getEscenario().showAndWait();
	}

	@FXML
	void btListarVehiculo(ActionEvent event) {
		ListarVehiculos ventanaListarVehiculos = (ListarVehiculos) Controladores.get("vistas/listarVehiculos.fxml", "Vista grafica de listar vehiculos", getEscenario());
		ventanaListarVehiculos.getEscenario().showAndWait();
	}

	// ------------------------------------------------------------------

	@FXML
	void ratonEncimaBoton(MouseEvent event) {
		DropShadow sombra = new DropShadow();
		sombra.setColor(Color.GRAY);
		Button boton = (Button) event.getSource();
		boton.setEffect(sombra);
	}

	@FXML
	void ratonSaliendoBoton(MouseEvent event) {
		Button boton = (Button) event.getSource();
		boton.setEffect(null);
	}

	// ------------------------------------------------------------------
	
	VBox crearMenuVbox() {
		VBox vbox = new VBox();
		
		vbox.setId("estiloVboxMenuDesplegable");
		vbox.setAlignment(Pos.CENTER);
		vbox.setMaxWidth(120);
		vbox.setMaxHeight(250);
		
		return vbox;
	}
	
	Button crearBotonesMenu() {
		Button boton = new Button();
		
		boton.setId("estiloBotonesMenuDesplegable");
		boton.setMinWidth(120);
		boton.setOpacity(1.0);
		
		return boton;
	}
	
	void desplegarMenu() {
	
		TranslateTransition transicionDespliegue = new TranslateTransition(Duration.seconds(0.2), stackPaneMenuDesplegable);
		transicionDespliegue.setFromX(-10);
		transicionDespliegue.setToX(0);

		transicionDespliegue.play();
	}
	
	// ---------------------------------------------------------------------------------------------------------------------------------------------------------------

	@FXML
	void ratonPresionaBotonMenu(MouseEvent event) {
		VBox vbox = crearMenuVbox();
		
		if (!stackPaneMenuDesplegable.getChildren().isEmpty()) {	
			stackPaneMenuDesplegable.getChildren().clear();
		} else {

			Button botonSalir = crearBotonesMenu();
			Button botonGuardar = crearBotonesMenu();
			
			botonSalir.setText("Salir");
			botonSalir.setOnAction(this::salir);

			botonGuardar.setText("Exportar pdf");
			botonGuardar.setOnAction(this::exportarPdf);
			
			vbox.getChildren().addAll(botonGuardar, botonSalir);
			VBox.setMargin(botonSalir, new Insets(6));
			VBox.setMargin(botonGuardar, new Insets(6));
			
			stackPaneMenuDesplegable.getChildren().add(0, vbox);
			stackPaneMenuDesplegable.setAlignment(Pos.CENTER_LEFT);
			
			desplegarMenu();
		}
	}

	@FXML
	void salir(ActionEvent event) {
		System.exit(0);
	}
	
	@FXML
	void exportarPdf(ActionEvent event) {
	    
	        Document document = new Document();
	         
	        try{
	            PdfWriter.getInstance(document, new FileOutputStream("Tablas_Alquileres_Vehiculos_Clientes.pdf"));
	           
	            document.setPageSize(PageSize.A4.rotate());
	            document.open();
	           
	            PdfPTable tableAlquileres11Celdas = crearTablaAlquileres();
	            PdfPTable tableVehiculos8Celdas = crearTablaVehiculos();
	            PdfPTable tableClientes3Celdas = crearTablaClientes();
	            
	            Paragraph espacio = new Paragraph("\n"); 
	           
	            document.add(tableAlquileres11Celdas);
	            document.add(espacio);
	            document.add(tableVehiculos8Celdas);
	            document.add(espacio);
	            document.add(tableClientes3Celdas); 
	            
	            document.close();
	            
	            abrirPDF("Tablas_Alquileres_Vehiculos_Clientes.pdf");
	             
	        } catch(Exception e) {
				Dialogos.mostrarDialogoError("ERROR: Ocurrio un error al crear el archivo PDF", e.getMessage(), getEscenario());
	        }
	}
	
	void abrirPDF(String archivo ) {
        try {
            File archivoPDF = new File(archivo);

            if (archivoPDF.exists()) {
        		String os = System.getProperty("os.name").toLowerCase();

        		String command = "";

        		if (os.contains("win")) {
        			command = "cmd /c start";
        		} else if (os.contains("mac")) {
        			command = "open";
        		} else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
        			command = "xdg-open";
        		}
	
        		ProcessBuilder pb = new ProcessBuilder(command, archivo);
        		pb.start();
        
            } else {
				Dialogos.mostrarDialogoError("ERROR: No se puede abrir el archivo PDF", "ERROR: El archivo PDF que desea abrir no existe", getEscenario());
            }
            
        } catch (IOException e) {
			Dialogos.mostrarDialogoError("ERROR: Ocurrio un error al abrir el archivo PDF", e.getMessage(), getEscenario());
        }
	}
	
	PdfPTable crearTablaAlquileres() throws DocumentException {		
        PdfPTable tableAlquileres = new PdfPTable(11);                
        
        PdfPCell celdaTituloAlquileres = new PdfPCell(new Paragraph("Alquileres", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
        celdaTituloAlquileres.setColspan(11);
        celdaTituloAlquileres.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableAlquileres.addCell(celdaTituloAlquileres);

        float[] anchuraColumnas = { 50f, 60f, 60f, 80f, 50f, 60f, 0f, 35f, 50f, 90f, 110f };
        tableAlquileres.setTotalWidth(PageSize.A4.getWidth());
		tableAlquileres.setWidths(anchuraColumnas);
		
		Font fontTitulos = new Font(Font.FontFamily.HELVETICA, 10);
        
        tableAlquileres.addCell(new PdfPCell(new Phrase("Nombre", fontTitulos)));
        tableAlquileres.addCell(new PdfPCell(new Phrase("DNI", fontTitulos)));
        tableAlquileres.addCell(new PdfPCell(new Phrase("Telefono", fontTitulos)));
        tableAlquileres.addCell(new PdfPCell(new Phrase("Marca", fontTitulos)));
        tableAlquileres.addCell(new PdfPCell(new Phrase("Modelo", fontTitulos)));
        tableAlquileres.addCell(new PdfPCell(new Phrase("Matricula", fontTitulos)));
        tableAlquileres.addCell(new PdfPCell(new Phrase("Cilindrada", fontTitulos)));
        tableAlquileres.addCell(new PdfPCell(new Phrase("PMA", fontTitulos)));
        tableAlquileres.addCell(new PdfPCell(new Phrase("Plazas", fontTitulos)));
        tableAlquileres.addCell(new PdfPCell(new Phrase("Fecha Alquiler", fontTitulos)));
        tableAlquileres.addCell(new PdfPCell(new Phrase("Fecha Devolucion", fontTitulos))); 
        
        Font fontTextoCelda = new Font(Font.FontFamily.HELVETICA, 7);
        List<Alquiler> listaAlquileres = VistaGrafica.getInstancia().getControlador().getAlquileres();
        for (Alquiler alquiler : listaAlquileres) {
        	tableAlquileres.addCell(new PdfPCell(new Phrase(alquiler.getCliente().getNombre(), fontTextoCelda)));
            tableAlquileres.addCell(new PdfPCell(new Phrase(alquiler.getCliente().getDni(), fontTextoCelda)));
            tableAlquileres.addCell(new PdfPCell(new Phrase(alquiler.getCliente().getTelefono(), fontTextoCelda)));
            tableAlquileres.addCell(new PdfPCell(new Phrase(alquiler.getVehiculo().getMarca(), fontTextoCelda)));
            tableAlquileres.addCell(new PdfPCell(new Phrase(alquiler.getVehiculo().getModelo(), fontTextoCelda)));
            tableAlquileres.addCell(new PdfPCell(new Phrase(alquiler.getVehiculo().getMatricula(), fontTextoCelda)));
            
            tableAlquileres.addCell(new PdfPCell(new Phrase((alquiler.getVehiculo() instanceof Turismo turismo)? String.format("%s", turismo.getCilindrada()) : "", fontTextoCelda)));
            tableAlquileres.addCell(new PdfPCell(new Phrase((alquiler.getVehiculo() instanceof Furgoneta furgoneta)? String.format("%s", furgoneta.getPma()) : "", fontTextoCelda)));
            tableAlquileres.addCell(new PdfPCell(new Phrase((alquiler.getVehiculo() instanceof Autobus autobus)? String.format("%s", autobus.getPlazas()) : (alquiler.getVehiculo() instanceof Furgoneta furgoneta)? String.format("%s", furgoneta.getPlazas()) : "", fontTitulos)));
            
            tableAlquileres.addCell(new PdfPCell(new Phrase(String.format("%s", alquiler.getFechaAlquiler()), fontTextoCelda)));
            tableAlquileres.addCell(new PdfPCell(new Phrase(String.format("%s", alquiler.getFechaAlquiler()), fontTextoCelda)));
        }
       
        return tableAlquileres;
	}
	
	
	PdfPTable crearTablaVehiculos() {		
        PdfPTable tableVehiculos = new PdfPTable(6);                
        
        PdfPCell celdaTituloVehiculos = new PdfPCell(new Paragraph("Vehiculos", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
        celdaTituloVehiculos.setColspan(6);
        celdaTituloVehiculos.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableVehiculos.addCell(celdaTituloVehiculos);

        tableVehiculos.setTotalWidth(350f);
        tableVehiculos.setLockedWidth(true);

		Font fontTitulos = new Font(Font.FontFamily.HELVETICA, 10);
        
        tableVehiculos.addCell(new PdfPCell(new Phrase("Marca", fontTitulos)));
        tableVehiculos.addCell(new PdfPCell(new Phrase("Modelo", fontTitulos)));
        tableVehiculos.addCell(new PdfPCell(new Phrase("Matricula", fontTitulos)));
        tableVehiculos.addCell(new PdfPCell(new Phrase("Cilindrada", fontTitulos)));
        tableVehiculos.addCell(new PdfPCell(new Phrase("PMA", fontTitulos)));
        tableVehiculos.addCell(new PdfPCell(new Phrase("Plazas", fontTitulos)));
        
        Font fontTextoCelda = new Font(Font.FontFamily.HELVETICA, 7);
        List<Vehiculo> listaVehiculos = VistaGrafica.getInstancia().getControlador().getVehiculos();
        for (Vehiculo vehiculo : listaVehiculos) {
            tableVehiculos.addCell(new PdfPCell(new Phrase(vehiculo.getMarca(), fontTextoCelda)));
            tableVehiculos.addCell(new PdfPCell(new Phrase(vehiculo.getModelo(), fontTextoCelda)));
            tableVehiculos.addCell(new PdfPCell(new Phrase(vehiculo.getMatricula(), fontTextoCelda)));
            
            tableVehiculos.addCell(new PdfPCell(new Phrase((vehiculo instanceof Turismo turismo)? String.format("%s", turismo.getCilindrada()) : "", fontTextoCelda)));
            tableVehiculos.addCell(new PdfPCell(new Phrase((vehiculo instanceof Furgoneta furgoneta)? String.format("%s", furgoneta.getPma()) : "", fontTextoCelda)));
            tableVehiculos.addCell(new PdfPCell(new Phrase((vehiculo instanceof Autobus autobus)? String.format("%s", autobus.getPlazas()) : (vehiculo instanceof Furgoneta furgoneta)? String.format("%s", furgoneta.getPlazas()) : "", fontTitulos)));
        }
       
        return tableVehiculos;
	}
	
	PdfPTable crearTablaClientes(){		
        PdfPTable tableClientes = new PdfPTable(3);                
        
        PdfPCell celdaTituloClientes = new PdfPCell(new Paragraph("Clientes", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
        celdaTituloClientes.setColspan(3);
        celdaTituloClientes.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableClientes.addCell(celdaTituloClientes);
		
        tableClientes.setTotalWidth(200f);
        tableClientes.setLockedWidth(true);
        
		Font fontTitulos = new Font(Font.FontFamily.HELVETICA, 10);
        
        tableClientes.addCell(new PdfPCell(new Phrase("Nombre", fontTitulos)));
        tableClientes.addCell(new PdfPCell(new Phrase("DNI", fontTitulos)));
        tableClientes.addCell(new PdfPCell(new Phrase("Telefono", fontTitulos)));
        
        Font fontTextoCelda = new Font(Font.FontFamily.HELVETICA, 7);
        List<Cliente> listaClientes = VistaGrafica.getInstancia().getControlador().getClientes();
        for (Cliente cliente : listaClientes) {
        	tableClientes.addCell(new PdfPCell(new Phrase(cliente.getNombre(), fontTextoCelda)));
            tableClientes.addCell(new PdfPCell(new Phrase(cliente.getDni(), fontTextoCelda)));
            tableClientes.addCell(new PdfPCell(new Phrase(cliente.getTelefono(), fontTextoCelda)));
        }
       
        return tableClientes;
	}
	
	// ---------------------------------------------------------------------------------------------------------------------------------------------------------------

	@FXML
	void ratonPresionaBotonVista(MouseEvent event) {
		VBox vbox = crearMenuVbox();
		
		if (!stackPaneMenuDesplegable.getChildren().isEmpty()) {			
			stackPaneMenuDesplegable.getChildren().clear();
		} else {

			Button botonBlanco = crearBotonesMenu();
			Button botonNegro = crearBotonesMenu();
			Button botonColor = crearBotonesMenu();
			Button botonMinimizar = crearBotonesMenu();
			Button botonMaximizar = crearBotonesMenu();

			botonBlanco.setText("Blanco");
			botonBlanco.setOnAction(e -> ratonPulsaCambiarColorBlanco());

			botonNegro.setText("Negro");
			botonNegro.setOnAction(e -> ratonPulsaCambiarColorNegro());

			botonColor.setText("Color");
			botonColor.setOnAction(e -> ratonPulsaCambiarColor());

			botonMinimizar.setText("Minimizar");
			botonMinimizar.setOnAction(e -> ratonPulsaMinimizar());

			botonMaximizar.setText("Maximizar");
			botonMaximizar.setOnAction(e -> ratonPulsaMaximizar());
			
			VBox.setMargin(botonBlanco, new Insets(6));
			VBox.setMargin(botonNegro, new Insets(6));
			VBox.setMargin(botonColor, new Insets(6));
			VBox.setMargin(botonMinimizar, new Insets(6));
			VBox.setMargin(botonMaximizar, new Insets(6));
			vbox.getChildren().addAll(botonBlanco, botonNegro, botonColor, botonMinimizar, botonMaximizar);
			
			stackPaneMenuDesplegable.getChildren().add(0, vbox);
			stackPaneMenuDesplegable.setAlignment(Pos.CENTER_LEFT);

			desplegarMenu();
		}
	}

	void ratonPulsaMaximizar() {
		//getEscenario().getScene().getRoot().getStylesheets("");	
		Dialogos.mostrarDialogoInformacion("Boton Maximizar", "AVISO: Este boton no sirve para nada, ya que su funcionalidad se implementará en el siguiente spring", getEscenario());
	}

	void ratonPulsaMinimizar() {
		getEscenario().setIconified(true);
		stackPaneMenuDesplegable.getChildren().clear();
	}

	void ratonPulsaCambiarColor() {
		getEscenario().getScene().getRoot().getStylesheets().clear();	
		getEscenario().getScene().getRoot().getStylesheets().add(LocalizadorRecursos.class.getResource("css/VentanaPrincipal.css").toExternalForm());
		getEscenario().getScene().getRoot().requestLayout();
		
	}

	void ratonPulsaCambiarColorNegro() {
		if (!getEscenario().getScene().getRoot().getStylesheets().contains("css/VentanaPrincipal-Negro.css")) {
			if (getEscenario().getScene().getRoot().getStylesheets().size() >= 2) {
				getEscenario().getScene().getRoot().getStylesheets().remove(1);
			}
			getEscenario().getScene().getRoot().getStylesheets().add(LocalizadorRecursos.class.getResource("css/VentanaPrincipal-Negro.css").toExternalForm());	
			getEscenario().getScene().getRoot().requestLayout();
		}
	}

	void ratonPulsaCambiarColorBlanco() {
		if (!getEscenario().getScene().getRoot().getStylesheets().contains("css/VentanaPrincipal-Blanco.css")) {
			if (getEscenario().getScene().getRoot().getStylesheets().size() >= 2) {
				getEscenario().getScene().getRoot().getStylesheets().remove(1);
			}
			getEscenario().getScene().getRoot().getStylesheets().add(LocalizadorRecursos.class.getResource("css/VentanaPrincipal-Blanco.css").toExternalForm());
			getEscenario().getScene().getRoot().requestLayout();
		}
	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	@FXML
	void ratonPresionaBotonEstadisticas(MouseEvent event) {
		VBox vbox = crearMenuVbox();
		
		if (!stackPaneMenuDesplegable.getChildren().isEmpty()) {
			stackPaneMenuDesplegable.getChildren().clear();
		} else {

			Button botonEstadisticas = crearBotonesMenu();

			botonEstadisticas.setText("Estadisticas");
			botonEstadisticas.setOnAction(e -> ratonPulsaMostrarEstadisticas());

			vbox.getChildren().addAll(botonEstadisticas);
			VBox.setMargin(botonEstadisticas, new Insets(6));
			
			stackPaneMenuDesplegable.getChildren().add(0, vbox);
			stackPaneMenuDesplegable.setAlignment(Pos.CENTER_LEFT);
			
			desplegarMenu();
		}
	}

	void ratonPulsaMostrarEstadisticas() {
		MostrarEstadisticasMensuales ventanaMostrarEstadisticasMensuales = (MostrarEstadisticasMensuales) Controladores.get("vistas/mostrarEstadisticasMensuales.fxml", "Vista grafica de las estadisticas mensuales", getEscenario());
		stackPaneMenuDesplegable.getChildren().clear();
		ventanaMostrarEstadisticasMensuales.getEscenario().showAndWait();
	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------------------------

	@FXML
	void ratonPresionaBotonInformacion(MouseEvent event) {
		VBox vbox = crearMenuVbox();
		
		if (!stackPaneMenuDesplegable.getChildren().isEmpty()) {
			stackPaneMenuDesplegable.getChildren().clear();
		} else {

			Button botonAutor = crearBotonesMenu();

			botonAutor.setText("Autor");
			botonAutor.setOnAction(e ->ratonPulsaBotonAutor());
			
			vbox.getChildren().addAll(botonAutor);
			VBox.setMargin(botonAutor, new Insets(6));
			
			stackPaneMenuDesplegable.getChildren().add(0, vbox);
			stackPaneMenuDesplegable.setAlignment(Pos.CENTER_LEFT);
			
			desplegarMenu();
		}
	}

	void ratonPulsaBotonAutor() {
		Autor ventanaAutor = (Autor) Controladores.get("vistas/Autor.fxml", "Vista grafica del autor", getEscenario());
		stackPaneMenuDesplegable.getChildren().clear();
		ventanaAutor.getEscenario().showAndWait();
	}

	// --------------------------------------------------------------------------------------------------------------------------------

	@FXML
	void ratonPresionaHyperlinkGithub(ActionEvent event) {
		abrirNavegadorLink("https://github.com/Serferman/AlquilerVehiculos-v3");
	}
	
	void abrirNavegadorLink(String link ) {
		
		String url = link;
		String os = System.getProperty("os.name").toLowerCase();

		String command = "";

		if (os.contains("win")) {
			command = "cmd /c start";
		} else if (os.contains("mac")) {
			command = "open";
		} else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
			command = "xdg-open";
		}

		try {
			ProcessBuilder pb = new ProcessBuilder(command, url);
			pb.start();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}

// ---------------------------------------------------------------------------------------------------------------------------------------------------------------