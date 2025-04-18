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
        //Sustituir con native query methods de hibernate!!!
		/*return findAll().stream()
		.filter(  a -> a.getActivo().equals(1))
		.toList();*/

        return imagenDAO.findByActivo(1);
    }

    @Override
    public Imagen findById(Long id) {
        return imagenDAO.findById(id).orElse(null);
    }

    @Override
    public Imagen save(Imagen imagen) {

        return imagenDAO.save(imagen);
    }

    @Override
    public void deleteById(Long id) {
        imagenDAO.deleteById(id);

    }

}
