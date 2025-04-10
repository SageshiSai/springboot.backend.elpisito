package com.ipartek.springboot.backend.elpisito.controller;


import com.ipartek.springboot.backend.elpisito.models.entity.BannerCarousel;
import com.ipartek.springboot.backend.elpisito.models.services.IBannerCarouselService;
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
public class BannerCarouselRestController {


    @Autowired
    private IBannerCarouselService bannerCarouselService;



    @GetMapping("/banners-c")
    public ResponseEntity<?> findAll(){

        Map<String, Object> response = new HashMap<>();
        List<BannerCarousel> resultado = new ArrayList<>();

        try {

            resultado =	bannerCarouselService.findAll();

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al realizar la búsqueda de todos los banners carousel en la BBDD");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500

        }

        return new ResponseEntity<List<BannerCarousel>>(resultado,HttpStatus.OK); //200

    }


    @GetMapping("/banners-c-activos")
    public ResponseEntity<?> findAllActive(){

        Map<String, Object> response = new HashMap<>();
        List<BannerCarousel> resultado = new ArrayList<>();

        try {

            resultado =	bannerCarouselService.findAllActive();

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al realizar la búsqueda de todos los banners carousel activos en la BBDD");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500

        }

        return new ResponseEntity<List<BannerCarousel>>(resultado,HttpStatus.OK); //200


    }

    @GetMapping("/banners-c-activos-tematica/{idTematica}")
    public ResponseEntity<?> findAllActiveAndTematica(@PathVariable Long idTematica){

        Map<String, Object> response = new HashMap<>();
        List<BannerCarousel> resultado = new ArrayList<>();

        try {

            resultado =	bannerCarouselService.findByActivoAndTematica(1, idTematica);

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al realizar la búsqueda de todos los banners carousel activos por temática en la BBDD");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500

        }

        return new ResponseEntity<List<BannerCarousel>>(resultado,HttpStatus.OK); //200


    }

    @GetMapping("/banner-c/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        BannerCarousel resultado = null;

        try {

            resultado =	bannerCarouselService.findById(id);

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al realizar la búsqueda de un banner carousel con el id: " + id + " en la BBDD");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500

        }

        return new ResponseEntity<BannerCarousel>(resultado,HttpStatus.OK); //200

    }


    @PostMapping("/banner-c")
    public ResponseEntity<?> create(@RequestBody BannerCarousel bannerCarousel) {

        Map<String, Object> response = new HashMap<>();
        BannerCarousel bannerCarouselNew = null;

        try {

            bannerCarouselNew = bannerCarouselService.save(bannerCarousel);

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al crear un banner carousel en la BBDD");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500

        }

        response.put("mensaje", "El banner carousel con id: " + bannerCarouselNew.getId() + " ha sido creado con éxito");
        response.put("banner carousel", bannerCarouselNew);
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK); //200


    }


    @PutMapping("/banner-c")
    public ResponseEntity<?> update(@RequestBody BannerCarousel bannerCarousel) {

        Long id = bannerCarousel.getId();
        Map<String, Object> response = new HashMap<>();
        BannerCarousel bannerCarouselUpdate = null;

        try {

            bannerCarouselUpdate = bannerCarouselService.save(bannerCarousel);

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al modificar un banner carousel en la BBDD");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500

        }

        response.put("mensaje", "El banner carousel con id: " + id + " ha sido modificado con éxito");
        response.put("banner carousel", bannerCarouselUpdate);
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK); //200

    }


    @DeleteMapping("/banner-c/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();

        try {

            bannerCarouselService.deleteById(id);

        }catch(DataAccessException e) {

            response.put("mensaje", "Error al eliminar el banner carousel con id: " + id + " en la BBDD");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500

        }

        response.put("mensaje", "El banner carousel con id: " + id + " ha sido eliminado con éxito");
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK); //200

    }



}
