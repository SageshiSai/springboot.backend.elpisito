package com.ipartek.springboot.backend.elpisito.security;

import com.ipartek.springboot.backend.elpisito.models.dao.IUsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JWTUserDetailsService implements UserDetailsService {
    @Autowired
    private IUsuarioDAO usuarioDAO;
    /*Tenemos anotado un objeto de la clase (INTERFACE) UserDetailsService (por polimorfismo)
    * @Service Esto quiere decir que JWTUserDetailsService va a existir en el contexto de Spring
    * y que, por lo tanto, podemos inyectarlo allá donde lo necesitemos
    * Esto es lo único que necesita Spring Security para tener un usuario ACTIVO que se suele cargar
    * generalmente desde una BBDD. ESTO ES MUY IMPORTANTE, porque es así cómo Spirng
    * conoce el nombre del usuario ACTIVO: conoce la contraseña, conoce el email...*/

    /*El objeto username que recibimos es el que llega de la request del controlador*/

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        //Vamos a utilizar email como username

        return usuarioDAO.findOneByEmail(email).map(u -> {
            //Solo tenemos un ROL
            var authorities = List.of(new SimpleGrantedAuthority(u.getRol()));
            //Vamos a suponer que en la BBDD existe una relación un usuario muchos roles....
            //List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(u.getRol()));

            //User implementa UserDetails
            return new User(u.getEmail(), u.getPassword(),u.getActivo()==1,true,true,true, authorities);
        }).orElseThrow( () -> new UsernameNotFoundException("Usuario no encontrado en la base de datos"));


    }
}


//EJEMPLO GUIA PARA UTILIZAR EN LA BBDD EN EL CASO DE QUE DECIDAMOS TENER UNA TABLA DE ROLL
//RELACIONADA CON LA TABLA DE USUARIO (UN USUARIO PUEDE TENER MUCHOS ROLES)

/*Set<Rol> roles = usuario.getRoles();
List<GrantedAuthority> authorities = roles.stream()
        .map(roles -> new SimpleGrantedAuthority(rol.getName()))
        .collect(Collectors.toList());*/