package com.examen.mercadolibre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examen.mercadolibre.model.Dna;

public interface DnaRepository extends JpaRepository<Dna, Integer>{
	long countByDnaMutant(Boolean dnaMutant);
	
}
