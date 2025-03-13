package com.ipartek.springboot.backend.elpisito.models.dao;

import com.ipartek.springboot.backend.elpisito.models.entity.Inmueble;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IInmuebleDAO extends CrudRepository<Inmueble, Long> {

    //Derived query method
    List<Inmueble> findByActivo(Integer activo);
    List<Inmueble> findByActivoAndPortada(Integer activo, Integer portada);
}
