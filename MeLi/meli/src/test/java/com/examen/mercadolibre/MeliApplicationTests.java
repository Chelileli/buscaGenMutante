package com.examen.mercadolibre;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.examen.mercadolibre.DTO.DnaDTO;
import com.examen.mercadolibre.model.Dna;
import com.examen.mercadolibre.model.Stats;
import com.examen.mercadolibre.repository.DnaRepository;
import com.examen.mercadolibre.rest.MutantRest;


public class MeliApplicationTests {
/*
	@Autowired
	public MutantRest mutantRest = new MutantRest();
*/
	

	DnaRepository dnaRepositoryMock = Mockito.mock(DnaRepository.class);

	@Autowired
	MutantRest mutantRest = new MutantRest(dnaRepositoryMock);

	@Test
	void dnaTestEsMutante() throws Exception {

		String[] dna = {"AAAAT", "CCCAT", "CATCT", "TCGAT", "TGACT"};
		DnaDTO adnDTO = new DnaDTO();
		adnDTO.setDna(dna);
		Dna mockDna = new Dna(dna.toString(),true);
		Mockito.when(dnaRepositoryMock.save(mockDna)).thenReturn(mockDna);
		ResponseEntity<String> response =  mutantRest.getMutant(adnDTO);

		Assertions.assertEquals(200,response.getStatusCodeValue());		
	}
	
	@Test
	void dnaTestNoEsMutante() throws Exception {

		String[] dna = {"AACAT", "CCCAT", "CATCT", "TCGAT", "TGACT"};
		DnaDTO adnDTO = new DnaDTO();
		adnDTO.setDna(dna);
		Dna mockDna = new Dna(dna.toString(),true);
		Mockito.when(dnaRepositoryMock.save(mockDna)).thenReturn(mockDna);
		ResponseEntity<String> response =  mutantRest.getMutant(adnDTO);

		Assertions.assertEquals(403,response.getStatusCodeValue());		
	}
	
	@Test
	void dnaTestNoEsMatrizValida() throws Exception {

		String[] dna = {"AACATTTT", "CCCAT", "CATCT", "TCGAT", "TGACT"};
		DnaDTO adnDTO = new DnaDTO();
		adnDTO.setDna(dna);
		Dna mockDna = new Dna(dna.toString(),true);
		Mockito.when(dnaRepositoryMock.save(mockDna)).thenReturn(mockDna);
		ResponseEntity<String> response =  mutantRest.getMutant(adnDTO);

		Assertions.assertEquals(400,response.getStatusCodeValue());		
	}
	
	@Test
	void dnaTestTieneCaracteresNoAceptados() throws Exception {

		String[] dna = {"AACAT", "CCCBT", "CATCT", "TCGAT", "TGACT"};
		DnaDTO adnDTO = new DnaDTO();
		adnDTO.setDna(dna);
		Dna mockDna = new Dna(dna.toString(),true);
		Mockito.when(dnaRepositoryMock.save(mockDna)).thenReturn(mockDna);
		ResponseEntity<String> response =  mutantRest.getMutant(adnDTO);

		Assertions.assertEquals(400,response.getStatusCodeValue());		
	}
	
	@Test
	void dnaTestDnaNull() throws Exception {
		DnaDTO adnDTO = new DnaDTO();
		adnDTO.setDna(null);
		Dna mockDna = new Dna(null,true);
		Mockito.when(dnaRepositoryMock.save(mockDna)).thenReturn(mockDna);
		ResponseEntity<String> response =  mutantRest.getMutant(adnDTO);

		Assertions.assertEquals(400,response.getStatusCodeValue());		
	}
	
	@Test
	void dnaTestDnaNull2() throws Exception {

		DnaDTO adnDTO = null;
		Dna mockDna = null;
		Mockito.when(dnaRepositoryMock.save(mockDna)).thenReturn(mockDna);
		ResponseEntity<String> response =  mutantRest.getMutant(adnDTO);

		Assertions.assertEquals(400,response.getStatusCodeValue());		
	}


	@Test
	public void testStatOk() throws Exception{ 
		ResponseEntity<Stats> response = mutantRest.getStats();
		Assertions.assertEquals(200,response.getStatusCodeValue());	

	}
}
