package com.ipartek.springboot.backend.elpisito.controller;

import com.ipartek.springboot.backend.elpisito.models.entity.Tematica;
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
@RequestMapping("/api")
public class TematicaRestController {

    @Autowired
    private IGeneralService<Tematica> tematicaService;


    @GetMapping("/tematicas")
    public ResponseEntity<?> findAll(){

        Map<String, Object> response = new HashMap<>();
        List<Tematica> resultado = new ArrayList<>();

        try {

            resultado =	tematicaService.findAll();

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al realizar la búsqueda de todas las temáticas en la BBDD");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500

        }

        return new ResponseEntity<List<Tematica>>(resultado,HttpStatus.OK); //200

    }


    @GetMapping("/tematicas-activas")
    public ResponseEntity<?> findAllActive(){

        Map<String, Object> response = new HashMap<>();
        List<Tematica> resultado = new ArrayList<>();

        try {

            resultado =	tematicaService.findAllActive();

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al realizar la búsqueda de todas las temáticas activas en la BBDD");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500

        }

        return new ResponseEntity<List<Tematica>>(resultado,HttpStatus.OK); //200


    }


    @GetMapping("/tematica/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {


        Map<String, Object> response = new HashMap<>();
        Tematica resultado = null;

        try {

            resultado =	tematicaService.findById(id);

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al realizar la búsqueda de una temática con el id: " + id + " en la BBDD");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500

        }

        return new ResponseEntity<Tematica>(resultado,HttpStatus.OK); //200


    }

    @PostMapping("/tematica")
    public ResponseEntity<?> create(@RequestBody Tematica tematica) {

        Map<String, Object> response = new HashMap<>();
        Tematica tematicaNew = null;

        try {

            tematicaNew = tematicaService.save(tematica);

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al crear una temática en la BBDD");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500

        }

        response.put("mensaje", "La temática con id: " + tematicaNew.getId() + " y de nombre: " + tematicaNew.getTematica() + " ha sido creada con éxito");
        response.put("tematica", tematicaNew);
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK); //200


    }


    @PutMapping("/tematica")
    public ResponseEntity<?> update(@RequestBody Tematica tematica) {

        Long id = tematica.getId();
        Map<String, Object> response = new HashMap<>();
        Tematica tematicaUpdate = null;

        try {

            tematicaUpdate = tematicaService.save(tematica);

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al modificar una temática en la BBDD");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500

        }

        response.put("mensaje", "La temática con id: " + id + " y de nombre: " + tematicaUpdate.getTematica() + " ha sido modificada con éxito");
        response.put("tematica", tematicaUpdate);
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK); //200


    }


    @DeleteMapping("/tematica/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();

        try {

            tematicaService.deleteById(id);

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al eliminar la temática con id: " + id + " en la BBDD");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500

        }

        response.put("mensaje", "La temática con id: " + id + " ha sido eliminada con éxito");
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK); //200

    }



}
