# buscaGenMutante
Api para detectar si un humano es mutante basandose en su secuencia de ADN
Se recibe como parámetro un array de Strings que representan cada fina de una tabla de NxN
con la secuencia de ADN. 

Se sabe que un humano es mutante, si se encuentra más de una secuencia de 
cuatro letras iguales, de forma horizonta, vertical o diagonal.

Las letras de los Strings solo pueden ser A, T, C y G

- Ejemplo (Caso mutante)

String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};

## Tecnologias

- Java 11
- Google App Engine
- MySql

## Ejecutar Api

El Api cuenta con dos servicios

### Servicio /mutant/

Servicio HTTP POST que recibe como parámetro de entrada un objeto Json con un arreglo de dna de NxN.
El servicio muestra como resultado HTTP 200-OK en caso de encontrar el gen mutante y, en caso contrario un
403-Forbidden, Como se ve en el siguiente ejemplo:

![image](https://user-images.githubusercontent.com/8041581/167479007-b0cf6f17-7b65-46b4-b412-c44f8f66993b.png)

Ejemplo de objeto Json:
{"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}

El servicio se encuentra disponible en la siguiente ruta:
https://egutierrez-meli.uc.r.appspot.com/mutant/

### Servicio /stats

Servicio HTTP GET que retorna un Json con las estadísticas de las verificaciones de ADN, como la cantidad de humanos, mutantes y su relación

![image](https://user-images.githubusercontent.com/8041581/167479068-628149fe-32e2-4030-801d-4b918530add0.png)

El servicio se encuentra disponible en la siguiente ruta:
https://egutierrez-meli.uc.r.appspot.com/stats


