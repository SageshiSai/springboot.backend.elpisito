package com.ipartek.springboot.backend.elpisito.controller;

import java.io.IOException;

import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tika.mime.MimeTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.ipartek.springboot.backend.apirest.models.entity.Imagen;
import com.ipartek.springboot.backend.apirest.storage.IImagenStorageService;


//@CrossOrigin(origins = ("http://localhost:4200"))
@RestController
@RequestMapping("/media")
public class ImagenStorageRestController {
	
	@Autowired
	private IImagenStorageService imagenStorageService; //Podemos utilizar la Interface (IImagenStorageService) en vez de la implementación (ImagenStorageServiceImpl) porque polimorficamente son compatibles
	

	
	@PostMapping("/imagen/{idInmueble}")
	public ResponseEntity<?> uploadImagen(@RequestParam("imagen") MultipartFile multipartFile, @PathVariable Long idInmueble) {
		//La anotación "imagen" hecha en @RequestParam es super importante porque es la referencia que
		//debemos emplear en cliente
		
		Map<String, String> response = new HashMap<>();
		String url = null;
		
		
		try {
			
			String nombreImagen = imagenStorageService.store(multipartFile,idInmueble);
			//EN ESTE PUNTO EL ARCHIVO FÍSICO YA ESTÁ SUBIDO Y LA ANOTACIÓN EFECTUADA EN LA BBDD
			
			//A PARTIR DE ESTE PUNTO LO QUE VAMOS A HACER ES CONSEGUIR LA URL COMPLETA DE LA IMAGEN
			//SUBIDA PARA DEVOLVERLA EN EL RESPONSE (response)
			//Vamos a conseguir la URL completa del archivo...
			
			url = imagenStorageService.getUrlCompletaImagen(nombreImagen);
			
		}catch(RuntimeException e) {
			
			response.put("mensaje", e.getMessage()); //Este es el mensaje de error 
			return new ResponseEntity<Map<String,String>>(response, HttpStatus.BAD_REQUEST);//Usamos 400 para indicar un error surgido en la capa de cliente
			
			
		}catch(MimeTypeException e) { //Excepcion de tipo checked. Nunca ponerla detrás del IOException
			
			response.put("mensaje", "Error al obtener el tipo MIME del archivo"); 
			return new ResponseEntity<Map<String,String>>(response, HttpStatus.NOT_ACCEPTABLE);//Usamos 406 para indicar que el archivo subido tiene algún problema con su tipo MIME. archivo corrupto???
			
			
		}catch(IOException e) {
			
			response.put("mensaje", "Error al subir el archivo al intentar su almacenamiento en el servidor"); 
			return new ResponseEntity<Map<String,String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);//Usamos 500 para indicar un error en el proceso de escritura o creación del archivo físico
			
			
		}
		
		
		
		
		response.put("url", url);
		return new ResponseEntity<Map<String,String>>(response, HttpStatus.OK);//Utilizamos un 200 para indicar que todo ha ido genial
		
		
		
	}
	
	//Este método nos devuelve la imagen física
	@GetMapping("/imagen/{nombreImagen:.+}")
	public ResponseEntity<?> getImagen(@PathVariable String nombreImagen){
		
		Map<String,String> response = new HashMap<>();
		Resource imagen = null;
		String contentType = null;
		
		try {
			
			imagen = imagenStorageService.loadAsResource(nombreImagen);
			//Extraemos el content type (Tipo de contenido-Tipo MIME) para pasarlo en el header de la response
			contentType =  Files.probeContentType(imagen.getFile().toPath());
		}catch(Exception e) {
			
			response.put("mensaje","Error al cargar el recurso");
			return new ResponseEntity<Map<String,String>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_TYPE,contentType)
				.body(imagen);
		
	}
	
	
	@GetMapping("/imagenes/{idInmueble}")
	public ResponseEntity<?> getImagenesByInmuebleId(@PathVariable Long idInmueble){
		
		Map<String, String> response = new HashMap<>();
		
		try {
		
			List<Imagen> imagenes = imagenStorageService.getImagenesByInmuebleId(idInmueble);
			
			
			if( imagenes.isEmpty() || imagenes == null) {
				
				response.put("mensaje", "No se encontraron imágenes para este inmueble");
				return new ResponseEntity<Map<String, String>>(response,HttpStatus.OK); //Enviamos un 200 porque no se han encontrado archivos pero no ha habido ningún problema...
			}else {
						
				return new ResponseEntity<List<Imagen>>(imagenes,HttpStatus.OK);
					
			}
		
		}catch (RuntimeException e) {
			
			response.put("mensaje", "Error al obtener las imágenes del inmueble");
			return new ResponseEntity<Map<String, String>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		
	}
	
	
	@DeleteMapping("/imagen/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			imagenStorageService.deleteImagen(id);
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al eliminar la imagen con id: " + id + " en la BBDD");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}catch(IOException e) {
			
			response.put("mensaje", "Error al eliminar la imagen con id: " + id + " en la BBDD");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500
			
		}
		
		response.put("mensaje", "La imagen con id: " + id + " ha sido eliminada con éxito");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK); //200
		
	}
	
	
	
	
	

}
