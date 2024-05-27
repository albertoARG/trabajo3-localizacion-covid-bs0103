package com.practica.genericas;


import com.practica.excecption.EmsInvalidNumberOfDataException;

public class Persona {
	private static int MAX_DATOS_PERSONA = 8;
	private String nombre, apellidos, documento, email, direccion, cp;
	FechaHora fechaNacimiento;

	public static Persona parsePersona(String[] data) throws EmsInvalidNumberOfDataException {
		if (data.length != MAX_DATOS_PERSONA) {
			throw new EmsInvalidNumberOfDataException("Invalid number of fields for PERSONA");
		}

		String nombre = data[2];
		String apellidos = data[3];
		String documento = data[1];
		String email = data[4];
		String direccion = data[5];
		String cp = data[6];
		FechaHora fechaNacimiento = FechaHora.parseFecha(data[7]);

		return new Persona(nombre, apellidos, documento, email, direccion, cp, fechaNacimiento);
	}

	public Persona(String documento) {
		this.documento = documento;
	}

	public Persona(Persona p) {
		this.nombre = p.nombre;
		this.apellidos = p.apellidos;
		this.documento = p.documento;
		this.email = p.email;
		this.direccion = p.direccion;
		this.cp = p.cp;
		this.fechaNacimiento = p.fechaNacimiento;
	}

	public Persona(String nombre, String apellidos, String documento, String email, String direccion, String cp,
				   FechaHora fechaNacimiento) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.documento = documento;
		this.email = email;
		this.direccion = direccion;
		this.cp = cp;
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public FechaHora getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(FechaHora fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public String toString() {
		FechaHora fecha = getFechaNacimiento();
		String cadena = "";
		// Documento
		cadena += String.format("%s;", getDocumento());
		// Nombre y apellidos
		cadena += String.format("%s,%s;", getApellidos(), getNombre());
		// correo electrónico
		cadena += String.format("%s;", getEmail());
        // Direccion y código postal
		cadena += String.format("%s,%s;", getDireccion(), getCp());
        // Fecha de nacimiento
		cadena+=String.format("%02d/%02d/%04d\n", fecha.getFecha().getDia(), 
        		fecha.getFecha().getMes(), 
        		fecha.getFecha().getAnio());

		return cadena;
	}
}
