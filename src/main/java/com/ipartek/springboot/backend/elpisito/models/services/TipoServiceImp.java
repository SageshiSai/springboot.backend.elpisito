package com.ipartek.springboot.backend.elpisito.models.services;

import com.ipartek.springboot.backend.elpisito.models.dao.ITipoDAO;
import com.ipartek.springboot.backend.elpisito.models.entity.Tipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoServiceImp implements IGeneralService<Tipo> {

    @Autowired
    private ITipoDAO tipoDAO;


    @Override
    public List<Tipo> findAll() {

        return (List<Tipo>) tipoDAO.findAll();
    }

    @Override
    public List<Tipo> findAllActive() {

        //Sustituir con native query methods de hibernate!!!
		/*return findAll().stream()
		.filter(  a -> a.getActivo().equals(1))
		.toList();*/

        return tipoDAO.findByActivo(1);

    }

    @Override
    public Tipo findById(Long id) {

        return tipoDAO.findById(id).orElse(null);
    }

    @Override
    public Tipo save(Tipo tipo) {

        return tipoDAO.save(tipo);
    }

    @Override
    public void deleteById(Long id) {

        tipoDAO.deleteById(id);

    }
}