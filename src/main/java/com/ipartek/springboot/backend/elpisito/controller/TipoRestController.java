package com.ipartek.springboot.backend.elpisito.controller;


import com.ipartek.springboot.backend.elpisito.models.entity.Tipo;
import com.ipartek.springboot.backend.elpisito.models.services.IGeneralService;
import com.ipartek.springboot.backend.elpisito.models.services.TipoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = ("http://localhost:4200"))
@RestController
@RequestMapping("/api")
public class TipoRestController {

    @Autowired
    private IGeneralService<Tipo> tipoService;

    @GetMapping("/tipos")
    public List<Tipo> findAll(){

        return tipoService.findAll();
    }


    @GetMapping("/tipos-activos")
    public List<Tipo> findAllActive(){

        return tipoService.findAllActive();

    }

    @GetMapping("/tipo/{id}")
    public Tipo findById(@PathVariable Long id) {

        return tipoService.findById(id);
    }


    @PostMapping("/tipo")
    public Tipo create(@RequestBody Tipo tipo) {

        return tipoService.save(tipo);

    }


    @PutMapping("/tipo")
    public Tipo update(@RequestBody Tipo tipo) {

        return tipoService.save(tipo);

    }


    @DeleteMapping("/tipo/{id}")
    public void deleteById(@PathVariable Long id) {

        tipoService.deleteById(id);
    }
}
