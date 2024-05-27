package com.practica.genericas;


import com.practica.excecption.EmsInvalidNumberOfDataException;

public class PosicionPersona {

	private static int MAX_DATOS_LOCALIZACION = 6;

	private Coordenada coordenada;
	private String documento;
	private FechaHora fechaPosicion;

	public static PosicionPersona parsePosicionPersona(String[] data) throws EmsInvalidNumberOfDataException {

		if (data.length != MAX_DATOS_LOCALIZACION) {
			throw new EmsInvalidNumberOfDataException("La cantidad de campos de la localización es incorrecta");
		}

		String documento = data[1];
		FechaHora fechaPosicion = FechaHora.parseFecha(data[2], data[3]);
		Coordenada coordenada = new Coordenada(Float.parseFloat(data[4]), Float.parseFloat(data[5]));
		return new PosicionPersona(coordenada, documento, fechaPosicion);
	}

	public PosicionPersona(){

	}

	public PosicionPersona(Coordenada coordenada, String documento, FechaHora fechaPosicion) {
		this.coordenada = coordenada;
		this.documento = documento;
		this.fechaPosicion = fechaPosicion;
	}


	public Coordenada getCoordenada() {
		return coordenada;
	}
	public void setCoordenada(Coordenada coordenada) {
		this.coordenada = coordenada;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public FechaHora getFechaPosicion() {
		return fechaPosicion;
	}
	public void setFechaPosicion(FechaHora fechaPosicion) {
		this.fechaPosicion = fechaPosicion;
	}

	public boolean isThisPerson(String documento) {
		return this.documento.equals(documento);
	}

	@Override
	public String toString() {
		return String.format("%s;%s;%s", documento, fechaPosicion.toString(), coordenada.toString());
	}


}
