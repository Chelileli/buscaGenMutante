package com.examen.mercadolibre.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examen.mercadolibre.DTO.DnaDTO;
import com.examen.mercadolibre.model.Dna;
import com.examen.mercadolibre.model.Stats;
import com.examen.mercadolibre.service.DnaService;
import com.examen.mercadolibre.service.MutantService;

@RestController
@RequestMapping("")
public class MutantRest {

	@Autowired
	MutantService mutantService;
	
	@Autowired
	DnaService dnaService;
	
	@PostMapping("/mutant/")
	public ResponseEntity<String[]> getMutant(@RequestBody DnaDTO dna) {
		if (mutantService.isMutant(dna.getDna())) {
			dnaService.createDna(new Dna(dna.getDna().toString(), true));
			return new ResponseEntity<String[]>(HttpStatus.OK);
		}else {
			dnaService.createDna(new Dna(dna.getDna().toString(), false));
			return new ResponseEntity<String[]>(HttpStatus.FORBIDDEN);
		}
	}
	
	@GetMapping("/stats")
	public ResponseEntity<Stats> getStats() {
		Long countMutant = dnaService.getMutantDnaCount();
		Long countHuman = dnaService.getHumanDnaCount();
		double radio = countMutant * 1.0 / countHuman;
		Stats stats = new Stats(countMutant, countHuman, radio);
		return new ResponseEntity<Stats>(stats, HttpStatus.OK);
		
	}
}
