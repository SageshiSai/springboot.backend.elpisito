package com.ipartek.springboot.backend.elpisito.models.services;

import com.ipartek.springboot.backend.elpisito.models.entity.Inmueble;

import java.util.List;

public interface IInmuebleService extends IGeneralService<Inmueble> {

    List<Inmueble> findAllPortada();

}
