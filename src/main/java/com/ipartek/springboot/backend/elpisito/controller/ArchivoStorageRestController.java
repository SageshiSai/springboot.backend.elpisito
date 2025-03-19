package com.ipartek.springboot.backend.elpisito.controller;


import com.ipartek.springboot.backend.elpisito.models.entity.Archivo;
import com.ipartek.springboot.backend.elpisito.models.services.ArchivoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ArchivoRestController {
    
    @Autowired
    private ArchivoServiceImpl archivoService;


    @GetMapping("/archivos")
    public List<Archivo> findAll(){
        return archivoService.findAll();
    }

    @GetMapping("/archivos-activos")
    public List<Archivo> findAllActive(){
        return archivoService.findAllActive();
    }

    @GetMapping("/archivo/{id}")
    public Archivo findById(@PathVariable Long id){
        return archivoService.findById(id);
    }

    @PostMapping("/archivo")
    public Archivo create(@RequestBody Archivo archivo){
        return archivoService.save(archivo);
    }

    @PutMapping("/archivo")
    public Archivo update(@RequestBody Archivo archivo){
        return archivoService.save(archivo);
    }

    @DeleteMapping("/archivo/{id}")
    public void delete(@PathVariable Long id){
        archivoService.deleteById(id);
    }
}
