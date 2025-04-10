package com.ipartek.springboot.backend.elpisito.controller;

import com.ipartek.springboot.backend.elpisito.models.entity.Usuarios;
import com.ipartek.springboot.backend.elpisito.models.services.IGeneralService;
import com.ipartek.springboot.backend.elpisito.models.services.UsuarioServiceImpl;
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
public class UsuarioRestController {

    //@RequestMapping sirve para indicar que las rutas definidas bajo
    //este Controller van a funcionar añadiendo /api (en nuestro caso)
    //a la url del servidor

    @Autowired
    private IGeneralService<Usuarios> usuarioService;

    @GetMapping("/usuarios")
    public ResponseEntity<?> findAll() {


        Map<String, Object> response = new HashMap<>();
        List<Usuarios> resultado = new ArrayList<>();

        try {

            resultado =	usuarioService.findAll();

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al realizar la búsqueda de todos los usuarios en la BBDD");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500

        }

        return new ResponseEntity<List<Usuarios>>(resultado,HttpStatus.OK); //200


    }





    @GetMapping("/usuarios-activos")
    public ResponseEntity<?> findAllActive() {


        Map<String, Object> response = new HashMap<>();
        List<Usuarios> resultado = new ArrayList<>();

        try {

            resultado =	usuarioService.findAllActive();

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al realizar la búsqueda de todos los usuarios activos en la BBDD");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500

        }

        return new ResponseEntity<List<Usuarios>>(resultado,HttpStatus.OK); //200

    }


    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        Usuarios resultado = null;

        try {

            resultado =	usuarioService.findById(id);

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al realizar la búsqueda de un usuario con el id: " + id + " en la BBDD");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500

        }

        return new ResponseEntity<Usuarios>(resultado,HttpStatus.OK); //200


    }


    @PostMapping("/usuario")
    public ResponseEntity<?> create(@RequestBody Usuarios usuario) {

        Map<String, Object> response = new HashMap<>();
        Usuarios usuarioNew = null;

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
    public ResponseEntity<?> update(@RequestBody Usuarios usuario) {

        Long id = usuario.getId();
        Map<String, Object> response = new HashMap<>();
        Usuarios usuarioUpdate = null;

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


    @DeleteMapping("/usuario/{id}")
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

    }








}
