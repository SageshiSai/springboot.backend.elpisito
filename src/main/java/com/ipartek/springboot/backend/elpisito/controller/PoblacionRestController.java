package com.ipartek.springboot.backend.elpisito.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.springboot.backend.apirest.models.entity.Poblacion;

import com.ipartek.springboot.backend.apirest.models.services.IGeneralService;

//@CrossOrigin(origins = ("http://localhost:4200"))
@RestController
@RequestMapping("/api")
public class PoblacionRestController {
	
	@Autowired
	private IGeneralService<Poblacion> poblacionService;
	
	
	
	@GetMapping("/poblaciones")
	public ResponseEntity<?> findAll(){
		
		Map<String, Object> response = new HashMap<>();
		List<Poblacion> resultado = new ArrayList<>();
		
		try {
			
			resultado =	poblacionService.findAll();
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la búsqueda de todas las poblaciones en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}		
		
		return new ResponseEntity<List<Poblacion>>(resultado,HttpStatus.OK); //200
		
		
	}
	
	
	@GetMapping("/poblaciones-activas")
	public ResponseEntity<?> findAllActive(){
		
		
		Map<String, Object> response = new HashMap<>();
		List<Poblacion> resultado = new ArrayList<>();
		
		try {
			
			resultado =	poblacionService.findAllActive();
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la búsqueda de todas las poblaciones activas en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}		
		
		return new ResponseEntity<List<Poblacion>>(resultado,HttpStatus.OK); //200

		
	}
	
	
	@GetMapping("poblacion/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		Poblacion resultado = null;
		
		try {
			
			resultado =	poblacionService.findById(id);
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la búsqueda de una población con el id: " + id + " en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}		
		
		return new ResponseEntity<Poblacion>(resultado,HttpStatus.OK); //200
		
	}
	
	
	@PostMapping("/poblacion")
	public ResponseEntity<?> create(@RequestBody Poblacion poblacion ) {
		
		Map<String, Object> response = new HashMap<>();
		Poblacion poblacionNew = null;
		
		try {
			
			poblacionNew = poblacionService.save(poblacion);
			
		}catch(DataIntegrityViolationException e) {
			
			response.put("mensaje", "Error al crear una población en la BBDD porque probablemente esta población ya existe");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST); //400
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al crear una población en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}	
		
		response.put("mensaje", "La población con id: " + poblacionNew.getId() + " y de nombre: " + poblacionNew.getNombre() + " ha sido creada con éxito");
		response.put("poblacion", poblacionNew);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK); //200
		
		
	}
	
	@PutMapping("/poblacion")
	public ResponseEntity<?> update(@RequestBody Poblacion poblacion ) {
		
		Long id = poblacion.getId();
		Map<String, Object> response = new HashMap<>();
		Poblacion poblacionUpdate = null;
		
		try {
			
			poblacionUpdate = poblacionService.save(poblacion);
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al modificar una población en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}	
		
		response.put("mensaje", "La población con id: " + id + " y de nombre: " + poblacionUpdate.getNombre() + " ha sido modificada con éxito");
		response.put("poblacion", poblacionUpdate);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK); //200
		
	}
	
	
	/*@DeleteMapping("/poblacion/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			poblacionService.deleteById(id);
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al eliminar la población con id: " + id + " en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}	
		
		response.put("mensaje", "La población con id: " + id + " ha sido eliminada con éxito");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK); //200
		
	}*/
	
	
	
	
	
	
	
	

}
