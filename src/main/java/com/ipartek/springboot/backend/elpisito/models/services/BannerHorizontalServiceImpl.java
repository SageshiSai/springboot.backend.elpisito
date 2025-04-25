package com.ipartek.springboot.backend.elpisito.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.apirest.models.dao.IBannerHorizontalDAO;

import com.ipartek.springboot.backend.apirest.models.entity.BannerHorizontal;


@Service
public class BannerHorizontalServiceImpl implements IGeneralService<BannerHorizontal>{

	@Autowired
	private IBannerHorizontalDAO bannerHorizontalDAO;
	
	
	@Override
	public List<BannerHorizontal> findAll() {
		return (List<BannerHorizontal>) bannerHorizontalDAO.findAll();
	}

	@Override
	public List<BannerHorizontal> findAllActive() {
		return bannerHorizontalDAO.findByActivo(1);
	}

	@Override
	public BannerHorizontal findById(Long id) {
		
		return bannerHorizontalDAO.findById(id).orElse(null);
		
	}

	@Override
	public BannerHorizontal save(BannerHorizontal bh) {
		
		return bannerHorizontalDAO.save(bh);
		
	}

	@Override
	public void deleteById(Long id) {
		
		bannerHorizontalDAO.deleteById(id);
	}



	
	
	
	
}
