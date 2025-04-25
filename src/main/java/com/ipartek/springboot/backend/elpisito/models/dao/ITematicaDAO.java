package com.ipartek.springboot.backend.elpisito.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.ipartek.springboot.backend.apirest.models.entity.Tematica;

@Repository
public interface ITematicaDAO extends CrudRepository<Tematica, Long> {

	//DERIVED QUERY METHOD
	List<Tematica> findByActivo(Integer activo);
}
