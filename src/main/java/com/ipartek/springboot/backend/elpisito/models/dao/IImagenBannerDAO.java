package com.ipartek.springboot.backend.elpisito.models.dao;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.ipartek.springboot.backend.apirest.models.entity.ImagenBanner;


@Repository
public interface IImagenBannerDAO extends CrudRepository<ImagenBanner, Long> {

	
	
}
