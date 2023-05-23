package org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public class ModeloCascada extends Modelo {
	
	public ModeloCascada(FactoriaFuenteDatos factoriaFuenteDatos) {
		super(factoriaFuenteDatos);
	}

	@Override
	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		getClientes().insertar(new Cliente(cliente));
	}

	@Override
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
		getVehiculos().insertar(Vehiculo.copiar(vehiculo));
	}

	@Override
	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede realizar un alquiler nulo.");
		}

		Cliente cliente = buscar(alquiler.getCliente());
		Vehiculo vehiculo = buscar(alquiler.getVehiculo());

		if (cliente == null) {
			throw new OperationNotSupportedException("ERROR: No existe el cliente del alquiler.");
		}

		if (vehiculo == null) {
			throw new OperationNotSupportedException("ERROR: No existe el turismo del alquiler.");
		}

		getAlquileres()
				.insertar(new Alquiler(new Cliente(cliente), Vehiculo.copiar(vehiculo), alquiler.getFechaAlquiler()));
	}

	@Override
	public Cliente buscar(Cliente cliente) {
		Cliente clienteBuscado = getClientes().buscar(cliente);
		return (clienteBuscado == null) ? null : new Cliente(clienteBuscado);
	}

	@Override
	public Vehiculo buscar(Vehiculo vehiculo) {
		Vehiculo vehiculoBuscado = getVehiculos().buscar(vehiculo);
		return (vehiculoBuscado == null) ? null : Vehiculo.copiar(vehiculoBuscado);
	}

	@Override
	public Alquiler buscar(Alquiler alquiler) {
		return new Alquiler(getAlquileres().buscar(alquiler));
	}

	@Override
	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		getClientes().modificar(cliente, nombre, telefono);
	}

	@Override
	public void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		// cliente = getClientes().buscar(cliente);

		if (cliente == null) {
			throw new OperationNotSupportedException("ERROR: No existe el cliente que debe devolver.");
		}

		getAlquileres().devolver(cliente, fechaDevolucion);
	}

	@Override
	public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		// vehiculo = getVehiculos().buscar(vehiculo);

		if (vehiculo == null) {
			throw new OperationNotSupportedException("ERROR: No existe el vehiculo a devolver.");
		}

		getAlquileres().devolver(vehiculo, fechaDevolucion);
	}

	@Override
	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		for (Alquiler alquilerAux : getAlquileres().get(cliente)) {
			borrar(alquilerAux);
		}
		getClientes().borrar(cliente);
	}

	@Override
	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
		for (Alquiler alquilerAux : getAlquileres().get(vehiculo)) {
			borrar(alquilerAux);
		}
		getVehiculos().borrar(vehiculo);
	}

	@Override
	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		getAlquileres().borrar(alquiler);
	}

	@Override
	public List<Alquiler> getListaAlquileres(Vehiculo vehiculo) {
		ArrayList<Alquiler> alquilerArray = new ArrayList<>();
		for (Alquiler alquilerAux : getAlquileres().get(vehiculo)) {
			alquilerArray.add(new Alquiler(alquilerAux));
		}
		return alquilerArray;
	}

	@Override
	public List<Alquiler> getListaAlquileres(Cliente cliente) {
		ArrayList<Alquiler> alquilerArray = new ArrayList<>();
		for (Alquiler alquilerAux : getAlquileres().get(cliente)) {
			alquilerArray.add(new Alquiler(alquilerAux));
		}
		return alquilerArray;
	}

	@Override
	public List<Alquiler> getListaAlquileres() {
		List<Alquiler> listaAlquiler = new ArrayList<>();
		for (Alquiler alquiler : getAlquileres().get()) {
			listaAlquiler.add(new Alquiler(alquiler));
		}
		return listaAlquiler;
	}

	@Override
	public List<Vehiculo> getListaVehiculos() {
		List<Vehiculo> listaVehiculo = new ArrayList<>();
		for (Vehiculo vehiculo : getVehiculos().get()) {
			listaVehiculo.add(Vehiculo.copiar(vehiculo));
		}
		return listaVehiculo;
	}

	@Override
	public List<Cliente> getListaClientes() {
		List<Cliente> listaCliente = new ArrayList<>();
		for (Cliente cliente : getClientes().get()) {
			listaCliente.add(new Cliente(cliente));
		}
		return listaCliente;
	}

}
