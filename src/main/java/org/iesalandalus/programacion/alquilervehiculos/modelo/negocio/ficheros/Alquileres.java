package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;
import javax.xml.parsers.DocumentBuilder;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Alquileres implements IAlquileres {

	private static final File FICHERO_ALQUILERES = new File(
			String.format("%s%s%s", "datos", File.separator, "alquileres.xml"));
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final String RAIZ = "alquileres";
	private static final String ALQUILER = "alquiler";
	private static final String CLIENTE = "cliente";
	private static final String VEHICULO = "vehiculo";
	private static final String FECHA_ALQUILER = "fechaAlquiler";
	private static final String FECHA_DEVOLUCION = "fechaDevolucion";

	private static Alquileres instancia;

	private List<Alquiler> coleccionAlquileres;

	public Alquileres() {
		coleccionAlquileres = new ArrayList<>();
	}

	static Alquileres getInstancia() {
		if (instancia == null) {
			instancia = new Alquileres();
		}

		return instancia;
	}

	@Override
	public void comenzar() {
		Document documentoXML = UtilidadesXml.leerXmlDeFichero(FICHERO_ALQUILERES);
		if (documentoXML != null) {
			leerDom(documentoXML);
			System.out.println("AVISO: El documento XML de Alquileres se ha leido correctamente.");
		} else {
			System.out.println("ERROR: El documento XML de Alquileres no se ha leido correctamente.");
		}
	}

	private void leerDom(Document documentoXml) {
		NodeList alquileres = documentoXml.getElementsByTagName(ALQUILER);
		for (int i = 0; i < alquileres.getLength(); i++) {
			Node alquiler = alquileres.item(i);
			if (alquiler.getNodeType() == Node.ELEMENT_NODE) {
				try {
					insertar(getAlquiler((Element) alquiler));
				} catch (DateTimeParseException | OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
					System.out.printf("%nERROR: No se ha podido procesar el alquiler: %d --> %s%n", i, e.getMessage());
				}
			}
		}
	}

	private Alquiler getAlquiler(Element elemento) throws OperationNotSupportedException {
		Cliente cliente = Cliente.getClienteConDni(elemento.getAttribute(CLIENTE));
		Vehiculo vehiculo = Vehiculo.getVehiculoConMatricula(elemento.getAttribute(VEHICULO));
		LocalDate fechaAlquiler = LocalDate.parse(elemento.getAttribute(FECHA_ALQUILER), FORMATO_FECHA);
		LocalDate fechaDevolucion = LocalDate.parse(elemento.getAttribute(FECHA_DEVOLUCION), FORMATO_FECHA);

		cliente = Clientes.getInstancia().buscar(cliente);
		vehiculo = Vehiculos.getInstancia().buscar(vehiculo);
		
		if ( cliente == null) {
			throw new NullPointerException("ERROR: El cliente no puede ser nulo");
		}

		
		if ( vehiculo == null) {
			throw new NullPointerException("ERROR: El vehiculo no puede ser nulo");
		}

		Alquiler alquiler = new Alquiler(cliente, vehiculo, fechaAlquiler);
		alquiler.devolver(fechaDevolucion);

		return alquiler;
	}

	@Override
	public void terminar() {
		UtilidadesXml.escribirXmlAFichero(crearDom(), FICHERO_ALQUILERES);
	}

	private Document crearDom() {
		DocumentBuilder constructor = UtilidadesXml.crearConstructorDocumentoXml();
		Document documentoXml = null;
		if (constructor != null) {
			documentoXml = constructor.newDocument();
			documentoXml.appendChild(documentoXml.createElement(RAIZ));
			for (Alquiler alquiler : coleccionAlquileres) {
				Element elementoAlquiler = getElemento(documentoXml, alquiler);
				documentoXml.getDocumentElement().appendChild(elementoAlquiler);
			}
		}
		return documentoXml;
	}

	private Element getElemento(Document documentoXml, Alquiler alquiler) {
		Element elementoAlquiler = documentoXml.createElement(ALQUILER);

		elementoAlquiler.setAttribute(CLIENTE, alquiler.getCliente().getDni());
		elementoAlquiler.setAttribute(VEHICULO, alquiler.getVehiculo().getMatricula());
		elementoAlquiler.setAttribute(FECHA_ALQUILER, alquiler.getFechaAlquiler().format(FORMATO_FECHA));

		if (alquiler.getFechaDevolucion() != null) {
			elementoAlquiler.setAttribute(FECHA_DEVOLUCION, alquiler.getFechaDevolucion().format(FORMATO_FECHA));
		}

		return elementoAlquiler;
	}

	public List<Alquiler> get() {
		return new ArrayList<>(coleccionAlquileres);
	}

	public List<Alquiler> get(Cliente cliente) {
		ArrayList<Alquiler> arrayAlquileresCliente = new ArrayList<>();

		for (Alquiler alquilerLista1 : coleccionAlquileres) {
			if (alquilerLista1.getCliente().equals(cliente)) {
				arrayAlquileresCliente.add(alquilerLista1);
			}
		}

		return arrayAlquileresCliente;
	}

	public List<Alquiler> get(Vehiculo vehiculo) {
		ArrayList<Alquiler> arrayAlquileresCliente = new ArrayList<>();

		for (Alquiler alquilerLista2 : coleccionAlquileres) {
			if (alquilerLista2.getVehiculo().equals(vehiculo)) { // MOdificado
				arrayAlquileresCliente.add(alquilerLista2);
			}
		}

		return arrayAlquileresCliente;
	}

	public int getCantidad() {
		return coleccionAlquileres.size();
	}

	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		}
		comprobarAlquiler(alquiler.getCliente(), alquiler.getVehiculo(), alquiler.getFechaAlquiler()); // Modificado
		coleccionAlquileres.add(alquiler);
	}

	private void comprobarAlquiler(Cliente cliente, Vehiculo vehiculo, LocalDate fechaAlquiler)
			throws OperationNotSupportedException {
		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getFechaDevolucion() == null) {
				// Sin devolver
				if (alquiler.getCliente().equals(cliente)) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");
				} else if (alquiler.getVehiculo().equals(vehiculo)) {
					throw new OperationNotSupportedException("ERROR: El vehículo está actualmente alquilado.");
					// MENSAJE DE ERROR INVALIDO, no es un turismo, es un vehiculo.
				}
			} else {
				// Devueltos
				if ((alquiler.getCliente().equals(cliente))
						&& (alquiler.getFechaDevolucion().compareTo(fechaAlquiler) >= 0)) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");
				} else if ((alquiler.getVehiculo().equals(vehiculo))
						&& (alquiler.getFechaDevolucion().compareTo(fechaAlquiler) >= 0)) {
					throw new OperationNotSupportedException("ERROR: El vehículo tiene un alquiler posterior.");
					// MENSAJE DE ERROR INVALIDO, no es un turismo, es un vehiculo.
				}
			}
		}
	}

	public void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler de un cliente nulo.");
		}

		Alquiler alquiler = getAlquilerAbierto(cliente);
		if (alquiler == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler abierto para ese cliente.");
		}
		alquiler.devolver(fechaDevolucion);
	}

	private Alquiler getAlquilerAbierto(Cliente cliente) {
		Alquiler alquilerEncontrado = null;
		Iterator<Alquiler> iterator = coleccionAlquileres.iterator();

		while (iterator.hasNext() && alquilerEncontrado == null) {
			Alquiler alquilerRecorrido = iterator.next(); //

			if (alquilerRecorrido.getCliente().equals(cliente) && alquilerRecorrido.getFechaDevolucion() == null) {
				alquilerEncontrado = alquilerRecorrido;
			}
		}

		return alquilerEncontrado;
	}

	public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler de un vehículo nulo.");
		}

		Alquiler alquiler = getAlquilerAbierto(vehiculo);
		if (alquiler == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler abierto para ese vehículo.");
		}
		alquiler.devolver(fechaDevolucion);
	}

	private Alquiler getAlquilerAbierto(Vehiculo vehiculo) {
		Alquiler alquilerEncontrado = null;
		Iterator<Alquiler> iterador = coleccionAlquileres.iterator();

		while (iterador.hasNext() && alquilerEncontrado == null) {
			Alquiler alquilerRecorrido = iterador.next();
			if (alquilerRecorrido.getVehiculo().equals(vehiculo)) {
				alquilerEncontrado = alquilerRecorrido;
			}
		}

		return alquilerEncontrado;
	}

	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");
		}

		if (buscar(alquiler) == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}
		coleccionAlquileres.remove(alquiler);
	}

	public Alquiler buscar(Alquiler alquiler) {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");
		}
		int indice = coleccionAlquileres.indexOf(alquiler);
		return (indice == -1) ? null : coleccionAlquileres.get(indice);
	}

}
