package com.ipartek.springboot.backend.elpisito.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.apirest.models.dao.IArchivoDAO;
import com.ipartek.springboot.backend.apirest.models.entity.Archivo;




@Service
public class ArchivoServiceImpl implements  IImagenArchivoGeneralService<Archivo> {

	@Autowired
	private IArchivoDAO archivoDAO;
	
	@Override
	public List<Archivo> findAll() {
		
		return (List<Archivo>) archivoDAO.findAll();
		
	}



	@Override
	public Archivo findById(Long id) {
		
		return archivoDAO.findById(id).orElse(null);
		
	}

	@Override
	public Archivo save(Archivo archivo) {
		
		return archivoDAO.save(archivo);
		
	}

	@Override
	public void deleteById(Long id) {
		
		archivoDAO.deleteById(id);
		
	}
	
	
	

}
