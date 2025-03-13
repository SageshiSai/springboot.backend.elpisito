package com.ipartek.springboot.backend.elpisito.models.services;

import com.ipartek.springboot.backend.elpisito.models.dao.IImagenDAO;
import com.ipartek.springboot.backend.elpisito.models.entity.Imagen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagenServiceImpl implements IGeneralService<Imagen> {

    @Autowired
    private IImagenDAO imagenDAO;

    @Override
    public List<Imagen> findAll() {
        return (List<Imagen>) imagenDAO.findAll();
    }

    @Override
    public List<Imagen> findAllActive() {
        return imagenDAO.findByActivo(1);
    }

    @Override
    public Imagen findById(Long id) {
        return imagenDAO.findById(id).orElse(null);
    }

    @Override
    public Imagen save(Imagen usuario) {
        return imagenDAO.save(usuario);
    }

    @Override
    public void deleteById(Long id) {
        imagenDAO.deleteById(id);
    }
}
