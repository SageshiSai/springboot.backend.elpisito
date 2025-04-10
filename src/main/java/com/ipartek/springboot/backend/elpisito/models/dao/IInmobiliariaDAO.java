package com.ipartek.springboot.backend.elpisito.models.dao;

import com.ipartek.springboot.backend.elpisito.models.entity.Inmobiliaria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IInmobiliariaDAO extends CrudRepository<Inmobiliaria, Long> {

    //DERIVED QUERY METHOD
    List<Inmobiliaria> findByActivo(Integer activo);
}
