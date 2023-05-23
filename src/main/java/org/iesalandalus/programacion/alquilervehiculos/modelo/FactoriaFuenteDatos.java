package org.iesalandalus.programacion.alquilervehiculos.modelo;

import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.FuenteDatosFicheros;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.mariadb.FuenteDatosMariaDB;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.mongodb.FuenteDatosMongoDB;

public enum FactoriaFuenteDatos {

	FICHEROS() {
		@Override
		IFuenteDatos crear() {
			return new FuenteDatosFicheros();
		}
	},
	MARIADB() {
		@Override
		IFuenteDatos crear() {
			return new FuenteDatosMariaDB();
		}
	},
	MONGODB() {
		@Override
		IFuenteDatos crear() {
			return new FuenteDatosMongoDB();
		}
	};

	abstract IFuenteDatos crear();

}
