package com.ipartek.springboot.backend.elpisito.controller;

import com.ipartek.springboot.backend.elpisito.models.entity.Provincia;
import com.ipartek.springboot.backend.elpisito.models.services.ProvinciaServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProvinciaRestController {

    Logger logger = LoggerFactory.getLogger(ProvinciaRestController.class);

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
    public ResponseEntity<Provincia> create(@RequestBody Provincia provincia){
        logger.info("Provincia: " + provincia.toString());
        return new ResponseEntity<>(this.provinciaService.save(provincia),HttpStatus.CREATED);
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
