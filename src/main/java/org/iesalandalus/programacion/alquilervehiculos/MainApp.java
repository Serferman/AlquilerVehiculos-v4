package org.iesalandalus.programacion.alquilervehiculos;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.Modelo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.ModeloCascada;
import org.iesalandalus.programacion.alquilervehiculos.vista.FactoriaVista;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

public class MainApp {

	public static void main(String[] args) {
		Controlador controlador = new Controlador(procesarArgumentosDatos(args), procesarArgumentosVista(args));
		controlador.comenzar();
	}

	private static Vista procesarArgumentosVista(String[] arg) {
		Vista vista = FactoriaVista.GRAFICA.crear();

		for (String argObtenido : arg) {
			if (argObtenido.equalsIgnoreCase("-vtexto")) {
				vista = FactoriaVista.TEXTO.crear();
			} else if (argObtenido.equalsIgnoreCase("-vgrafica")) {
				vista = FactoriaVista.GRAFICA.crear();
			}
		}
		return vista;
	}

	private static Modelo procesarArgumentosDatos(String[] arg) {
		Modelo modelo = new ModeloCascada(FactoriaFuenteDatos.MONGODB);

		for (String argObtenido : arg) {
			if (argObtenido.equalsIgnoreCase("-fdficheros")) {
				modelo = new ModeloCascada(FactoriaFuenteDatos.FICHEROS);
			} else if (argObtenido.equalsIgnoreCase("-fdmariadb")) {
				modelo = new ModeloCascada(FactoriaFuenteDatos.MARIADB);
			}  else if (argObtenido.equalsIgnoreCase("-fdmongodb")) {
				modelo = new ModeloCascada(FactoriaFuenteDatos.MONGODB);
			}
		}
		return modelo;
	}
}
