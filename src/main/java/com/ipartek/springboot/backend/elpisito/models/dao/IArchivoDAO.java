package com.ipartek.springboot.backend.elpisito.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ipartek.springboot.backend.apirest.models.entity.Archivo;




@Repository
public interface IArchivoDAO extends CrudRepository<Archivo, Long>{

	//DERIVED QUERY METHOD
	List<Archivo> findByInmuebleId(Long id);

}
