package com.ipartek.springboot.backend.elpisito.controller;


import com.ipartek.springboot.backend.elpisito.models.entity.BannerHorizontal;
import com.ipartek.springboot.backend.elpisito.models.services.BannerHorizontalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = ("http://localhost:4200"))
@RestController
@RequestMapping("/api")
public class BannerHorizontalRestController {

    @Autowired
    private BannerHorizontalServiceImpl bannerHorizontalService;

    @GetMapping("/banners")
    public List<BannerHorizontal> findAll(){
        return bannerHorizontalService.findAll();
    }

    @GetMapping("/banners-h-activos")
    public List<BannerHorizontal> findAllActive(){
        return bannerHorizontalService.findAllActive();
    }

    @GetMapping("/banners-h-activos-home")
    public List<BannerHorizontal> findActivosHome(){
        return bannerHorizontalService.findByActivoAndHome();
    }

    @GetMapping("/banner-h/{id}")
    public BannerHorizontal findById(@PathVariable Long id){
        return bannerHorizontalService.findById(id);
    }

    @PostMapping("/banner-h")
    public BannerHorizontal create(@RequestBody BannerHorizontal bannerHorizontal) {

        return bannerHorizontalService.save(bannerHorizontal);

    }


    @PutMapping("/banner-h")
    public BannerHorizontal update(@RequestBody BannerHorizontal bannerHorizontal) {

        return bannerHorizontalService.save(bannerHorizontal);

    }


    @DeleteMapping("/banner-h/{id}")
    public void deleteById(@PathVariable Long id) {

        bannerHorizontalService.deleteById(id);
    }
}
