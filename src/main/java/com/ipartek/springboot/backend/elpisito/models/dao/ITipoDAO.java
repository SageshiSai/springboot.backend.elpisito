package com.ipartek.springboot.backend.elpisito.models.dao;


import com.ipartek.springboot.backend.elpisito.models.entity.Tipo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITipoDAO extends CrudRepository<Tipo, Long> {

    List<Tipo> findByActivo(Integer activo);
}
