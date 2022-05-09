package com.examen.mercadolibre.DTO;

import java.io.Serializable;

public class DnaDTO implements Serializable{
	static private final long serialVersionUID = 1L;

	public String[] dna;

	public DnaDTO() {
		super();
	}
	
	public DnaDTO(String[] dna) {
		super();
		this.dna = dna;
	}

	public String[] getDna() {
		return dna;
	}


	public void setDna(String[] dna) {
		this.dna = dna;
	}	
}
