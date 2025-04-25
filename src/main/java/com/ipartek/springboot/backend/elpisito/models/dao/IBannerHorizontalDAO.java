package com.ipartek.springboot.backend.elpisito.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ipartek.springboot.backend.apirest.models.entity.BannerHorizontal;


@Repository
public interface IBannerHorizontalDAO extends CrudRepository<BannerHorizontal, Long> {

	
	//DERIVED QUERY METHOD
	List<BannerHorizontal> findByActivo(Integer activo);

	
	
	
}
