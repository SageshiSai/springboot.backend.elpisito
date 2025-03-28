package com.ipartek.springboot.backend.elpisito.models.services;

import com.ipartek.springboot.backend.elpisito.models.dao.IBannerCarouselDAO;
import com.ipartek.springboot.backend.elpisito.models.entity.BannerCarousel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerCarouselServiceImpl implements IBannerCarouselService{


    @Autowired
    private IBannerCarouselDAO bannerCarouselDAO;


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
    public List<BannerCarousel> findByActivoAndTematica(Integer activo, String tematica) {
        return bannerCarouselDAO.findByActivoAndTematica(activo, tematica);
    }

}
