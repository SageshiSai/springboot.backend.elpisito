package com.ipartek.springboot.backend.elpisito.models.dao;

import com.ipartek.springboot.backend.elpisito.models.entity.BannerCarousel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBannerCarouselDAO extends CrudRepository<BannerCarousel, Long> {

    //DERIVED QUERY METHOD
    List<BannerCarousel> findByActivo(Integer activo);
    List<BannerCarousel> findByActivoAndTematica(Integer activo, String tematica);
}
