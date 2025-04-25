package com.ipartek.springboot.backend.elpisito.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.springboot.backend.apirest.models.entity.Archivo;

import com.ipartek.springboot.backend.apirest.models.services.IImagenArchivoGeneralService;

//@CrossOrigin(origins = ("http://localhost:4200"))
@RestController
@RequestMapping("/api")
public class ArchivoRestController {
	
	@Autowired
	private IImagenArchivoGeneralService<Archivo> archivoService;
	
	
	@GetMapping("/archivos")
	public ResponseEntity<?> findAll(){
		
		Map<String, Object> response = new HashMap<>();
		List<Archivo> resultado = new ArrayList<>();
		
		try {
			
			resultado =	archivoService.findAll();
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la búsqueda de todos los archivos en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}		
		
		return new ResponseEntity<List<Archivo>>(resultado,HttpStatus.OK); //200
		
	}
	

	
	
	@GetMapping("/archivo/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		Archivo resultado = null;
		
		try {
			
			resultado =	archivoService.findById(id);
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la búsqueda de un archivo con el id: " + id + " en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}		
		
		return new ResponseEntity<Archivo>(resultado,HttpStatus.OK); //200
		
	}
	
	
	@PostMapping("/archivo")
	public ResponseEntity<?> create(@RequestBody Archivo archivo) {
		
		Map<String, Object> response = new HashMap<>();
		Archivo archivoNew = null;
		
		try {
			
			archivoNew = archivoService.save(archivo);
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al crear un archivo en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}	
		
		
		return new ResponseEntity<Archivo>(archivoNew,HttpStatus.OK); //200
		
		
	}
	
	@PutMapping("/archivo")
	public ResponseEntity<?> update(@RequestBody Archivo archivo) {
		
		
		Map<String, Object> response = new HashMap<>();
		Archivo archivoUpdate = null;
		
		try {
			
			archivoUpdate = archivoService.save(archivo);
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al modificar un archivo en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}	
		

		return new ResponseEntity<Archivo>(archivoUpdate,HttpStatus.OK); //200
		
		
	}
	
	/*@DeleteMapping("/archivo/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			archivoService.deleteById(id);
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al eliminar el archivo con id: " + id + " en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}	
		
		response.put("mensaje", "El archivo con id: " + id + " ha sido eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK); //200
		
	}*/
	

}
