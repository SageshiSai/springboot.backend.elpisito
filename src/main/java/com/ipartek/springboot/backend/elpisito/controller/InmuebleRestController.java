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

import com.ipartek.springboot.backend.apirest.models.entity.Inmueble;

import com.ipartek.springboot.backend.apirest.models.services.IInmuebleService;

//@CrossOrigin(origins = ("http://localhost:4200"))
@RestController
@RequestMapping("/api")
public class InmuebleRestController {
	
	
	@Autowired
	private IInmuebleService inmuebleService;
	
	
	@GetMapping("/inmuebles")
	public ResponseEntity<?> findAll(){
		
		
		Map<String, Object> response = new HashMap<>();
		List<Inmueble> resultado = new ArrayList<>();
		
		try {
			
			resultado =	inmuebleService.findAll();
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la búsqueda de todos los inmuebles en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}		
		
		return new ResponseEntity<List<Inmueble>>(resultado,HttpStatus.OK); //200
	}
	
	
	
	
	@GetMapping("/inmuebles-activos")
	public ResponseEntity<?> findAllActive(){
		
		
		Map<String, Object> response = new HashMap<>();
		List<Inmueble> resultado = new ArrayList<>();
		
		try {
			
			resultado =	inmuebleService.findAllActive();
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la búsqueda de todos los inmuebles activos en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}		
		
		return new ResponseEntity<List<Inmueble>>(resultado,HttpStatus.OK); //200
		
		
	}
	
	
	@GetMapping("/inmuebles-portada")
	public ResponseEntity<?> findAllPortada(){
		
		Map<String, Object> response = new HashMap<>();
		List<Inmueble> resultado = new ArrayList<>();
		
		try {
			
			resultado =	inmuebleService.findAllPortada();
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la búsqueda de todos los inmuebles de portada en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}		
		
		return new ResponseEntity<List<Inmueble>>(resultado,HttpStatus.OK); //200
		
		
		
	}
	
	
	@GetMapping("/inmuebles-inmobiliaria/{idInmobiliaria}")
	public ResponseEntity<?> findAllInmobiliaria(@PathVariable Long idInmobiliaria){
		
		
		
		Map<String, Object> response = new HashMap<>();
		List<Inmueble> resultado = new ArrayList<>();
		
		try {
			
			resultado =	inmuebleService.findAllInmobiliaria(idInmobiliaria);
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la búsqueda de todos los inmuebles de una inmobiliaria en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}		
		
		return new ResponseEntity<List<Inmueble>>(resultado,HttpStatus.OK); //200
		
		
	}
	
	
	@GetMapping("/inmueble/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		
		
		Map<String, Object> response = new HashMap<>();
		Inmueble resultado = null;
		
		try {
			
			resultado =	inmuebleService.findById(id);
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la búsqueda de un inmueble con el id: " + id + " en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}		
		
		return new ResponseEntity<Inmueble>(resultado,HttpStatus.OK); //200
		
		
		
		
	}
	
	
	@PostMapping("/inmueble")
	public ResponseEntity<?> create(@RequestBody Inmueble inmueble) {
		
		Map<String, Object> response = new HashMap<>();
		Inmueble inmuebleNew = null;
		
		try {
			
			inmuebleNew = inmuebleService.save(inmueble);
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al crear un inmueble en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}	
		
		response.put("mensaje", "El inmueble con id: " + inmuebleNew.getId() + " ha sido creado con éxito");
		response.put("inmueble", inmuebleNew);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK); //200
		
	}
	
	
	@PutMapping("/inmueble")
	public ResponseEntity<?> update(@RequestBody Inmueble inmueble) {
		
		Long id = inmueble.getId();
		Map<String, Object> response = new HashMap<>();
		Inmueble inmuebleUpdate = null;
		
		try {
			
			inmuebleUpdate = inmuebleService.save(inmueble);
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al modificar un inmueble en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}	
		
		response.put("mensaje", "El inmueble con id: " + id + " ha sido modificado con éxito");
		response.put("inmueble", inmuebleUpdate);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK); //200
		
		
	}
	
	
	/*@DeleteMapping("/inmueble/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			inmuebleService.deleteById(id);
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al eliminar el inmueble con id: " + id + " en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}	
		
		response.put("mensaje", "El inmueble con id: " + id + " ha sido eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK); //200
		
	}*/
	
	
	

}
