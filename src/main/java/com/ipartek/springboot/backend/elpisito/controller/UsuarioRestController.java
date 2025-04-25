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

import com.ipartek.springboot.backend.apirest.models.entity.Usuario;
import com.ipartek.springboot.backend.apirest.models.services.IGeneralService;



//@CrossOrigin(origins = ("http://localhost:4200"))
@RestController
@RequestMapping("/api")
public class UsuarioRestController {
	
	//@RequestMapping sirve para indicar que las rutas definidas bajo
	//este Controller van a funcionar añadiendo /api (en nuestro caso)
	//a la url del servidor
	
	@Autowired
	private IGeneralService<Usuario> usuarioService;
	
	@GetMapping("/usuarios")
	public ResponseEntity<?> findAll() {
		
		
		Map<String, Object> response = new HashMap<>();
		List<Usuario> resultado = new ArrayList<>();
		
		try {
			
			resultado =	usuarioService.findAll();
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la búsqueda de todos los usuarios en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}		
		
		return new ResponseEntity<List<Usuario>>(resultado,HttpStatus.OK); //200
		
		
	}
	
	
	
	
	
	@GetMapping("/usuarios-activos")
	public ResponseEntity<?> findAllActive() {
		
		
		Map<String, Object> response = new HashMap<>();
		List<Usuario> resultado = new ArrayList<>();
		
		try {
			
			resultado =	usuarioService.findAllActive();
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la búsqueda de todos los usuarios activos en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}		
		
		return new ResponseEntity<List<Usuario>>(resultado,HttpStatus.OK); //200
		
	}
	
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		Usuario resultado = null;
		
		try {
			
			resultado =	usuarioService.findById(id);
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la búsqueda de un usuario con el id: " + id + " en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}		
		
		return new ResponseEntity<Usuario>(resultado,HttpStatus.OK); //200
		
		
	}
	
	
	@PostMapping("/usuario")
	public ResponseEntity<?> create(@RequestBody Usuario usuario) {
		
		Map<String, Object> response = new HashMap<>();
		Usuario usuarioNew = null;
		
		try {
			
			usuarioNew = usuarioService.save(usuario);
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al crear un usuario en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}	
		
		response.put("mensaje", "El usuario con id: " + usuarioNew.getId() + " y de nombre: " + usuarioNew.getUser() + " ha sido creado con éxito");
		response.put("usuario", usuarioNew);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK); //200
		
	}
	
	
	@PutMapping("/usuario")
	public ResponseEntity<?> update(@RequestBody Usuario usuario) {
		
		Long id = usuario.getId();
		Map<String, Object> response = new HashMap<>();
		Usuario usuarioUpdate = null;
		
		try {
			
			usuarioUpdate = usuarioService.save(usuario);
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al modificar un usuario en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}	
		
		response.put("mensaje", "El usuario con id: " + id + " y de nombre: " + usuarioUpdate.getUser() + " ha sido modificado con éxito");
		response.put("usuario", usuarioUpdate);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK); //200
		
		
	}
	
	
	/*@DeleteMapping("/usuario/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			usuarioService.deleteById(id);
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al eliminar al usuario con id: " + id + " en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}	
		
		response.put("mensaje", "El usuario con id: " + id + " ha sido eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK); //200
		
	}*/
	
	
	
	
	
	
	

}
