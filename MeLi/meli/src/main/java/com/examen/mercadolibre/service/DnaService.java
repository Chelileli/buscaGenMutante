package com.examen.mercadolibre.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examen.mercadolibre.model.Dna;
import com.examen.mercadolibre.repository.DnaRepository;

@Service
public class DnaService {
	 	
	@Autowired
	DnaRepository dnaRepository;
	
	public List<Dna> getDna(){
		return dnaRepository.findAll();
	}
	
	@Transactional
	public Dna createDna(Dna dna){
		dna = dnaRepository.save(dna);
		return dna;
	}

	public Long getHumanDnaCount() {
		return dnaRepository.countByDnaMutant(false);
	}
	
	public Long getMutantDnaCount() {
		return dnaRepository.countByDnaMutant(true);
	}
}
