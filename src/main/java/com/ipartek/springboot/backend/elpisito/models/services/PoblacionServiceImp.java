package com.ipartek.springboot.backend.elpisito.models.services;

import com.ipartek.springboot.backend.elpisito.models.dao.IPoblacionDAO;
import com.ipartek.springboot.backend.elpisito.models.entity.Poblacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoblacionServiceImp implements IGeneralService<Poblacion> {

    @Autowired
    private IPoblacionDAO iPoblacionDAO;


    @Override
    public List<Poblacion> findAll() {
        return (List<Poblacion>) iPoblacionDAO.findAll();
    }

    @Override
    public List<Poblacion> findAllActive() {
        return iPoblacionDAO.findByActivo(1);
    }

    @Override
    public Poblacion findById(Long id) {
        return iPoblacionDAO.findById(id).orElse(null);
    }

    @Override
    public Poblacion save(Poblacion usuario) {
        return iPoblacionDAO.save(usuario);
    }

    @Override
    public void deleteById(Long id) {
        iPoblacionDAO.deleteById(id);
    }
}
