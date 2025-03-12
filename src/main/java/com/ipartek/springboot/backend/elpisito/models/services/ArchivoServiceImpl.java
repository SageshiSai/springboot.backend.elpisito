package com.ipartek.springboot.backend.elpisito.models.services;

import com.ipartek.springboot.backend.elpisito.models.dao.IArchivoDAO;
import com.ipartek.springboot.backend.elpisito.models.entity.Archivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchivoServiceImpl implements IGeneralService<Archivo> {

    @Autowired
    private IArchivoDAO archivoDAO;

    @Override
    public List<Archivo> findAll() {
        return (List<Archivo>) archivoDAO.findAll();
    }

    @Override
    public List<Archivo> findAllActive() {
        return findAll().stream()
                .filter( a -> Integer.valueOf(1).equals(a.getActivo()))
                .toList();
    }

    @Override
    public Archivo findById(Long id) {
        return archivoDAO.findById(id).orElse(null);
    }

    @Override
    public Archivo save(Archivo usuario) {
        return archivoDAO.save(usuario);
    }

    @Override
    public void deleteById(Long id) {
        archivoDAO.deleteById(id);
    }
}
