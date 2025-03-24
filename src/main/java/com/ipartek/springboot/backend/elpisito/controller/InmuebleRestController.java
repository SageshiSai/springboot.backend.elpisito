package com.ipartek.springboot.backend.elpisito.controller;


import com.ipartek.springboot.backend.elpisito.models.entity.Inmueble;
import com.ipartek.springboot.backend.elpisito.models.services.IInmuebleService;
import com.ipartek.springboot.backend.elpisito.models.services.InmuebleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = ("http://localhost:4200"))
@RestController
@RequestMapping("/api")
public class InmuebleRestController {

    @Autowired
    private IInmuebleService inmuebleService;


    @GetMapping("/inmuebles")
    public List<Inmueble> findAll(){
        return (List<Inmueble>) inmuebleService.findAll();
    }

    @GetMapping("/inmuebles-activos")
    public List<Inmueble> findAllActive(){
        return inmuebleService.findAllActive();
    }

    @GetMapping("/inmuebles-activos-portada")
    public List<Inmueble> findAllPortada(){
        return inmuebleService.findAllPortada();
    }

    @GetMapping("/inmueble/{id}")
    public Inmueble findById(@PathVariable Long id){
        return inmuebleService.findById(id);
    }

    @PostMapping("/inmueble")
    public Inmueble create(@RequestBody Inmueble inmueble){
        return inmuebleService.save(inmueble);
    }

    @PutMapping("/inmueble")
    public Inmueble update(@RequestBody Inmueble inmueble){
        return inmuebleService.save(inmueble);
    }

    @DeleteMapping("/inmueble/{id}")
    public void delete(@PathVariable Long id){
        inmuebleService.deleteById(id);
    }

}
