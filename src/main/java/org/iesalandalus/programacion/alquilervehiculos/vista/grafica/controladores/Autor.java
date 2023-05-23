package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.io.IOException;

import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class Autor extends Controlador {

	@FXML
	private HBox hboxWebView;

	// ------------------------------------------------------------------------------------------------------------------------

	void abrirNavegadorLink(String link) {

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

	// ------------------------------------------------------------------------------------------------------------------------

	@FXML
	void ratonPresionaHyperlinkGithub(ActionEvent event) {
		abrirNavegadorLink("https://github.com/Serferman");
	}

	@FXML
	void ratonPresionaHyperlinkInstagram(ActionEvent event) {
		abrirNavegadorLink("https://www.instagram.com/serferman1/");
	}

	@FXML
	void ratonPresionaHyperlinkLinkedin(ActionEvent event) {
		abrirNavegadorLink("https://www.linkedin.com/in/sergiofern%C3%A1ndezpardo-serferman/");
	}

	@FXML
	void ratonPresionaHyperlinkThingiverse(ActionEvent event) {
		abrirNavegadorLink("https://www.thingiverse.com/serferman12");
	}

	// ------------------------------------------------------------------------------------------------------------------------

	@FXML
	void ratonPresionaHyperlinkGithubSADK3s(ActionEvent event) {
		abrirNavegadorLink("https://github.com/Serferman/Sistema-de-alta-disponibilidad-con-k3s");
	}

	@FXML
	void ratonPresionaHyperlinkGithubScriptCreator(ActionEvent event) {
		abrirNavegadorLink("https://github.com/Serferman/ScriptCreator\"");
	}
}
