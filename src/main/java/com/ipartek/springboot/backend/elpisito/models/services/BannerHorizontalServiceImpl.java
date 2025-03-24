package com.ipartek.springboot.backend.elpisito.models.services;

import com.ipartek.springboot.backend.elpisito.models.dao.IBannerHorizontalDAO;
import com.ipartek.springboot.backend.elpisito.models.entity.BannerHorizontal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerHorizontalServiceImpl implements IGeneralService<BannerHorizontal> {

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
    public BannerHorizontal save(BannerHorizontal bannerHorizontal) {
        return bannerHorizontalDAO.save(bannerHorizontal);
    }

    @Override
    public void deleteById(Long id) {
        bannerHorizontalDAO.deleteById(id);
    }

    public List<BannerHorizontal> findByActivoAndHome(){
        return bannerHorizontalDAO.findByActivoAndHome(1,1);
    }
}
