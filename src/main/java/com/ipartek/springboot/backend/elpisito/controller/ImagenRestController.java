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

import com.ipartek.springboot.backend.apirest.models.entity.Imagen;


import com.ipartek.springboot.backend.apirest.models.services.IImagenArchivoGeneralService;

//@CrossOrigin(origins = ("http://localhost:4200"))
@RestController
@RequestMapping("/api")
public class ImagenRestController {
	
	@Autowired
	private IImagenArchivoGeneralService<Imagen> imagenService;
	
	
	@GetMapping("/imagenes")
	public ResponseEntity<?> findAll(){
		
		Map<String, Object> response = new HashMap<>();
		List<Imagen> resultado = new ArrayList<>();
		
		try {
			
			resultado =	imagenService.findAll();
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la búsqueda de todas las imágenes en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}		
		
		return new ResponseEntity<List<Imagen>>(resultado,HttpStatus.OK); //200
		
		
	}
	
	
	
	@GetMapping("/imagen/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		Imagen resultado = null;
		
		try {
			
			resultado =	imagenService.findById(id);
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la búsqueda de una imagen con el id: " + id + " en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}		
		
		return new ResponseEntity<Imagen>(resultado,HttpStatus.OK); //200
	}
	
	
	
	@PostMapping("/imagen")
	public ResponseEntity<?> create(@RequestBody Imagen imagen) {
		
		Map<String, Object> response = new HashMap<>();
		Imagen imagenNew = null;
		
		try {
			
			imagenNew = imagenService.save(imagen);
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al crear una imagen en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}	
		
		
		return new ResponseEntity<Imagen>(imagenNew,HttpStatus.OK); //200
		
		
	}
	
	
	@PutMapping("/imagen")
	public ResponseEntity<?> update(@RequestBody Imagen imagen) {
		
		
		Map<String, Object> response = new HashMap<>();
		Imagen imagenUpdate = null;
		
		try {
			
			imagenUpdate = imagenService.save(imagen);
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al modificar una imagen en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}	
		
	
		return new ResponseEntity<Imagen>(imagenUpdate,HttpStatus.OK); //200
		
		
	}
	
	
	
	/*@DeleteMapping("/imagen/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			imagenService.deleteById(id);
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al eliminar la imagen con id: " + id + " en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}	
		
		response.put("mensaje", "La imagen con id: " + id + " ha sido eliminada con éxito");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK); //200
		
	}*/
	
	
	
	
	

}
