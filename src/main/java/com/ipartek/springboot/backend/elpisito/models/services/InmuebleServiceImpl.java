package com.ipartek.springboot.backend.elpisito.models.services;

import com.ipartek.springboot.backend.elpisito.models.dao.IInmuebleDAO;
import com.ipartek.springboot.backend.elpisito.models.entity.Inmueble;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InmuebleServiceImpl implements IInmuebleService{

    @Autowired
    private IInmuebleDAO inmuebleDAO;

    @Override
    public List<Inmueble> findAll() {
        return (List<Inmueble>) inmuebleDAO.findAll();
    }

    @Override
    public List<Inmueble> findAllPortada() {
        return inmuebleDAO.findByActivoAndPortada(1,1);
    }

    @Override
    public List<Inmueble> findAllActive() {
        return inmuebleDAO.findByActivo(1);
    }

    @Override
    public Inmueble findById(Long id) {
        return inmuebleDAO.findById(id).orElse(null);
    }

    @Override
    public Inmueble save(Inmueble usuario) {

        return inmuebleDAO.save(usuario);
    }

    @Override
    public void deleteById(Long id) {
        inmuebleDAO.deleteById(id);
    }
}
