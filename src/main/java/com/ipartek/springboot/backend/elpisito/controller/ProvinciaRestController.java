package com.ipartek.springboot.backend.elpisito.controller;

import com.ipartek.springboot.backend.elpisito.models.entity.Provincia;
import com.ipartek.springboot.backend.elpisito.models.services.IGeneralService;
import com.ipartek.springboot.backend.elpisito.models.services.ProvinciaServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ProvinciaRestController {

    @Autowired
    private IGeneralService<Provincia> provinciaService;

    @GetMapping("/provincias")
    public ResponseEntity<?> findAll(){

        Map<String, Object> response = new HashMap<>();
        List<Provincia> resultado = new ArrayList<>();

        try {

            resultado =	provinciaService.findAll();

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al realizar la búsqueda de todas las provincias en la BBDD");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500

        }

        return new ResponseEntity<List<Provincia>>(resultado,HttpStatus.OK); //200

    }


    @GetMapping("/provincias-activas")
    public ResponseEntity<?> findAllActive(){

        Map<String, Object> response = new HashMap<>();
        List<Provincia> resultado = new ArrayList<>();

        try {

            resultado =	provinciaService.findAllActive();

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al realizar la búsqueda de todas las provincias activas en la BBDD");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500

        }

        return new ResponseEntity<List<Provincia>>(resultado,HttpStatus.OK); //200


    }

    @GetMapping("/provincia/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {


        Map<String, Object> response = new HashMap<>();
        Provincia resultado = null;

        try {

            resultado =	provinciaService.findById(id);

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al realizar la búsqueda de una provincia con el id: " + id + " en la BBDD");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500

        }

        return new ResponseEntity<Provincia>(resultado,HttpStatus.OK); //200

    }


    @PostMapping("/provincia")
    public ResponseEntity<?> create(@RequestBody Provincia provincia) {


        Map<String, Object> response = new HashMap<>();
        Provincia provinciaNew = null;

        try {

            provinciaNew = provinciaService.save(provincia);

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al crear una provincia en la BBDD");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500

        }

        response.put("mensaje", "La provincia con id: " + provinciaNew.getId() + " y de nombre: " + provinciaNew.getNombre() + " ha sido creada con éxito");
        response.put("provincia", provinciaNew);
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK); //200


    }


    @PutMapping("/provincia")
    public ResponseEntity<?> update(@RequestBody Provincia provincia) {

        Long id = provincia.getId();
        Map<String, Object> response = new HashMap<>();
        Provincia provinciaUpdate = null;

        try {

            provinciaUpdate = provinciaService.save(provincia);

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al modificar la provincia en la BBDD");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500

        }

        response.put("mensaje", "La provincia con id: " + id + " y de nombre: " + provinciaUpdate.getNombre() + " ha sido modificada con éxito");
        response.put("provincia", provinciaUpdate);
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK); //200


    }


    @DeleteMapping("/provincia/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();

        try {

            provinciaService.deleteById(id);

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al eliminar la provincia con id: " + id + " en la BBDD");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500

        }

        response.put("mensaje", "La provincia con id: " + id + " ha sido eliminada con éxito");
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK); //200

    }







}
