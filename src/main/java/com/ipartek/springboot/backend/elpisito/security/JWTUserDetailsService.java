package com.ipartek.springboot.backend.apirest.securiy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.apirest.models.dao.IUsuarioDAO;





@Service
public class JWTUserDetailsService implements UserDetailsService {
	
	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	/*TENEMOS ANOTADO como @Service*UN OBJETO DE LA CLASE (INTERFACE) UserDetailsService (por polimorfismo)
	 * Esto quiere decir que JWTUserDetailService va a existir en el contexto de Spring y que, por lo tanto,
	 * podemos inyectarlo allá donde lo necesitemos
	 * Esto es lo único que necesita Spring Security para tener un usuario ACTIVO que se suele cargar
	 * generalmente desde una BBDD. ESTO ES MUY IMPORTANTE, porque es así cómo Spring Security
	 * conoce el nombre del usuario ACTIVO: conoce la contraseña, conoce el email....*/
	
	/*El objeto username que recibimos es el que llega de la request del controlador*/

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		
		//Vamos a utilizar email como username
		
		return usuarioDAO.findOneByEmail(email).map(u -> {
			
			//Solo tenemos un ROL
			List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(u.getRol()));
			
			//Vamos a suponer que en la BBDD existe una relación un usuario muchos roles...
			//List<GrantedAuthority> authorities =  List.of(new SimpleGrantedAuthority(u.getRol()));
			
			//User implementa UserDatails
			return new User(u.getEmail(),u.getPassword(),u.getActivo()==1,true,true,true,authorities);		
			
		}).orElseThrow( () -> new UsernameNotFoundException("usuario no encontrado en al BBDD"));	
		
		
	}

}


//EJEMPLO GUÍA PARA UTILIZAR EN LA BBDD EN EL CASO DE QUE DECIDAMOS TENER UNA TABLA DE ROL
//RELACIONADA CON LA TABLA DE USUARIO (UN USUARIO PUEDE TENER MUCHOS ROLES)

/*Set<Rol> roles = usuario.getRoles();
List<GrantedAuthority> authorities = roles.stream()
											
											.map(rol -> new SimpleGrantedAuthority(rol.getName())
											.collect(Collectors.toList())*/
