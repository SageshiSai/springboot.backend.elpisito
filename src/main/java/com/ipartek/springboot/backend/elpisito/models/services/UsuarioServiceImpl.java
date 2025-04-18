package com.ipartek.springboot.backend.elpisito.models.services;

import com.ipartek.springboot.backend.elpisito.models.dao.IUsuarioDAO;
import com.ipartek.springboot.backend.elpisito.models.entity.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements IGeneralService<Usuarios>{

    //Una de las principales caracteristicas de un @Service es que sus atributos suelen ser un su mayoría DAOS
    //@Repository

    @Autowired
    private IUsuarioDAO usuarioDAO;

    @Autowired
    private PasswordEncoder passwordEncoder; //Utilizamos el bean declarado en la clase SecurityConfig


    @Override
    public List<Usuarios> findAll() {
        return (List<Usuarios>) usuarioDAO.findAll();
    }

    @Override
    public List<Usuarios> findAllActive() {

        //Sustituye con native query methods de hibernate!!!
        return usuarioDAO.findByActivo(1);
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
        if(usuario.getId() != null){
            Usuarios usuarioActualBBDD =  usuarioDAO.findById(usuario.getId()).orElse(null);
            if(usuarioActualBBDD != null){//Si el usuario recibido existe...
                //Si el password es null o está vacío
                if(usuario.getPassword() == null || usuario.getPassword().isEmpty()){
                    usuario.setPassword(usuarioActualBBDD.getPassword());
                } else{
                    //Si el password llega cambiado
                    usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
                }
            } else {//Es un create
                usuario.setPassOpen(usuario.getPassword());
                usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            }
        }

        return usuarioDAO.save(usuario);
    }

    @Override
    public void deleteById(Long id) {
        usuarioDAO.deleteById(id);
    }
}
