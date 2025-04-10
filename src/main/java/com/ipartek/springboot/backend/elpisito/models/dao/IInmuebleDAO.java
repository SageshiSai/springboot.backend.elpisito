package com.ipartek.springboot.backend.elpisito.models.dao;

import com.ipartek.springboot.backend.elpisito.models.entity.Inmobiliaria;
import com.ipartek.springboot.backend.elpisito.models.entity.Inmueble;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IInmuebleDAO extends CrudRepository<Inmueble, Long> {

    //DERIVED QUERY METHOD
    List<Inmueble> findByActivo(Integer activo);
    List<Inmueble> findByActivoAndPortada(Integer activo, Integer portada);
    List<Inmueble> findByInmobiliaria(Inmobiliaria inmobiliaria);
}
