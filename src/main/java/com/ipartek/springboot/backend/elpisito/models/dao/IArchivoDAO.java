package com.ipartek.springboot.backend.elpisito.models.dao;

import com.ipartek.springboot.backend.elpisito.models.entity.Archivo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IArchivoDAO extends CrudRepository<Archivo, Long> {
}
