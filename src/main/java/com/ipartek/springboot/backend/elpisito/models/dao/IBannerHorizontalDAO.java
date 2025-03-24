package com.ipartek.springboot.backend.elpisito.models.dao;

import com.ipartek.springboot.backend.elpisito.models.entity.BannerHorizontal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBannerHorizontalDAO extends CrudRepository<BannerHorizontal, Long> {

    List<BannerHorizontal> findByActivo(Integer activo);
    List<BannerHorizontal> findByActivoAndHome(Integer activo, Integer home);
}
