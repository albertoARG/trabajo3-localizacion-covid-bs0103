package com.practica.excecption;


public class EmsInvalidNumberOfDataException extends IllegalArgumentException {

	public EmsInvalidNumberOfDataException() {
		super("NUMERO DE PARAMETROS INCORRECTOS!");
		// TODO Auto-generated constructor stub
	}

	public EmsInvalidNumberOfDataException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}