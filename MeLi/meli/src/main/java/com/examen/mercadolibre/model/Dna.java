package com.examen.mercadolibre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dna {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dnaId;
	
	@Column
    private String dnaGenes;
	
	@Column
    private Boolean dnaMutant;

	
	public Dna() {
		super();
	}

	public Dna(String dnaGenes, Boolean dnaMutant) {
		super();
		this.dnaGenes = dnaGenes;
		this.dnaMutant = dnaMutant;
	}

	public Integer getDnaId() {
		return dnaId;
	}

	public void setDnaId(Integer dnaId) {
		this.dnaId = dnaId;
	}

	public String getDnaGenes() {
		return dnaGenes;
	}

	public void setDnaGenes(String dnaGenes) {
		this.dnaGenes = dnaGenes;
	}

	public Boolean getDnaMutant() {
		return dnaMutant;
	}

	public void setDnaMutant(Boolean dnaMutant) {
		this.dnaMutant = dnaMutant;
	}
}
