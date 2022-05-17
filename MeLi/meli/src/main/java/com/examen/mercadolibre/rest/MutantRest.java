package com.examen.mercadolibre.rest;

import java.io.IOException;

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
import com.examen.mercadolibre.repository.DnaRepository;
import com.examen.mercadolibre.service.MutantService;

@RestController
@RequestMapping("")
public class MutantRest {
	
	@Autowired
	MutantService mutantService = new MutantService();

	
	@Autowired 
	DnaRepository dnaRepository;
	
	public MutantRest(DnaRepository dnaRepository) {
		this.dnaRepository = dnaRepository;
	}

	@PostMapping("/mutant/")
	public ResponseEntity<String> getMutant(@RequestBody DnaDTO dna) throws IOException{
		try {

			if (mutantService.isMutant(dna)) {
				dnaRepository.save(new Dna(dna.getDna().toString(), true));
				return new ResponseEntity<String>(HttpStatus.OK);
			}else {
				dnaRepository.save(new Dna(dna.getDna().toString(), false));	
				return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
			}
				
		}catch (Exception e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/stats")
	public ResponseEntity<Stats> getStats(){
		try { 
			Long countMutant = dnaRepository.countByDnaMutant(true);
			Long countHuman = dnaRepository.countByDnaMutant(false);
			double ratio = countMutant * 1.0 / countHuman;
			Stats stats = new Stats(countMutant, countHuman, ratio);
			return new ResponseEntity<Stats>(stats, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<Stats>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
