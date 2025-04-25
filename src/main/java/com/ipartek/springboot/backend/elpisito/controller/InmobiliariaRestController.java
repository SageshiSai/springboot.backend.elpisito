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

import com.ipartek.springboot.backend.apirest.models.entity.Inmobiliaria;

import com.ipartek.springboot.backend.apirest.models.services.IGeneralService;

//@CrossOrigin(origins = ("http://localhost:4200"))
@RestController
@RequestMapping("/api")
public class InmobiliariaRestController {
	
	
	@Autowired
	private IGeneralService<Inmobiliaria> inmobiliariaService;
	
	
	@GetMapping("/inmobiliarias")
	public ResponseEntity<?> findAll(){
		
		Map<String, Object> response = new HashMap<>();
		List<Inmobiliaria> resultado = new ArrayList<>();
		
		try {
			
			resultado =	inmobiliariaService.findAll();
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la búsqueda de todas las inmobiliarias en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}		
		
		return new ResponseEntity<List<Inmobiliaria>>(resultado,HttpStatus.OK); //200
		
	}
	
	
	@GetMapping("/inmobiliarias-activas")
	public ResponseEntity<?> findAllActive(){
		
		Map<String, Object> response = new HashMap<>();
		List<Inmobiliaria> resultado = new ArrayList<>();
		
		try {
			
			resultado =	inmobiliariaService.findAllActive();
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la búsqueda de todas las inmobiliarias activas en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}		
		
		return new ResponseEntity<List<Inmobiliaria>>(resultado,HttpStatus.OK); //200
		
	}
	
	
	@GetMapping("inmobiliaria/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		Inmobiliaria resultado = null;
		
		try {
			
			resultado =	inmobiliariaService.findById(id);
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la búsqueda de una inmobiliaria con el id: " + id + " en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}		
		
		return new ResponseEntity<Inmobiliaria>(resultado,HttpStatus.OK); //200
	}
	
	
	@PostMapping("/inmobiliaria")
	public ResponseEntity<?> create(@RequestBody Inmobiliaria inmobiliaria ) {
		
		Map<String, Object> response = new HashMap<>();
		Inmobiliaria inmobiliariaNew = null;
		
		try {
			
			inmobiliariaNew = inmobiliariaService.save(inmobiliaria);
			
		}catch(DataIntegrityViolationException e) {
			
			response.put("mensaje", "Error al crear una inmobiliaria en la BBDD porque probablemente esta inmobiliaria ya existe");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST); //400
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al crear una inmobiliaria en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}	
		
		response.put("mensaje", "La inmobiliaria con id: " + inmobiliariaNew.getId() + " y de nombre: " + inmobiliariaNew.getNombre() + " ha sido creada con éxito");
		response.put("inmobiliaria", inmobiliariaNew);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK); //200
		
		
	}
	
	@PutMapping("/inmobiliaria")
	public ResponseEntity<?> update(@RequestBody Inmobiliaria inmobiliaria ) {
		
		Long id = inmobiliaria.getId();
		Map<String, Object> response = new HashMap<>();
		Inmobiliaria inmobiliariaUpdate = null;
		
		try {
			
			inmobiliariaUpdate = inmobiliariaService.save(inmobiliaria);
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "La inmobiliaria con id: " + id + " y de nombre: " + inmobiliariaUpdate.getNombre() + " ha sido modificada con éxito");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}	
		
		response.put("mensaje", "La inmobiliaria con id: " + id + " ha sido modificada con éxito");
		response.put("inmobiliaria", inmobiliariaUpdate);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK); //200
		
	}
	
	
	/*@DeleteMapping("/inmobiliaria/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			inmobiliariaService.deleteById(id);
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al eliminar la inmobiliaria con id: " + id + " en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}	
		
		response.put("mensaje", "La inmobiliaria con id: " + id + " ha sido eliminada con éxito");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK); //200
		
	}*/
	
	
	
	

}
