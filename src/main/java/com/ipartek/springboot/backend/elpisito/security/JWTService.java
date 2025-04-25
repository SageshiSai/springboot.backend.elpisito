package com.ipartek.springboot.backend.apirest.securiy;


import java.nio.charset.StandardCharsets;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.apirest.models.dao.IUsuarioDAO;
import com.ipartek.springboot.backend.apirest.models.entity.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JWTService {
	
	//En esta clase vamos a definir (parametrizar) varios parámetros como, por ejemplo, tiempo 
	//de validez del token, el nombre secreto, crear un token, verificar si es válido...
	
	public static final long JWT_TOKEN_VALIDITY = 1200; //SEGUNDOS (20 MINUTOS)
	public static final String JWT_SECRET = "utitiotoprioriroorprprphhgggggtt";
	
	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	
	//////////////////////////////////////////////////////////////////
	///MÉTODOS PARA COMPROBAR Y SACAR INFORMACIÓN DEL TOKEN
	//////////////////////////////////////////////////////////////////

	//Este método devolverá los claims del token
	//Recibimos una cosa parecida a esta "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
	//que es un token...
	private Claims getAllClaimsFromToken(String token) {
		
		//Creamos una llave (Key) partiendo del JWT_SECRET
		final Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
		
		//Vamos a realizar el proceso inverso a la creación del token
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token) //parseClaimsJws!!!!!  parseClaimsJwt NOOOO!!!
				.getBody();
		
	}
	
	
	//Este método recibe los claims de un objeto Claims y devuelve objetos del tipo claim ("Strings","Elfos"...)
	public <T> T getClaimsFromToken(String token, Function<Claims,T> claimsResolver){
		
		
		final Claims claims = this.getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
		
	}
	
	
	//Este método devuelve la fecha de expiración del token...
	private Date getTokenExpirationDate(String token) {
		
		return this.getClaimsFromToken(token, Claims::getExpiration );

	}
	
	
	//Este método comprueba si el token está expirado
	
	private boolean isTokenExpired(String token) {
		
		final Date expirationDate = this.getTokenExpirationDate(token);
		return expirationDate.before(new Date());
	}
	
	
	//Este método consigue el nombre de usuario del claim del token (se supone
	//que en proceso de creación del token lo vamos a rellenar)
	public String getTokenUserName(String token) {
		
		return this.getClaimsFromToken(token, Claims::getSubject );
	}
	
	
	//Este método va a comprobar si el token es válido: no caducado + usuario valido
	
	public boolean isTokenValid(String token, UserDetails userDetails) {
		
		final String userNameFromUserDetail = userDetails.getUsername(); //le mandamos el email
		final String userNameFromJWT = this.getTokenUserName(token);
		
		return userNameFromUserDetail.equals(userNameFromJWT) && !this.isTokenExpired(token);
		
		
	}
	

	
	///	//////////////////////////////////////////////////////////////////
	///MÉTODOS PARA CREAR EL TOKEN
	/////////////////////////////////////////////////////////////////////

	//Este método genera "internamente" el token...
	private String getToken(Map<String, Object> claims, String subject) {
		
		//Creamos una llave (Key) partiendo del JWT_SECRET
		final Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
		
		return Jwts
				.builder() //el buider es el creador del token
				.setClaims(claims) //Es un map con todo tipo de datos. Aquí lo seteamos en el token
				.setSubject(subject) //Es el username que en nuestro caso es el email
				.setIssuedAt(new Date(System.currentTimeMillis())) //ahora
				.setExpiration(new Date(System.currentTimeMillis() + (JWT_TOKEN_VALIDITY * 1000))) //momento en el que el token expira
				.signWith(key) //cerramos el proceso con el key que contiene la llave secreta
				.compact(); //convierte en String el objeto Jwts builder
		
	}
	
	//Este método "pasa" el token generado allá donde nos haga falta...
	//Recordemos que en el objeto userDetails va la información del usuario: email, password, rol (o roles...)
	public String generateToken(UserDetails userDetails) {
		
		Usuario elUsuario =  usuarioDAO.findOneByEmail(userDetails.getUsername()).map(u -> u).orElseThrow( () -> new UsernameNotFoundException("Usuario no encontrado en la BBDD"));
		
		//Es aquí donde podríamos pasar datos en el objeto Claims del token que estamos creando
		final Map<String, Object> claims = new HashMap<>();
		claims.put("ROLES", userDetails.getAuthorities().toString());//ROLE_ADMIN, ROLE_USER
		claims.put("usuario", elUsuario.getUser());
		//claims.put("felicitación", "Feliz navidad!!!");//Podemos enviar en el token cualquier información siempre que no sea confidencial
		claims.put("id", elUsuario.getId());
		
		//Retornamos el token
		return this.getToken(claims,userDetails.getUsername());
		
	
	}

}
