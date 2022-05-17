package com.examen.mercadolibre.service;

import org.springframework.stereotype.Service;

import com.examen.mercadolibre.DTO.DnaDTO;

@Service
public class MutantService {

	static char[] basesNitrogenadas = {'A', 'T', 'C', 'G'};

	static int size;
	//indica cuantas veces seguidas se debe encontrar la base nitrogendada
	static final Integer cantidad = 4;
	 
    // Indican las direcciones
	// Derecha, abajo, derecha-abajo, izquierda-abajo
    static Integer[] x = { 1, 0, 1, -1};
    static Integer[] y = { 0, 1, 1, 1};
 
    // Funcion que busca en las 4 direcciones
    // Desde el nucleotido que se encuentre en la posición [row][col]
    static boolean buscaSecuencia(String[] dna, Integer row, Integer col, char base)
    {
        //Se busca el caracter en todas las direcciones
        for (Integer dir = 0; dir < x.length ; dir++) {
            //Se inicializa la posicion para la direccion actual
        	Integer cont, rowDir = row + x[dir], colDir = col + y[dir];
            
            //El primer caracter ya ha sido validado
            //Se inicia el contador en 1 y se revisan los demas caracteres
            for (cont = 1; cont < cantidad; cont++) {
                // Se valida que la siguiente posicion no este fuera de rango
                if (rowDir >= size || rowDir < 0 || colDir >= size || colDir < 0)
                    break;
 
                // Validar si el caracter corresponde con la secuencia esperada
                if (dna[rowDir].charAt(colDir) != base)
                    break;
 
                // Modificar los indices segun la direccion para el siguiente caracter a validar
                rowDir += x[dir];
                colDir += y[dir];
            }
 
            // Si el la secuencia de nucleotidos fue encontrada
            // Entonces el contador tendra como valor 4 
            if (cont == cantidad)
                return true;
        }
        return false;
    }
 
    //Busca una secuencia dada en una matriz de genes
    public boolean recorreDNA(String[] dna, char base) throws Exception
    {
        // Se recorre la matriz
    	for (Integer row = 0; row < size; row++) {
    		if(dna[row].length() != size) {
    			throw new Exception("La Matriz de DNA no es de NxN");
    		}
            for (Integer col = 0; col < size; col++) {
                if (dna[row].charAt(col) == base  && buscaSecuencia(dna, row, col, base)) {
                	 return true;
                }
            }
        }
    	return false;
    }
 
    public boolean isMutant(DnaDTO adn) throws Exception
    {
		validateDna(adn);
        size = adn.dna.length;
        Integer contGenes = 0;
        for (char base : basesNitrogenadas) {
        	if(recorreDNA(adn.dna, base)) {
        		contGenes++;
        	}
        	if(contGenes > 1) {
        		return true;
        	}
        }
        return false;
    }
    
    public void validateDna(DnaDTO dna) throws Exception{
    	if(dna == null || dna.getDna() == null || dna.getDna().length == 0) {
    		throw new Exception("Ingrese DNA");
    	}
    	for (Integer row = 0; row < size; row++) {
            for (Integer col = 0; col < size; col++) {
                if (!isBase(dna.getDna()[row].charAt(col))) {
                	throw new Exception("El DNA ingresado contiene moleculas que no corresponden a bases nitrogenadas");
                }
            }
        }
    }    
    
    public boolean isBase(char base){
    	for(char nucl : basesNitrogenadas) {
    		if(base == nucl) {
    			return true;
    		}
    	}
    	return false;
    }
    
  
}
