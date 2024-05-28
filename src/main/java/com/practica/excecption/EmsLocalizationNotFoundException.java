package com.practica.excecption;


public class EmsLocalizationNotFoundException extends IllegalArgumentException{

	public EmsLocalizationNotFoundException() {
		super("LOCALIZACION NO ENCONTRADA!");
		// TODO Auto-generated constructor stub
	}

	public EmsLocalizationNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}