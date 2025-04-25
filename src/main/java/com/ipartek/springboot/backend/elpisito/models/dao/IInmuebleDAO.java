package com.ipartek.springboot.backend.elpisito.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ipartek.springboot.backend.apirest.models.entity.Inmobiliaria;
import com.ipartek.springboot.backend.apirest.models.entity.Inmueble;

@Repository
public interface IInmuebleDAO extends CrudRepository<Inmueble, Long> {

	//DERIVED QUERY METHOD
	List<Inmueble> findByActivo(Integer activo);
	List<Inmueble> findByActivoAndPortada(Integer activo, Integer portada);
	List<Inmueble> findByInmobiliaria(Inmobiliaria inmobiliaria);
	
}
