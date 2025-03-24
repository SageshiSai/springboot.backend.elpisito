package com.ipartek.springboot.backend.elpisito.models.dao;

import com.ipartek.springboot.backend.elpisito.models.entity.Archivo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IArchivoDAO extends CrudRepository<Archivo, Long> {

    List<Archivo> findByActivo(Integer activo);

    List<Archivo> findByInmuebleIdAndActivo(Long idInmueble, Integer activo);
}
