package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;

public class Cliente{

	private static final String ER_NOMBRE = "([A-Z][a-z]+)\\s?([A-Z]{1}[a-z]+\\s*)*";
	private static final String ER_DNI = "\\d{8}[A-Z]";
	private static final String ER_TELEFONO = "\\d{9}";

	private String nombre;
	private String dni;
	private String telefono;

	public Cliente(String nombre, String dni, String telefono) {
		setNombre(nombre);
		setDni(dni);
		setTelefono(telefono);
	}

	public Cliente(Cliente cliente) {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No es posible copiar un cliente nulo.");
		}

		this.nombre = cliente.getNombre();
		this.dni = cliente.getDni();
		this.telefono = cliente.getTelefono();
	}
	
	public static Cliente getClienteConDni(String dni) {
		return new Cliente("Sergio", dni, "688913774");
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) { // Nombre es un atrubuto mutable, por que su metodo set es publico
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
		}

		if (!nombre.matches(ER_NOMBRE)) {
			throw new IllegalArgumentException("ERROR: El nombre no tiene un formato válido.");
		}

		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	private void setDni(String dni) {
		if (dni == null) {
			throw new NullPointerException("ERROR: El DNI no puede ser nulo.");
		}

		if ((!dni.matches(ER_DNI))) {
			throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
		}

		if (!comprobarLetraDni(dni)) {
			throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
		}
		this.dni = dni;
	}

	private boolean comprobarLetraDni(String dni) {
		char[] arrayLetraDni = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q',
				'V', 'H', 'L', 'C', 'K', 'E' };

		int numeroDni = Integer.parseInt(dni.substring(0, 8));
		int resultado = numeroDni % 23;

		return arrayLetraDni[resultado] == dni.charAt(8);
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) { // Telefono es un atrubuto mutable, porque su metodo set es publico
		if (telefono == null) {
			throw new NullPointerException("ERROR: El teléfono no puede ser nulo.");
		}

		if (!telefono.matches(ER_TELEFONO)) {
			throw new IllegalArgumentException("ERROR: El teléfono no tiene un formato válido.");
		}
		this.telefono = telefono;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(dni, other.dni);
	}

	@Override
	public String toString() {
		return String.format("%s - %s (%s)", nombre, dni, telefono);
	}

}
