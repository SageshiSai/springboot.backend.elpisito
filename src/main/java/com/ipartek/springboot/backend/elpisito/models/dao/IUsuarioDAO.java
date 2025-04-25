package com.ipartek.springboot.backend.elpisito.models.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.ipartek.springboot.backend.apirest.models.entity.Usuario;



@Repository
public interface IUsuarioDAO extends CrudRepository<Usuario,Long> {
	
	//Capa de persistencia
	
	//DERIVED QUERY METHOD
	List<Usuario> findByActivo(Integer activo);
	Optional<Usuario> findOneByEmail(String email);

}
