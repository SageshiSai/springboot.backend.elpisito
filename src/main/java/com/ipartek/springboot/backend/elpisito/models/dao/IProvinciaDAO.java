package com.ipartek.springboot.backend.elpisito.models.dao;


import com.ipartek.springboot.backend.elpisito.models.entity.Provincia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProvinciaDAO extends CrudRepository<Provincia, Long> {

    List<Provincia> findByActivo(Integer activo);
}
