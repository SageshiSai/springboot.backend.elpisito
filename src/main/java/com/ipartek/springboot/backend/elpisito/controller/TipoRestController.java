package com.ipartek.springboot.backend.elpisito.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ipartek.springboot.backend.apirest.models.entity.Tipo;
import com.ipartek.springboot.backend.apirest.models.services.IGeneralService;

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

//@CrossOrigin(origins = ("http://localhost:4200"))
@RestController
@RequestMapping("/api")
public class TipoRestController {
	
	@Autowired
	private IGeneralService<Tipo> tipoService;
	
	
	@GetMapping("/tipos")
	public ResponseEntity<?> findAll(){
		
		Map<String, Object> response = new HashMap<>();
		List<Tipo> resultado = new ArrayList<>();
		
		try {
			
			resultado =	tipoService.findAll();
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la búsqueda de todos los tipos en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}		
		
		return new ResponseEntity<List<Tipo>>(resultado,HttpStatus.OK); //200
		
	}
	
	
	
	
	
	@GetMapping("/tipos-activos")
	public ResponseEntity<?>  findAllActive(){
		
		Map<String, Object> response = new HashMap<>();
		List<Tipo> resultado = new ArrayList<>();
		
		try {
			
			resultado =	tipoService.findAllActive();
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la búsqueda de todos los tipos activos en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}		
		
		return new ResponseEntity<List<Tipo>>(resultado,HttpStatus.OK); //200
			
		
		
	}
	
	
	
	
	
	
	@GetMapping("/tipo/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		
		
		Map<String, Object> response = new HashMap<>();
		Tipo resultado = null;
		
		try {
			
			resultado =	tipoService.findById(id);
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la búsqueda de un tipo con el id: " + id + " en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}		
		
		return new ResponseEntity<Tipo>(resultado,HttpStatus.OK); //200
		
		
	}
	
	
	
	
	
	
	
	
	@PostMapping("/tipo")
	public ResponseEntity<?> create(@RequestBody Tipo tipo) {
		
		Map<String, Object> response = new HashMap<>();
		Tipo tipoNew = null;
		
		try {
			
			tipoNew = tipoService.save(tipo);
			
		}catch(DataIntegrityViolationException e) {
			
			response.put("mensaje", "Error al crear un tipo en la BBDD porque probablemente este tipo ya existe");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST); //400
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al crear un tipo en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}	
		
		response.put("mensaje", "El tipo con id: " + tipoNew.getId() + " y de nombre: " + tipoNew.getNombre() + " ha sido creado con éxito");
		response.put("tipo", tipoNew);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK); //200
		

		
		
		
	}
	
	
	
	
	@PutMapping("/tipo")
	public ResponseEntity<?> update(@RequestBody Tipo tipo) {
		
		Long id = tipo.getId();
		Map<String, Object> response = new HashMap<>();
		Tipo tipoUpdate = null;
		
		try {
			
			tipoUpdate = tipoService.save(tipo);
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al modificar un tipo en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}	
		
		response.put("mensaje", "El tipo con id: " + id + " y de nombre: " + tipoUpdate.getNombre() + " ha sido modificado con éxito");
		response.put("tipo", tipoUpdate);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK); //200
		
	}
	
	
	/*@DeleteMapping("/tipo/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			tipoService.deleteById(id);
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al eliminar el tipo con id: " + id + " en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}	
		
		response.put("mensaje", "El tipo con id: " + id + " ha sido eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK); //200
	}*/
	
	

}
