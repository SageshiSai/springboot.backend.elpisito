package com.ipartek.springboot.backend.elpisito.models.dao;

import com.ipartek.springboot.backend.elpisito.models.entity.ImagenCarousel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImagenCarouselDAO extends CrudRepository<ImagenCarousel, Long> {
}
