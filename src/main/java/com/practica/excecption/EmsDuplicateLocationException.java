package com.practica.excecption;


public class EmsDuplicateLocationException extends IllegalArgumentException{
	public EmsDuplicateLocationException() {
		super("LOCALIZACION DUPLICADA!");
		// TODO Auto-generated constructor stub
	}

	public EmsDuplicateLocationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}