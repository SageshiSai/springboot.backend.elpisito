package com.ipartek.springboot.backend.elpisito.controller;

import com.ipartek.springboot.backend.elpisito.storage.IImagenStorageService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/media")
public class ImagenStorageRestController {

    @Autowired
    private IImagenStorageService imagenStorageService;
    @Autowired
    private HttpServletRequest request;

    @PostMapping("/upload/{idInmueble}")
    public ResponseEntity<?> uploadFile(@RequestParam("imagen") MultipartFile multipartFile, @PathVariable Long idInmueble){
        //La anotación "file" hecha en @RequestParam es superimportante porque es la referencia que debemos emplear en cliente

        Map<String, String> response = new HashMap<>();
        String url = null;

        try{
            String nombreImagen = imagenStorageService.store(multipartFile, idInmueble);

            //El archivo físico ya está subido y la anotación efectuada en la BBDD
            //A partir de este punto lo que vamos a hacer es conseguir la URL completa de la IMAGEN subida para devolverla en el response (response)
            //Vamos a conseguir la URL completa del archivo...

            String host = request.getRequestURL().toString().replace(request.getRequestURI(), ""); //"http://localhost:8080" (En desarrollo claro!!!...)
            url = ServletUriComponentsBuilder
                    .fromUriString(host)  //Añadimos la primera parte "http://localhost:8080"
                    .path("/media/imagen/") //Añadimos la carpeta donde se encuentra el recurso
                    .path(nombreImagen)   //"http://localhost:8080/media/imagen/89898989898989898.jpg"
                    .toUriString();
        } catch (RuntimeException e) {
            response.put("mensaje", e.getMessage()); //Este es el mensaje de error
            return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST); //Usamos 400 para indicar un error en cliente
        } catch (IOException e){
            response.put("mensaje", "Error al subir el archivo en al intentar su almacenamiento en el servidor"); //Este es el mensaje de error
            return new ResponseEntity<Map<String, String>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //Usamos 500 para indicar un error en servidor en el proceso de escritura o creación del archivo físico
        }
        response.put("url", url);
        return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK); //Utilizamos un 200 para indicar que todo esta bien
    }
    //Este método nos devuelve la imagen física.
    @GetMapping("imagen/{nombreImagen:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String nombreImagen){
        Resource imagen  = imagenStorageService.loadAsResource(nombreImagen);
        String contentType = null;
        try {
            //Extraemos el content type (tipo de contenido-Tipo MIME) para pasarlo en el header de la response
        contentType = Files.probeContentType(imagen.getFile().toPath());

        } catch (IOException e){

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(imagen);
    }
}
