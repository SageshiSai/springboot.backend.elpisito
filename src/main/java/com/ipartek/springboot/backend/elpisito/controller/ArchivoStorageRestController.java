package com.ipartek.springboot.backend.elpisito.controller;


import com.ipartek.springboot.backend.elpisito.models.entity.Archivo;
import com.ipartek.springboot.backend.elpisito.models.services.IGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = ("http://localhost:4200"))
@RestController
@RequestMapping("/media")
public class ArchivoStorageRestController {

    @Autowired
    private IGeneralService<Archivo> archivoStorageService; //Podemos utilizar la Interface (IArchivoStorageService) en vez de la implementación (ArchivoStorageServiceImpl) porque polimorficamente son compatibles



    @GetMapping("/archivos")
    public ResponseEntity<?> findAll(){

        Map<String, Object> response = new HashMap<>();
        List<Archivo> resultado = new ArrayList<>();

        try {

            resultado =	archivoStorageService.findAll();

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al realizar la búsqueda de todos los archivos en la BBDD");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500

        }

        return new ResponseEntity<List<Archivo>>(resultado,HttpStatus.OK); //200

    }


    @GetMapping("/archivos-activos")
    public ResponseEntity<?> findAllActive(){

        Map<String, Object> response = new HashMap<>();
        List<Archivo> resultado = new ArrayList<>();

        try {

            resultado =	archivoStorageService.findAllActive();

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al realizar la búsqueda de todos los archivos activos en la BBDD");
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

            resultado =	archivoStorageService.findById(id);

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

            archivoNew = archivoStorageService.save(archivo);

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

            archivoUpdate = archivoStorageService.save(archivo);

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al modificar un archivo en la BBDD");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500

        }


        return new ResponseEntity<Archivo>(archivoUpdate,HttpStatus.OK); //200


    }

    @DeleteMapping("/archivo/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();

        try {

            archivoStorageService.deleteById(id);

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al eliminar el archivo con id: " + id + " en la BBDD");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500

        }

        response.put("mensaje", "El archivo con id: " + id + " ha sido eliminado con éxito");
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK); //200

    }


}


