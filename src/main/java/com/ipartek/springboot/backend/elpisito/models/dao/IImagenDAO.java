package com.ipartek.springboot.backend.elpisito.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ipartek.springboot.backend.apirest.models.entity.Imagen;


@Repository
public interface IImagenDAO extends CrudRepository<Imagen, Long>{

	//DERIVED QUERY METHOD
	List<Imagen> findByInmuebleId(Long id);
	
	
}
