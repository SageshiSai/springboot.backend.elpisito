package com.ipartek.springboot.backend.elpisito.controller;


import com.ipartek.springboot.backend.elpisito.models.entity.Imagen;
import com.ipartek.springboot.backend.elpisito.models.services.ImagenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ImagenRestController {

    @Autowired
    private ImagenServiceImpl imagenService;


    @GetMapping("/imagenes")
    public List<Imagen> findAll(){
        return imagenService.findAll();
    }

    @GetMapping("/imagenes-activos")
    public List<Imagen> findAllActive(){
        return imagenService.findAllActive();
    }

    @GetMapping("/imagen/{id}")
    public Imagen findById(@PathVariable Long id){
        return imagenService.findById(id);
    }

    @PostMapping("/imagen")
    public Imagen create(@RequestBody Imagen imagen){
        return imagenService.save(imagen);
    }

    @PutMapping("/imagen")
    public Imagen update(@RequestBody Imagen imagen){
        return imagenService.save(imagen);
    }

    @DeleteMapping("/imagen/{id}")
    public void delete(@PathVariable Long id){
        imagenService.deleteById(id);
    }

}
