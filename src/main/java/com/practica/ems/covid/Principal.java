package com.practica.ems.covid;

import java.io.IOException;

import com.practica.excecption.EmsDuplicateLocationException;
import com.practica.excecption.EmsDuplicatePersonException;
import com.practica.excecption.EmsInvalidNumberOfDataException;
import com.practica.excecption.EmsInvalidTypeException;

public class Principal {


	public static void main(String[] args) throws EmsDuplicatePersonException, EmsDuplicateLocationException, EmsInvalidTypeException, EmsInvalidNumberOfDataException {
		ContactosCovid contactosCovid = new ContactosCovid();

		try {
			contactosCovid.loadDataFile("datos2.txt", false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(contactosCovid.getLocalizacion().toString());
		System.out.println(contactosCovid.getPoblacion().toString());
		System.out.println(contactosCovid.getListaContactos().tamanioLista());
		System.out.println(contactosCovid.getListaContactos().getPrimerNodo());
		System.out.println(contactosCovid.getListaContactos());
	}
}