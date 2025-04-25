package com.ipartek.springboot.backend.elpisito.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.apirest.models.dao.IInmobiliariaDAO;
import com.ipartek.springboot.backend.apirest.models.entity.Inmobiliaria;


@Service
public class InmobiliariaServiceImpl implements IGeneralService<Inmobiliaria> {

	@Autowired
	private IInmobiliariaDAO inmobiliariaDAO;
	
	
	@Override
	public List<Inmobiliaria> findAll() {
		return (List<Inmobiliaria>) inmobiliariaDAO.findAll();
	}

	@Override
	public List<Inmobiliaria> findAllActive() {
		return inmobiliariaDAO.findByActivo(1);
	}

	@Override
	public Inmobiliaria findById(Long id) {
		return inmobiliariaDAO.findById(id).orElse(null);
	}

	@Override
	public Inmobiliaria save(Inmobiliaria inmobiliaria) {
		return inmobiliariaDAO.save(inmobiliaria);
	}

	@Override
	public void deleteById(Long id) {
		inmobiliariaDAO.deleteById(id);
		
	}

}
