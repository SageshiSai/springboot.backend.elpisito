package com.ipartek.springboot.backend.elpisito.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ipartek.springboot.backend.apirest.models.entity.BannerCarousel;
import com.ipartek.springboot.backend.apirest.models.entity.Tematica;


@Repository
public interface IBannerCarouselDAO extends CrudRepository<BannerCarousel, Long> {

	//DERIVED QUERY METHOD
	List<BannerCarousel> findByActivo(Integer activo);
	List<BannerCarousel> findByActivoAndTematica(Integer activo, Tematica tematica);
	
}
