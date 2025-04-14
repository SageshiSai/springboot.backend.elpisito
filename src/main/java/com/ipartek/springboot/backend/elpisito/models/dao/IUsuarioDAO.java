package com.ipartek.springboot.backend.elpisito.models.dao;


import com.ipartek.springboot.backend.elpisito.models.entity.Usuarios;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioDAO extends CrudRepository<Usuarios, Long> {


    List<Usuarios> findByActivo(Integer activo);
    Optional<Usuarios> findOneByEmail(String email);
}
