package com.practica.ems.covid;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.practica.excecption.EmsDuplicateLocationException;
import com.practica.excecption.EmsLocalizationNotFoundException;
import com.practica.excecption.EmsPersonNotFoundException;
import com.practica.genericas.FechaHora;
import com.practica.genericas.PosicionPersona;

public class Localizacion {
	LinkedList<PosicionPersona> lista;

	public Localizacion() {
		super();
		this.lista = new LinkedList<PosicionPersona>();
	};
	
	public LinkedList<PosicionPersona> getLista() {
		return lista;
	}

	public void setLista(LinkedList<PosicionPersona> lista) {
		this.lista = lista;
	}

	public void addLocalizacion (PosicionPersona p) throws EmsDuplicateLocationException {
		if (lista.contains(p)) {
			throw new EmsDuplicateLocationException();
		}
		lista.add(p);
	}

	public int findLocalizacion (String documento, FechaHora fh) throws EmsLocalizationNotFoundException {
		PosicionPersona pp = new PosicionPersona(null, documento, fh);
		int pos = lista.indexOf(pp);

		if (pos == -1) {
			throw new EmsLocalizationNotFoundException();
		}

		return pos + 1;
	}

	public List<PosicionPersona> localizacionPersona(String documento) throws EmsPersonNotFoundException {
		List<PosicionPersona> result = lista.stream().filter(pp -> pp.isThisPerson(documento)).collect(Collectors.toList());

		if (result.isEmpty()) {
			throw new EmsPersonNotFoundException();
		}

		return result;
	}

	public void delLocalizacion(String documento, FechaHora fh) throws EmsLocalizationNotFoundException {
		PosicionPersona pp = new PosicionPersona(null, documento, fh);
		if (!lista.remove(pp)) {
			throw new EmsLocalizationNotFoundException();
		}
	}
	void printLocalizacion() {    
	    for(int i = 0; i < this.lista.size(); i++) {
	        System.out.printf("%d;%s;", i, lista.get(i).getDocumento());
	        FechaHora fecha = lista.get(i).getFechaPosicion();        
	        System.out.printf("%02d/%02d/%04d;%02d:%02d;", 
	        		fecha.getFecha().getDia(), 
	        		fecha.getFecha().getMes(), 
	        		fecha.getFecha().getAnio(),
	        		fecha.getHora().getHora(),
	        		fecha.getHora().getMinuto());
	        System.out.printf("%.4f;%.4f\n", lista.get(i).getCoordenada().getLatitud(), 
	        		lista.get(i).getCoordenada().getLongitud());
	    }
	}

	@Override
	public String toString() {
		String cadena = "";
		for(int i = 0; i < this.lista.size(); i++) {
			PosicionPersona pp = lista.get(i);
	        cadena += String.format("%s;", pp.getDocumento());
	        FechaHora fecha = pp.getFechaPosicion();        
	        cadena+=String.format("%02d/%02d/%04d;%02d:%02d;", 
	        		fecha.getFecha().getDia(), 
	        		fecha.getFecha().getMes(), 
	        		fecha.getFecha().getAnio(),
	        		fecha.getHora().getHora(),
	        		fecha.getHora().getMinuto());
	        cadena+=String.format("%.4f;%.4f\n", pp.getCoordenada().getLatitud(), 
	        		pp.getCoordenada().getLongitud());
	    }
		
		return cadena;		
	}
	

	
}
