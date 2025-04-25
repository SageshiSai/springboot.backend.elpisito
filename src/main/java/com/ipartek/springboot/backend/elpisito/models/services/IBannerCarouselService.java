package com.ipartek.springboot.backend.elpisito.models.services;

import java.util.List;

import com.ipartek.springboot.backend.apirest.models.entity.BannerCarousel;


public interface IBannerCarouselService extends IGeneralService<BannerCarousel> {

	
	List<BannerCarousel> findByActivoAndTematica(Integer activo,Long idTematica);
	
}
