package com.ipartek.springboot.backend.elpisito.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.apirest.models.dao.IUsuarioDAO;
import com.ipartek.springboot.backend.apirest.models.entity.Usuario;



@Service
public class UsuarioServiceImpl implements IGeneralService<Usuario> {

	//Una de las principales características  de un @Service
	//es que sus atributos suelen ser en su mayoría DAOS (@Repository)
	
	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	//usuarioDAO, automática y mágicamente tiene los métodos IMPLEMENTADOS
	//del CrudRepository 
	//¿Quién ha implementado esos métodos y dónde se han implementado?:
	//la implementación la ha hecho HIBERNATE en el contexto de Spring.
	//Ahí es donde reside toda la lógica de negocio y persistencia SQL
	
	@Autowired
	private PasswordEncoder passwordEncoder; //Utilizamos el @Bean declarado en la clase SecurityConfig
	
	
	@Override
	public List<Usuario> findAll() {
		
		return (List<Usuario>) usuarioDAO.findAll();
	}

	@Override
	public List<Usuario> findAllActive() {
		
		//Sustituir con native query methods de hibernate!!!
		/*return findAll().stream()
		.filter(  a -> a.getActivo().equals(1))
		.toList();*/

		return usuarioDAO.findByActivo(1);
		
	}

	@Override
	public Usuario findById(Long id) {
		//El atributo del orElse no tiene porqué devolver un null
		//por ejemplo podría devolver un Usuario si nos interesa
		return usuarioDAO.findById(id).orElse(null);
		
	}

	@Override
	public Usuario save(Usuario usuario) {
		
		//El usuario que llega aquí lo hace con el password sin hashear
		//es decir, tal y como lo escribió en el formulario de registro
		//¿Sería aquí el sitio ideal para hashearlo? Si
		
		//Hibernate trabaja con el método save de dos formas:
		//1) si el Usuario del argumento llega con id lo considera un update
		//2) si el Usuario del argumento llega sin id lo considera un create
	
		usuario.setPassopen(usuario.getPassword());
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		
		return usuarioDAO.save(usuario);
		
	}

	@Override
	public void deleteById(Long id) {
		
		usuarioDAO.deleteById(id);
		
	}
	
	

}
