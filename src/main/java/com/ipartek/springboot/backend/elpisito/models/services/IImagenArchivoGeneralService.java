package com.ipartek.springboot.backend.elpisito.models.services;

import java.util.List;

public interface IImagenArchivoGeneralService<T> {
	
	
	//Capa de negocios
	//Creamos unos métodos abstractos basados en el CrudRepository por arquitectura
	//Podemos elegir los que nos interesen (no hace falta crear todos)
	//También podemos añadir métodos personalizados (es decir, métodos que no estén en el CrudRepository)
	List<T> findAll();
	T findById(Long id);
	T save(T t);
	void deleteById(Long id);

}
