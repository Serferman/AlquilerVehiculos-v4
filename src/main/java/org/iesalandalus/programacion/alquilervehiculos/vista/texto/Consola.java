package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	private static final String PATRON_FECHA = "dd/MM/yyyy";
	private static final String PATRON_MES = "MM/yyyy";
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern(PATRON_FECHA);

	private Consola() {
	}

	public static void mostrarCabecera(String mensaje) {
		StringBuilder rayas = new StringBuilder();
		for (int i = 0; i < mensaje.length(); i++) {
			rayas.append("-");
		}
		System.out.printf("%n%s%n%s%n", mensaje, rayas);
	}

	public static void mostrarMenuAcciones() {
		mostrarCabecera("         MENÚ         ");
		for (Accion opcion : Accion.values()) {
			System.out.println(opcion);
		}
		System.out.println("");
	}

	public static Accion elegirAccion() {
		int indiceOpcion = 0;
		Accion accion = null;
		do {
			try {
				indiceOpcion = leerEntero("Introduce una opción: ");
				accion = Accion.get(indiceOpcion);
			} catch (IllegalArgumentException e) {
				System.out.printf("%s", e.getMessage());
			}
		} while (accion == null);
		return accion;
	}

	private static String leerCadena(String mensaje) {
		System.out.print(mensaje);
		return Entrada.cadena();
	}

	private static Integer leerEntero(String mensaje) {
		System.out.print(mensaje);
		return Entrada.entero();
	}

	private static LocalDate leerFecha(String mensaje, String patron) { // AUN POR REVISAR
		LocalDate fecha = null;
		try {
			if (PATRON_MES.equals(patron)) {
				System.out.printf("%nAVISO: El patrón de la fecha que debe usar es: %s%n", PATRON_MES);
				System.out.print(mensaje);
				fecha = LocalDate.parse("01/" + Entrada.cadena(), FORMATO_FECHA);
			} else {
				System.out.printf("%nAVISO: El patrón de la fecha que debe usar es: %s%n", PATRON_FECHA);
				System.out.print(mensaje);
				fecha = LocalDate.parse(Entrada.cadena(), FORMATO_FECHA);
			}
		} catch (DateTimeParseException e) {
			System.out.printf("%s", e.getMessage());
		}
		return fecha;
	}

	public static Cliente leerCliente() {
		return new Cliente(leerNombre(), leerCadena("Introduceme un DNI válido: "), leerTelefono());
	}

	public static Cliente leerClienteDni() {
		return Cliente.getClienteConDni(leerCadena("Introduceme un DNI válido: "));
	}

	public static String leerNombre() {
		return leerCadena("Introduce el nombre del cliente: ");
	}

	public static String leerTelefono() {
		return leerCadena("Introduce un teléfono del cliente: ");
	}

	public static Vehiculo leerVehiculo() {
		mostrarMenuTiposVehiculos();
		return leerVehiculo(elegirTipoVehiculo());
	}

	private static void mostrarMenuTiposVehiculos() {
		mostrarCabecera("MENÚ - VEHICULOS");
		for (TipoVehiculo tipovehiculo : TipoVehiculo.values()) {
			System.out.printf("%d.- %s%n", tipovehiculo.ordinal(), tipovehiculo);
		}
		System.out.println("");
	}

	private static TipoVehiculo elegirTipoVehiculo() {
		int indiceOpcion = 0;
		TipoVehiculo tipoVehiculo = null;
		do {
			try {
				indiceOpcion = leerEntero("Introduce una opción: ");
				tipoVehiculo = TipoVehiculo.get(indiceOpcion);
			} catch (IllegalArgumentException e) {
				System.out.printf("%s", e.getMessage());
			}
		} while (tipoVehiculo == null);
		return tipoVehiculo;
	}

	private static Vehiculo leerVehiculo(TipoVehiculo tipoVehiculo) {
		Vehiculo vehiculo = null;
		String cadenaMarca = leerCadena("Introduce la marca: ");
		String cadenaModelo = leerCadena("Introduce el modelo: ");
		String cadenaMatricula = leerCadena("Introduce la matrícula: ");

		switch (tipoVehiculo) {
		case TURISMO:
			vehiculo = new Turismo(cadenaMarca, cadenaModelo, leerEntero("Introduce las cilindradas: "),
					cadenaMatricula);
			break;
		case AUTOBUS:
			vehiculo = new Autobus(cadenaMarca, cadenaModelo, leerEntero("Introduce las plazas: "), cadenaMatricula);
			break;
		case FURGONETA:
			vehiculo = new Furgoneta(cadenaMarca, cadenaModelo, leerEntero("Introduce el PMA: "),
					leerEntero("Introduce las plazas: "), cadenaMatricula);
			break;
		}

		return vehiculo;
	}

	public static Vehiculo leerVehiculoMatricula() {
		return Vehiculo.getVehiculoConMatricula(leerCadena("Introduceme una matrícula válida: "));
	}

	public static Alquiler leerAlquiler() {
		return new Alquiler(leerClienteDni(), leerVehiculoMatricula(),
				leerFecha("Introduce una fecha de alquiler: ", PATRON_FECHA));
	}

	public static LocalDate leerFechaDevolucion() {
		return leerFecha("Introduce una fecha de devolución: ", PATRON_FECHA);
	}

	public static LocalDate leerMes() { // AUN POR REVISAR
		return leerFecha("Introduceme un mes y año de alquiler: ", PATRON_MES);
	}

}
