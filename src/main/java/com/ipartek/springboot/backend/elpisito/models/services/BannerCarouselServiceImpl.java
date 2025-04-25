package com.ipartek.springboot.backend.elpisito.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.apirest.models.dao.IBannerCarouselDAO;
import com.ipartek.springboot.backend.apirest.models.dao.ITematicaDAO;
import com.ipartek.springboot.backend.apirest.models.entity.BannerCarousel;
import com.ipartek.springboot.backend.apirest.models.entity.Tematica;




@Service
public class BannerCarouselServiceImpl implements IBannerCarouselService{

	
	@Autowired
	private IBannerCarouselDAO  bannerCarouselDAO;
	
	@Autowired
	private ITematicaDAO tematicaDAO;
	
	
	@Override
	public List<BannerCarousel> findAll() {
		return (List<BannerCarousel>) bannerCarouselDAO.findAll();
	}

	@Override
	public List<BannerCarousel> findAllActive() {
		
		return bannerCarouselDAO.findByActivo(1);
	}

	@Override
	public BannerCarousel findById(Long id) {
		return bannerCarouselDAO.findById(id).orElse(null);
	}

	@Override
	public BannerCarousel save(BannerCarousel bc) {
		return bannerCarouselDAO.save(bc);
	}

	@Override
	public void deleteById(Long id) {
		bannerCarouselDAO.deleteById(id);
		
	}

	@Override
	public List<BannerCarousel> findByActivoAndTematica(Integer activo, Long idTematica) {
		
		Tematica tematica = tematicaDAO.findById(idTematica).orElse(null);
		return bannerCarouselDAO.findByActivoAndTematica(activo, tematica);
	}

}
