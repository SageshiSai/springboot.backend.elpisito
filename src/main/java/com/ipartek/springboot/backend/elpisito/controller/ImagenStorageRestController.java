package com.ipartek.springboot.backend.elpisito.controller;

import com.ipartek.springboot.backend.elpisito.models.entity.Imagen;
import com.ipartek.springboot.backend.elpisito.models.services.IGeneralService;
import com.ipartek.springboot.backend.elpisito.storage.IImagenStorageService;


import org.apache.tika.mime.MimeTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = ("http://localhost:4200"))
@RestController
@RequestMapping("/media")
public class ImagenStorageRestController {

    @Autowired
    private IGeneralService<Imagen> imagenService; //Podemos utilizar la Interface (IImagenStorageService) en vez de la implementación (ImagenStorageServiceImpl) porque polimorficamente son compatibles



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

    @GetMapping("/imagenes-activas")
    public ResponseEntity<?> findAllActive(){

        Map<String, Object> response = new HashMap<>();
        List<Imagen> resultado = new ArrayList<>();

        try {

            resultado =	imagenService.findAllActive();

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al realizar la búsqueda de todas las imágenes activas en la BBDD");
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



    @DeleteMapping("/imagen/{id}")
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

    }






}

