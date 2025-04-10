package com.ipartek.springboot.backend.elpisito.models.services;

import com.ipartek.springboot.backend.elpisito.models.dao.ITematicaDAO;
import com.ipartek.springboot.backend.elpisito.models.entity.Tematica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TematicaServiceImpl implements IGeneralService<Tematica> {

    @Autowired
    private ITematicaDAO tematicaDAO;


    @Override
    public List<Tematica> findAll() {
        return (List<Tematica>) tematicaDAO.findAll();
    }

    @Override
    public List<Tematica> findAllActive() {
        return tematicaDAO.findByActivo(1);
    }

    @Override
    public Tematica findById(Long id) {
        return tematicaDAO.findById(id).orElse(null);
    }

    @Override
    public Tematica save(Tematica tematica) {
        return tematicaDAO.save(tematica);
    }

    @Override
    public void deleteById(Long id) {

        tematicaDAO.deleteById(id);
    }




}