package com.ipartek.springboot.backend.elpisito.models.dao;


import com.ipartek.springboot.backend.elpisito.models.entity.Poblacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPoblacionDAO extends CrudRepository<Poblacion, Long> {

    List<Poblacion> findByActivo(Integer activo);
}
