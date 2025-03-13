package com.ipartek.springboot.backend.elpisito.models.services;


import com.ipartek.springboot.backend.elpisito.models.dao.IProvinciaDAO;
import com.ipartek.springboot.backend.elpisito.models.entity.Provincia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinciaServiceImpl implements IGeneralService<Provincia> {

    @Autowired
    private IProvinciaDAO provinciaDAO;

    @Override
    public List<Provincia> findAll() {
        return (List<Provincia>) provinciaDAO.findAll();
    }

    @Override
    public List<Provincia> findAllActive() {
        return provinciaDAO.findByActivo(1);
    }

    @Override
    public Provincia findById(Long id) {
        return provinciaDAO.findById(id).orElse(null);
    }

    @Override
    public Provincia save(Provincia usuario) {
        return provinciaDAO.save(usuario);
    }

    @Override
    public void deleteById(Long id) {
        provinciaDAO.deleteById(id);
    }
}
