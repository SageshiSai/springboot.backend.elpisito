package com.ipartek.springboot.backend.elpisito.models.dao;

import com.ipartek.springboot.backend.elpisito.models.entity.ImagenBanner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IImagenBannerDAO extends CrudRepository<ImagenBanner, Long> {

    List<ImagenBanner> findByBannerHorizontalId(Long idBanner);
}
