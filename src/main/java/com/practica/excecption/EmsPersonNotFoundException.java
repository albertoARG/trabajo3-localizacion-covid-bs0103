package com.practica.excecption;


public class EmsPersonNotFoundException extends IllegalArgumentException{

	public EmsPersonNotFoundException() {
		super("PERSONA NO ENCONTRADA!");
	}

	public EmsPersonNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}