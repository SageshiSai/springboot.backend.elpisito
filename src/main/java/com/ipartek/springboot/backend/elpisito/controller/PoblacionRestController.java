package com.ipartek.springboot.backend.elpisito.controller;

import com.ipartek.springboot.backend.elpisito.models.entity.Poblacion;
import com.ipartek.springboot.backend.elpisito.models.services.PoblacionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PoblacionRestController {

    @Autowired
    private PoblacionServiceImp poblacionServiceImp;

    @GetMapping("/poblaciones")
    public List<Poblacion> findAll(){
        return poblacionServiceImp.findAll();
    }

    @GetMapping("/poblaciones-activos")
    public List<Poblacion> findAllActive(){
        return poblacionServiceImp.findAllActive();
    }

    @GetMapping("/poblacion/{id}")
    public Poblacion findById(@PathVariable Long id){
        return poblacionServiceImp.findById(id);
    }

    @PostMapping("/poblacion")
    public Poblacion create(@RequestBody Poblacion poblacion){
        return poblacionServiceImp.save(poblacion);
    }

    @PutMapping("/poblacion")
    public Poblacion update(@RequestBody Poblacion poblacion){
        return poblacionServiceImp.save(poblacion);
    }

    @DeleteMapping("/poblacion/{id}")
    public void delete(@PathVariable Long id){
        poblacionServiceImp.deleteById(id);
    }
}
