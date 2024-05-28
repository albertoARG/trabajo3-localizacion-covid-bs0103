package com.practica.ems.covid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import com.practica.excecption.EmsDuplicateLocationException;
import com.practica.excecption.EmsDuplicatePersonException;
import com.practica.excecption.EmsInvalidNumberOfDataException;
import com.practica.excecption.EmsInvalidTypeException;
import com.practica.excecption.EmsLocalizationNotFoundException;
import com.practica.excecption.EmsPersonNotFoundException;
import com.practica.genericas.FechaHora;
import com.practica.genericas.Persona;
import com.practica.genericas.PosicionPersona;
import com.practica.lista.ListaContactos;

public class ContactosCovid {
	private Poblacion poblacion;
	private Localizacion localizacion;
	private ListaContactos listaContactos;

	public ContactosCovid() {
		this.poblacion = new Poblacion();
		this.localizacion = new Localizacion();
		this.listaContactos = new ListaContactos();
	}

	public Poblacion getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(Poblacion poblacion) {
		this.poblacion = poblacion;
	}

	public Localizacion getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(Localizacion localizacion) {
		this.localizacion = localizacion;
	}

	public ListaContactos getListaContactos() {
		return listaContactos;
	}

	public void setListaContactos(ListaContactos listaContactos) {
		this.listaContactos = listaContactos;
	}

	public void loadData(String data, boolean reset) throws
			EmsInvalidTypeException, EmsInvalidNumberOfDataException, EmsDuplicatePersonException, EmsDuplicateLocationException
	{
		if (reset) {
			reset();
		}

		Stream<String> stream = Stream.of(data.split("\\n"));
		stream.forEach(line -> parseLine(line));
	}

	public void loadDataFile(String fichero, boolean reset) throws
			EmsInvalidTypeException, EmsInvalidNumberOfDataException, EmsDuplicatePersonException, EmsDuplicateLocationException, IOException
	{
		if (reset) {
			reset();
		}

		try (Stream<String> stream = Files.lines(Paths.get(fichero))) {
			stream.forEach(line -> parseLine(line));
		}
	}

	public int findPersona(String documento) throws EmsPersonNotFoundException {
		return poblacion.findPersona(documento);
	}

	public int findLocalizacion(String documento, String fecha, String hora) throws EmsLocalizationNotFoundException {
		FechaHora fh = FechaHora.parseFecha(fecha, hora);
		return localizacion.findLocalizacion(documento, fh);
	}

	public List<PosicionPersona> localizacionPersona(String documento) throws EmsPersonNotFoundException {
		return localizacion.localizacionPersona(documento);
	}

	public boolean delPersona(String documento) throws EmsPersonNotFoundException {
		poblacion.delPersona(documento);
		return false;
	}

	private static String[] dividirLineaData(String data) {
		return data.split("\\;");
	}

	private void reset() {
		this.poblacion = new Poblacion();
		this.localizacion = new Localizacion();
		this.listaContactos = new ListaContactos();
	}

	private void parseLine(String linea) throws
			EmsInvalidTypeException, EmsInvalidNumberOfDataException,EmsDuplicatePersonException, EmsDuplicateLocationException
	{

		String datos[] = ContactosCovid.dividirLineaData(linea);

		if ("PERSONA".equals(datos[0])) {
			this.poblacion.addPersona(Persona.parsePersona(datos));
		} else if ("LOCALIZACION".equals(datos[0])) {
			PosicionPersona pp = PosicionPersona.parsePosicionPersona(datos);
			this.localizacion.addLocalizacion(pp);
			this.listaContactos.insertarNodoTemporal(pp);
		} else {
			throw new EmsInvalidTypeException();
		}
	}
}