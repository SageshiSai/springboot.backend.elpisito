package com.ipartek.springboot.backend.elpisito.models.dao;

import com.ipartek.springboot.backend.elpisito.models.entity.Tematica;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITematicaDAO extends CrudRepository<Tematica, Long> {

    //DERIVED QUERY METHOD
    List<Tematica> findByActivo(Integer activo);
}
