package com.ipartek.springboot.backend.elpisito.controller;

import com.ipartek.springboot.backend.elpisito.models.entity.Provincia;
import com.ipartek.springboot.backend.elpisito.models.services.ProvinciaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProvinciaRestController {

    @Autowired
    private ProvinciaServiceImpl provinciaService;

    @GetMapping("/provincias")
    public List<Provincia> findAll(){
        return provinciaService.findAll();
    }

    @GetMapping("/provincias-activos")
    public List<Provincia> findAllActive(){
        return provinciaService.findAllActive();
    }

    @GetMapping("/provincia/{id}")
    public Provincia findById(@PathVariable Long id){
        return provinciaService.findById(id);
    }

    @PostMapping("/provincia")
    public Provincia create(@RequestBody Provincia provincia){
        return provinciaService.save(provincia);
    }

    @PutMapping("/provincia")
    public Provincia update(@RequestBody Provincia provincia){
        return provinciaService.save(provincia);
    }

    @DeleteMapping("/provincia/{id}")
    public void delete(@PathVariable Long id){
        provinciaService.deleteById(id);
    }
}
