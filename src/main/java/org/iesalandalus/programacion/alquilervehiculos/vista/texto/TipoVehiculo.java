package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public enum TipoVehiculo {
	TURISMO("Turismo"), AUTOBUS("Autobus"), FURGONETA("Furgoneta");

	private String nombre;

	private TipoVehiculo(String nombre) {
		this.nombre = nombre;
	}

	private static boolean esOrdinalValido(int ordinal) {
		return ((ordinal >= 0) && (TipoVehiculo.values().length) > ordinal);
	}

	public static TipoVehiculo get(int ordinal) {
		if (!esOrdinalValido(ordinal)) {
			throw new IllegalArgumentException("ERROR: La acción elegida no es válida.");
		}
		return TipoVehiculo.values()[ordinal];
	}

	public static TipoVehiculo get(Vehiculo vehiculo) {
		TipoVehiculo tipoVehiculoDevolver = null;

		if (vehiculo instanceof Turismo) {
			tipoVehiculoDevolver = TipoVehiculo.TURISMO;
		} else if (vehiculo instanceof Autobus) {
			tipoVehiculoDevolver = TipoVehiculo.AUTOBUS;
		} else if (vehiculo instanceof Furgoneta) {
			tipoVehiculoDevolver = TipoVehiculo.FURGONETA;
		}

		return tipoVehiculoDevolver;

	}

	@Override
	public String toString() {
		return String.format("%s", nombre);
	}
}
