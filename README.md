# buscaGenMutante
Api para detectar si un humano es mutante basandose en su secuencia de ADN
Se recibe como parámetro un array de Strings que representan cada fina de una tabla de NxN
con la secuencia de ADN. 

Se sabe que un humano es mutante, si se encuentra más de una secuencia de 
cuatro letras iguales, de forma horizonta, vertical o diagonal.

Las letras de los Strings solo pueden ser A, T, C y G

- Ejemplo (Caso mutante)

String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};

## Pre-requisitos

- JDK 11
- Spring tool suite 4 
- Google App Engine
- MySql

## Ejecutar en Ambiente Local

Importar proyecto existente al IDE  
Ejecutar proyecto como Spring Boot App

El hambiente local requiere de una base de datos MySql. Si desea replicar la base de datos local, utilice el siguiente script,
Luego de esto debe modificar las configuraciones necesarias para la conexion a la nueva base de datos en el archivo application.propeties

#### Script de creación del schema y la base de datos
```sql
CREATE SCHEMA IF NOT EXISTS `examen_meli` DEFAULT CHARACTER SET latin1 COLLATE latin1_general_ci;
USE `examen_meli`;
CREATE USER 'examen_meli' identified by '123456';
GRANT ALL PRIVILEGES ON `examen_meli`.* TO 'examen_meli'@'%';
FLUSH PRIVILEGES;



CREATE TABLE IF NOT EXISTS `dna` (
`dna_id` INT NOT NULL AUTO_INCREMENT,
`dna_genes` VARCHAR(5000) NULL,
`dna_mutant` BOOLEAN NULL,
PRIMARY KEY (`dna_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 101;
```

## Ejecutar en el Cloud
Esta Api se encuentra deployada en Google App Engine en la siguiente url: https://egutierrez-meli.uc.r.appspot.com

El ambiente utiliza una base de datos MySql que ya cuenta con la base de datos creada para almacenar el historico de los DNA validados.
Para este caso no es necesario realizar una configuración de la base de datos.


### Endpoints
Los Endpoins para los requerimientos solicitados son los siguientes:
* `/mutant`: determina si un DNA tiene el gen mutante o no, respondiendo `200 OK` en caso de ser afirmativo y `403 FORBIDDEN` en caso contrario
* `/stats`: retorna información estadística de la cantidad de mutantes y humanos detectados y su proporción

## Consumir API

### Servicio /mutant/

Servicio HTTP POST que recibe como parámetro de entrada un objeto Json con un arreglo de dna de NxN.
El servicio muestra como resultado HTTP 200-OK en caso de encontrar el gen mutante y, en caso contrario un
403-Forbidden, Como se ve en el siguiente ejemplo:

![image](https://user-images.githubusercontent.com/8041581/167479007-b0cf6f17-7b65-46b4-b412-c44f8f66993b.png)

Ejemplo de objeto Json:
{"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}

El servicio se encuentra disponible en la siguiente ruta:
*Local: http://localhost:8080/mutant/
*Cloud: https://egutierrez-meli.uc.r.appspot.com/mutant/

### Servicio /stats

Servicio HTTP GET que retorna un Json con las estadísticas de las verificaciones de ADN, como la cantidad de humanos, mutantes y su relación

![image](https://user-images.githubusercontent.com/8041581/167479068-628149fe-32e2-4030-801d-4b918530add0.png)

El servicio se encuentra disponible en la siguiente ruta:
*Local: http://localhost:8080/stats
*Cloud: https://egutierrez-meli.uc.r.appspot.com/stats


