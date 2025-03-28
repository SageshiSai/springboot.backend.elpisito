package com.ipartek.springboot.backend.elpisito.models.services;

import com.ipartek.springboot.backend.elpisito.models.entity.BannerCarousel;

import java.util.List;

public interface IBannerCarouselService extends IGeneralService<BannerCarousel> {


    List<BannerCarousel> findByActivoAndTematica(Integer activo, String tematica);

}