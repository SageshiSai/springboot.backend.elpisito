package com.ipartek.springboot.backend.elpisito.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.ipartek.springboot.backend.apirest.models.entity.ImagenCarousel;



@Repository
public interface IImagenCarouselDAO extends CrudRepository<ImagenCarousel, Long> {

}
