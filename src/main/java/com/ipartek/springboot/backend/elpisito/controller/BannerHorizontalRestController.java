package com.ipartek.springboot.backend.elpisito.controller;


import com.ipartek.springboot.backend.elpisito.models.entity.BannerHorizontal;
import com.ipartek.springboot.backend.elpisito.models.services.BannerHorizontalServiceImpl;
import com.ipartek.springboot.backend.elpisito.models.services.IBannerHorizontalService;
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
public class BannerHorizontalRestController {

    @Autowired
    private IBannerHorizontalService bannerHorizontalService;



    @GetMapping("/banners-h")
    public ResponseEntity<?> findAll(){

        Map<String, Object> response = new HashMap<>();
        List<BannerHorizontal> resultado = new ArrayList<>();

        try {

            resultado =	bannerHorizontalService.findAll();

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al realizar la búsqueda de todos los banners horizontales en la BBDD");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500

        }

        return new ResponseEntity<List<BannerHorizontal>>(resultado,HttpStatus.OK); //200

    }


    @GetMapping("/banners-h-activos")
    public ResponseEntity<?> findAllActive(){

        Map<String, Object> response = new HashMap<>();
        List<BannerHorizontal> resultado = new ArrayList<>();

        try {

            resultado =	bannerHorizontalService.findAllActive();

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al realizar la búsqueda de todos los banners horizontales activos en la BBDD");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500

        }

        return new ResponseEntity<List<BannerHorizontal>>(resultado,HttpStatus.OK); //200


    }

    @GetMapping("/banners-h-activos-home")
    public ResponseEntity<?> findAllActiveAndHome(){


        Map<String, Object> response = new HashMap<>();
        List<BannerHorizontal> resultado = new ArrayList<>();

        try {

            resultado =	bannerHorizontalService.findByActivoAndHome();

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al realizar la búsqueda de todos los banners horizontales activos en la Home en la BBDD");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500

        }

        return new ResponseEntity<List<BannerHorizontal>>(resultado,HttpStatus.OK); //200


    }

    @GetMapping("/banner-h/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        BannerHorizontal resultado = null;

        try {

            resultado =	bannerHorizontalService.findById(id);

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al realizar la búsqueda de un banner horizontal con el id: " + id + " en la BBDD");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500

        }

        return new ResponseEntity<BannerHorizontal>(resultado,HttpStatus.OK); //200

    }


    @PostMapping("/banner-h")
    public ResponseEntity<?> create(@RequestBody BannerHorizontal bannerHorizontal) {

        Map<String, Object> response = new HashMap<>();
        BannerHorizontal bannerHorizontalNew = null;

        try {

            bannerHorizontalNew = bannerHorizontalService.save(bannerHorizontal);

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al crear un banner horizontal en la BBDD");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500

        }

        response.put("mensaje", "El banner horizontal con id: " + bannerHorizontalNew.getId() + " ha sido creado con éxito");
        response.put("banner horizontal", bannerHorizontalNew);
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK); //200

    }


    @PutMapping("/banner-h")
    public ResponseEntity<?> update(@RequestBody BannerHorizontal bannerHorizontal) {

        Long id = bannerHorizontal.getId();
        Map<String, Object> response = new HashMap<>();
        BannerHorizontal bannerHorizontalUpdate = null;

        try {

            bannerHorizontalUpdate = bannerHorizontalService.save(bannerHorizontal);

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al modificar un banner horizontal en la BBDD");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500

        }

        response.put("mensaje", "El banner horizontal con id: " + id + " ha sido modificado con éxito");
        response.put("banner horizontal", bannerHorizontalUpdate);
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK); //200


    }


    @DeleteMapping("/banner-h/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {


        Map<String, Object> response = new HashMap<>();

        try {

            bannerHorizontalService.deleteById(id);

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al eliminar el banner horizontal con id: " + id + " en la BBDD");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500

        }

        response.put("mensaje", "El banner horizontal con id: " + id + " ha sido eliminado con éxito");
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK); //200

    }






}
