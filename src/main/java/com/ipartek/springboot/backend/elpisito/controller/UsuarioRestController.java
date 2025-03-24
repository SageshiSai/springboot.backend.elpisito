package com.ipartek.springboot.backend.elpisito.controller;

import com.ipartek.springboot.backend.elpisito.models.entity.Usuarios;
import com.ipartek.springboot.backend.elpisito.models.services.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = ("http://localhost:4200"))
@RestController
@RequestMapping("/api")
public class UsuarioRestController {

    //@RequestMapping sirve para indicar que las rutas definidas bajo bajo este Controller van a funcionar
    //añadiendo /api (en nuestro caso) a la URL del servidor.

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping("/usuarios")
    public List<Usuarios> findAll(){
        return usuarioService.findAll();
    }

    @GetMapping("/usuarios-activos")
    public List<Usuarios> findAllActive() {
        return usuarioService.findAllActive();
    }

    @GetMapping("/usuario/{id}")
    public Usuarios findById(@PathVariable Long id){
        return usuarioService.findById(id);
    }

    @PostMapping("/usuario")
    public Usuarios create(@RequestBody Usuarios usuarios) {
        return usuarioService.save(usuarios);
    }

    @PutMapping("/usuario")
    public Usuarios update(@RequestBody Usuarios usuarios){
        return usuarioService.save(usuarios);
    }

    @DeleteMapping("/usuario/{id}")
    public void deleteById(@PathVariable Long id){
        usuarioService.deleteById(id);
    }
}
