package com.ipartek.springboot.backend.elpisito.models.services;

import com.ipartek.springboot.backend.elpisito.models.dao.IUsuarioDAO;
import com.ipartek.springboot.backend.elpisito.models.entity.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements IGeneralService<Usuarios>{

    //Una de las principales caracteristicas de un @Service es que sus atributos suelen ser un su mayoría DAOS
    //@Repository

    @Autowired
    private IUsuarioDAO usuarioDAO;


    @Override
    public List<Usuarios> findAll() {
        return (List<Usuarios>) usuarioDAO.findAll();
    }

    @Override
    public List<Usuarios> findAllActive() {

        //Sustituye con native query methods de hibernate!!!
        return findAll().stream()
                .filter( u -> Integer.valueOf(1).equals(u.getActivo()))
                .toList();
    }

    @Override
    public Usuarios findById(Long id) {
        //El atributo del orElse no tiene porqué devolver un null por ejemplo podría devolver un Usuario
        //si nos interesa

        return usuarioDAO.findById(id).orElse(null);
    }

    @Override
    public Usuarios save(Usuarios usuario) {
        //El usuario que llega aqui, lo hace con el password sin hashear. es decir, tal y como lo escribió
        //en el formulario de registro. ¿Seria aqui el sitio ideal para hashearlo?

        //Hibernate trabaja con le metodo save de dos formas:
        //1) si el usuario del argumento llega con id lo considera update
        //2) si el usuario del argumento llega sin id lo considera create
        Usuarios user =  Usuarios.builder()
                .user(usuario.getUser())
                .password(usuario.getPassword())
                .passOpen(usuario.getPassOpen())
                .activo(usuario.getActivo())
                .build();
        return usuarioDAO.save(user);
    }

    @Override
    public void deleteById(Long id) {
        usuarioDAO.deleteById(id);
    }
}
