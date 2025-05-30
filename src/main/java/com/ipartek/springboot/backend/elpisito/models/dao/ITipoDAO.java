package com.ipartek.springboot.backend.elpisito.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.ipartek.springboot.backend.apirest.models.entity.Tipo;


@Repository
public interface ITipoDAO extends CrudRepository<Tipo, Long> {

	
	//DERIVED QUERY METHOD
	List<Tipo> findByActivo(Integer activo);
}
