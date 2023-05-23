package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

public class VistaTexto extends Vista {

	public VistaTexto() {
		super();
		Accion.setVista(this);
	}

	public void comenzar() {
		Accion accion = null;
		do {
			Consola.mostrarMenuAcciones();
			accion = Consola.elegirAccion();
			accion.ejecutar(); 
		} while (accion != Accion.SALIR);
	}

	public void terminar() {
		getControlador().terminar();
		System.out.printf("%nAVISO: La vista ha terminado%n");
	}

	public void insertarCliente() {
		Consola.mostrarCabecera("INSERTAR CLIENTE");
		try {
			getControlador().insertar(Consola.leerCliente());
			System.out.printf("%nAVISO: Se ha insertado el cliente correctamente%n");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.printf("%s", e.getMessage());
		}

	}

	public void insertarVehiculo() {
		Consola.mostrarCabecera("INSERTAR TURISMO");
		try {
			getControlador().insertar(Consola.leerVehiculo());
			System.out.printf("%nAVISO: Se ha insertado el vehiculo correctamente%n");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.printf("%s", e.getMessage());
		}
	}

	public void insertarAlquiler() {
		Consola.mostrarCabecera("INSERTAR ALQUILER");
		try {
			getControlador().insertar(Consola.leerAlquiler());
			System.out.printf("%nAVISO: Se ha insertado el alquiler correctamente%n");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.printf("%s", e.getMessage());
		}
	}

	public void buscarCliente() {
		Consola.mostrarCabecera("BUSCAR CLIENTE");
		try {
			System.out.printf("%n%s%n", getControlador().buscar(Consola.leerClienteDni()));
			System.out.printf("%nAVISO: Se ha buscado el cliente correctamente%n");
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.printf("%s", e.getMessage());
		}
	}

	public void buscarVehiculo() {
		Consola.mostrarCabecera("BUSCAR TURISMO");
		try {
			System.out.printf("%n%s%n", getControlador().buscar(Consola.leerVehiculoMatricula()));
			System.out.printf("%nAVISO: Se ha buscado el vehiculo correctamente%n");
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.printf("%s", e.getMessage());
		}
	}

	public void buscarAlquiler() {
		Consola.mostrarCabecera("BUSCAR ALQUILER");
		try {
			System.out.printf("%n%s%n", getControlador().buscar(Consola.leerAlquiler()));
			System.out.printf("%nAVISO: Se ha buscado el alquiler correctamente%n");
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.printf("%s", e.getMessage());
		}
	}

	public void modificarCliente() {
		Consola.mostrarCabecera("MODIFICAR CLIENTE");
		try {
			getControlador().modificar(Consola.leerCliente(), Consola.leerNombre(), Consola.leerTelefono());
			System.out.printf("%nAVISO: Se ha modificado el cliente correctamente%n");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.printf("%s", e.getMessage());
		}
	}

	public void devolverAlquilerCliente() {
		Consola.mostrarCabecera("DEVOLVER ALQUILER");
		try {
			getControlador().devolver(Consola.leerClienteDni(), Consola.leerFechaDevolucion());
			System.out.printf("%nAVISO: Se ha devuelto el vehiculo correctamente%n");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.printf("%s", e.getMessage());
		}
	}

	public void devolverAlquilerVehiculo() {
		Consola.mostrarCabecera("DEVOLVER ALQUILER");
		try {
			getControlador().devolver(Consola.leerVehiculoMatricula(), Consola.leerFechaDevolucion());
			System.out.printf("%nAVISO: Se ha devuelto el vehiculo correctamente%n");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.printf("%s", e.getMessage());
		}
	}

	public void borrarCliente() {
		Consola.mostrarCabecera("BORRAR CLIENTE");
		try {
			getControlador().borrar(Consola.leerClienteDni());
			System.out.printf("%nAVISO: Se ha borrado el cliente correctamente%n");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.printf("%s", e.getMessage());
		}
	}

	public void borrarVehiculo() {
		Consola.mostrarCabecera("BORRAR TURISMO");
		try {
			getControlador().borrar(Consola.leerVehiculoMatricula());
			System.out.printf("%nAVISO: Se ha borrado el vehiculo correctamente%n");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.printf("%s", e.getMessage());
		}
	}

	public void borrarAlquiler() {
		Consola.mostrarCabecera("BORRAR ALQUILER");
		try {
			getControlador().borrar(Consola.leerAlquiler());
			System.out.printf("%nAVISO: Se ha borrado el alquiler correctamente%n");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.printf("%s", e.getMessage());
		}
	}

	public void listarClientes() {
		Consola.mostrarCabecera("LISTAR CLIENTES");
		try {
			List<Cliente> lista = getControlador().getClientes();
			lista.sort(Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni));
			for (Cliente cliente : lista) {
				System.out.printf("%s%n", cliente);
			}
			System.out.printf("%nAVISO: Se han listado los clientes correctamente%n");
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.printf("%s", e.getMessage());
		}
	}

	public void listarVehiculos() {
		Consola.mostrarCabecera("LISTAR TURISMOS");
		try {
			List<Vehiculo> lista = getControlador().getVehiculos();
			lista.sort(Comparator.comparing(Vehiculo::getMarca).thenComparing(Vehiculo::getModelo)
					.thenComparing(Vehiculo::getMatricula));
			for (Vehiculo turismo : lista) {
				System.out.printf("%s%n", turismo);
			}
			System.out.printf("%nAVISO: Se han listado los vehiculos correctamente%n");
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.printf("%s", e.getMessage());
		}
	}

	public void listarAlquileres() {
		Consola.mostrarCabecera("LISTAR ALQUILERES");
		try {
			List<Alquiler> listaAlquiler = getControlador().getAlquileres();
			Comparator<Cliente> comparador = Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni);
			listaAlquiler.sort(
					Comparator.comparing(Alquiler::getFechaAlquiler).thenComparing(Alquiler::getCliente, comparador));
			for (Alquiler alquiler : listaAlquiler) {
				System.out.printf("%s%n", alquiler);
			}
			System.out.printf("%nAVISO: Se han listado los alquileres correctamente%n");
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.printf("%s", e.getMessage());
		}
	}

	public void listarAlquileresCliente() {
		Consola.mostrarCabecera("LISTAR ALQUILERES CLIENTE");
		try {
			List<Alquiler> listaAlquileresCliente = getControlador().getAlquileres(Consola.leerClienteDni());
			listaAlquileresCliente.sort(Comparator.comparing(Alquiler::getFechaAlquiler));
			for (Alquiler alquilerCliente : listaAlquileresCliente) {
				System.out.printf("%s%n", alquilerCliente);
			}
			System.out.printf("%nAVISO: Se han listado los alquileres del cliente correctamente%n");
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.printf("%s", e.getMessage());
		}
	}

	public void listarAlquileresVehiculo() {
		Consola.mostrarCabecera("LISTAR ALQUILERES TURISMO");
		try {
			List<Alquiler> listaAlquileresVehiculo = getControlador().getAlquileres(Consola.leerVehiculoMatricula());
			Comparator<Cliente> comparador = Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni);
			listaAlquileresVehiculo.sort(
					Comparator.comparing(Alquiler::getFechaAlquiler).thenComparing(Alquiler::getCliente, comparador));
			for (Alquiler alquilerTurismo : listaAlquileresVehiculo) {
				System.out.printf("%s%n", alquilerTurismo);
			}
			System.out.printf("%nAVISO: Se han listado los alquileres del turismo correctamente%n");
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.printf("%s", e.getMessage());
		}
	}

	public void mostrarEstadisticasMensualesTipoVehiculo() {
		Map<TipoVehiculo, Integer> estadisticasInicializadas = inicializarEstadisticas();
		LocalDate mes = Consola.leerMes();

		/* Nota Importante
		 * 
		 * getYear() saca el año de una fecha y lo convierte en un tipo de dato INT.
		 * getMonth() saca el mes de una fecha y lo busca dentro de un enumerado, por lo
		 * que te devuelve el literal del mismo. getMonthValue() saca el mes de una
		 * fecha y lo convierte en un tipo de dato INT.
		 */

		for (Alquiler alquiler : getControlador().getAlquileres()) {
			if (alquiler.getFechaAlquiler().getYear() == mes.getYear()
					&& alquiler.getFechaAlquiler().getMonth().equals(mes.getMonth())) {
				TipoVehiculo tipoVehiculo = TipoVehiculo.get(alquiler.getVehiculo());
				estadisticasInicializadas.put(tipoVehiculo,
						estadisticasInicializadas.containsKey(tipoVehiculo)
								? estadisticasInicializadas.get(tipoVehiculo) + 1
								: 1);
			}
		}

		System.out.println(estadisticasInicializadas);
	}

	private Map<TipoVehiculo, Integer> inicializarEstadisticas() {
		Map<TipoVehiculo, Integer> estadisticas = new EnumMap<>(TipoVehiculo.class);

		for (TipoVehiculo tipoVehiculo : TipoVehiculo.values()) {
			estadisticas.put(tipoVehiculo, 0);
		}

		return estadisticas;
	}

}