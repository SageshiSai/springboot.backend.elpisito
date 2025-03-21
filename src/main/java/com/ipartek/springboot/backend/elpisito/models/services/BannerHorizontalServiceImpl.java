package com.ipartek.springboot.backend.elpisito.models.services;

import com.ipartek.springboot.backend.elpisito.models.entity.BannerHorizontal;

import java.util.List;

public class BannerHorizontalServiceImpl implements IGeneralService<BannerHorizontal> {
    @Override
    public List<BannerHorizontal> findAll() {
        return List.of();
    }

    @Override
    public List<BannerHorizontal> findAllActive() {
        return List.of();
    }

    @Override
    public BannerHorizontal findById(Long id) {
        return null;
    }

    @Override
    public BannerHorizontal save(BannerHorizontal bannerHorizontal) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
