package com.ipartek.springboot.backend.elpisito.models.services;

import com.ipartek.springboot.backend.elpisito.models.entity.BannerHorizontal;

import java.util.List;

public interface IBannerHorizontalService extends IGeneralService<BannerHorizontal>{


    List<BannerHorizontal> findByActivoAndHome();

}
