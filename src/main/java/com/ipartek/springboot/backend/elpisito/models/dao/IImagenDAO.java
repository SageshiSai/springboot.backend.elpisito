package com.ipartek.springboot.backend.elpisito.models.dao;


import com.ipartek.springboot.backend.elpisito.models.entity.Imagen;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IImagenDAO extends CrudRepository<Imagen, Long> {

    List<Imagen> findByActivo(Integer activo);
}
