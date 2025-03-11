package com.ipartek.springboot.backend.elpisito.models.services;

import com.ipartek.springboot.backend.elpisito.models.dao.ITipoDAO;
import com.ipartek.springboot.backend.elpisito.models.entity.Tipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoServiceImp implements IGeneralService<Tipo>{

    @Autowired
    private ITipoDAO iTipoDAO;

    @Override
    public List<Tipo> findAll() {
        return (List<Tipo>) iTipoDAO.findAll();
    }

    @Override
    public List<Tipo> findAllActive() {
        return findAll().stream()
                .filter( t -> t.getActivo().equals(1))
                .toList();
    }

    @Override
    public Tipo findById(Long id) {
        return iTipoDAO.findById(id).orElse(null);
    }

    @Override
    public Tipo save(Tipo usuario) {
        return iTipoDAO.save(usuario);
    }


    @Override
    public void deleteById(Long id) {
        iTipoDAO.deleteById(id);
    }
}
