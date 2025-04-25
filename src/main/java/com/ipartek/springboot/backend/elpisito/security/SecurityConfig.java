package com.ipartek.springboot.backend.apirest.securiy;


import java.util.List;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {
	
	
	@Bean 
	SecurityFilterChain securityFilterChain(HttpSecurity http, JWTValidationFilter jwtValidationFilter) throws Exception{
		
		//En una API REST no existen sesiones. No existen los estados
		
		http.sessionManagement(sess-> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		
		http
			.authorizeHttpRequests(auth -> {
				
				//Podemos hacer infinitas combinaciones utilizando el SELECTOR y el AUTORIZADOR
				//Podemos jugar con el SELECTOR : anyRequest(), requestMatchers()...
				//Podemos jugar con el AUTORIZADOR: permitAll(), authenticated(), hasRole(), hasAnyRole()
				//Haciendo combinaciones entre un SELECTOR y un AUTORIZADOR crearemos autorizaciones
				//a la carta
				//Es obligatorio para Spring Security que en la BBDD esté anotado como "ROLE_LOQUESEA" y aquí "LOQUESEA"
				
				/*auth.requestMatchers("/api/provincias").hasRole("USER");
				auth.requestMatchers("/api/tipos").hasAnyRole("ADMIN_PLUS","ADMIN");
				auth.requestMatchers("/api/**").authenticated();*/   
				
				auth.requestMatchers(HttpMethod.POST,"/api/inmueble").hasAnyRole("ADMIN_PLUS","ADMIN","INMOBILIARIA");
				auth.requestMatchers(HttpMethod.PUT,"/api/inmueble").hasAnyRole("ADMIN_PLUS","ADMIN");
				auth.requestMatchers(HttpMethod.GET,"/api/inmuebles").hasAnyRole("ADMIN_PLUS","ADMIN");
				auth.requestMatchers(HttpMethod.GET,"/api/inmuebles-inmobiliaria/**").hasAnyRole("ADMIN_PLUS","ADMIN");
				
				auth.requestMatchers(HttpMethod.POST,"/api/poblacion").hasAnyRole("ADMIN_PLUS","ADMIN");
				auth.requestMatchers(HttpMethod.PUT,"/api/poblacion").hasAnyRole("ADMIN_PLUS","ADMIN");
				auth.requestMatchers(HttpMethod.GET,"/api/poblaciones").hasAnyRole("ADMIN_PLUS","ADMIN");
				auth.requestMatchers(HttpMethod.GET,"/api/poblaciones-activas").hasAnyRole("ADMIN_PLUS","ADMIN");
		
				
				auth.requestMatchers(HttpMethod.POST,"/api/provincia").hasAnyRole("ADMIN_PLUS","ADMIN");
				auth.requestMatchers(HttpMethod.PUT,"/api/provincia").hasAnyRole("ADMIN_PLUS","ADMIN");
				auth.requestMatchers(HttpMethod.GET,"/api/provincias").hasAnyRole("ADMIN_PLUS","ADMIN");
				auth.requestMatchers(HttpMethod.GET,"/api/provincias-activas").hasAnyRole("ADMIN_PLUS","ADMIN");
	
				
				auth.requestMatchers(HttpMethod.POST,"/api/tipo").hasAnyRole("ADMIN_PLUS","ADMIN");
				auth.requestMatchers(HttpMethod.PUT,"/api/tipo").hasAnyRole("ADMIN_PLUS","ADMIN");
				auth.requestMatchers(HttpMethod.GET,"/api/tipos").hasAnyRole("ADMIN_PLUS","ADMIN");
				auth.requestMatchers(HttpMethod.GET,"/api/tipos-activos").hasAnyRole("ADMIN_PLUS","ADMIN");

				
				//el POST no existe porque no podemos restringir que una persona se de de alta...
				auth.requestMatchers(HttpMethod.PUT,"/api/usuario").hasRole("ADMIN_PLUS");
				auth.requestMatchers(HttpMethod.GET,"/api/usuario/**").hasRole("ADMIN_PLUS");
				auth.requestMatchers(HttpMethod.GET,"/api/usuarios").hasRole("ADMIN_PLUS");
				auth.requestMatchers(HttpMethod.GET,"/api/usuarios-activos").hasRole("ADMIN_PLUS");
				
				auth.requestMatchers(HttpMethod.POST,"/api/tematica").hasAnyRole("ADMIN_PLUS","ADMIN");
				auth.requestMatchers(HttpMethod.PUT,"/api/tematica").hasAnyRole("ADMIN_PLUS","ADMIN");
				auth.requestMatchers(HttpMethod.GET,"/api/tematicas").hasAnyRole("ADMIN_PLUS","ADMIN");
				auth.requestMatchers(HttpMethod.GET,"/api/tematicas-activas").hasAnyRole("ADMIN_PLUS","ADMIN");
				auth.requestMatchers(HttpMethod.GET,"/api/tematica/**").hasAnyRole("ADMIN_PLUS","ADMIN");
				
				auth.requestMatchers(HttpMethod.POST,"/api/banner-h").hasAnyRole("ADMIN_PLUS","ADMIN");
				auth.requestMatchers(HttpMethod.PUT,"/api/banner-h").hasAnyRole("ADMIN_PLUS","ADMIN");
				auth.requestMatchers(HttpMethod.GET,"/api/banners-h").hasAnyRole("ADMIN_PLUS","ADMIN");
				auth.requestMatchers(HttpMethod.GET,"/api/banner-h/**").hasAnyRole("ADMIN_PLUS","ADMIN");
				
				auth.requestMatchers(HttpMethod.POST,"/api/banner-c").hasAnyRole("ADMIN_PLUS","ADMIN");
				auth.requestMatchers(HttpMethod.PUT,"/api/banner-c").hasAnyRole("ADMIN_PLUS","ADMIN");
				auth.requestMatchers(HttpMethod.GET,"/api/banners-c").hasAnyRole("ADMIN_PLUS","ADMIN");
				auth.requestMatchers(HttpMethod.GET,"/api/banner-c/**").hasAnyRole("ADMIN_PLUS","ADMIN");
				
				
				auth.anyRequest().permitAll(); 	
				//Esta linea de código se suele poner siempre al final porque 
				//significa que todo lo que no hayamos restringido "arriba" está permitido
				//Por lo tanto el orden en el que anotemos las "restricciones de acceso" es
				//importantísimo
				
				
			});
		
		
		
		http.addFilterAfter(jwtValidationFilter, BasicAuthenticationFilter.class);
		http.cors(cors-> cors.configurationSource(corsConfigurationSource()));
		
		//El CSRF del inglés: Cross Site Request Forgery o falsificación en petición de sitios cruzados
		//es un tipo de EXPLOIT malicioso de un sitio Web en el que comandos no autorizados son
		//transmitidos por un usuario (sin que el lo sepa) en el cual el sitio web confía
		
		http.csrf(csrf-> csrf.disable());
		
		return http.build();
		
	}//final del security filter chain
	
	
	//Una aplicación de Spring Security solo puede tener un password encoder y además
	//es OBLIGATORIO que lo tenga. 
	//Este password encoder debe estar anotado com @Bean. Al estar anotado como @Bean está
	//permanentemente cargado en al contexto de la aplicación y por lo tanto está contínuamente
	//activo
	
	@Bean
	PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuracion) throws Exception{
		
		
		return configuracion.getAuthenticationManager();
		
		
	}
	
	//Una vez que establezcamos CORS podemos deshabilitar los permisos de CORS que 
	//hicimos en los controladores para poder hacer las pruebas desde Angular en desarrollo
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		
		CorsConfiguration config = new CorsConfiguration();
		
		config.setAllowedOrigins(List.of("*"));//permitidos todos los orígenes
		config.setAllowedMethods(List.of("*"));//permitidos todos los métodos de http: POST, PUT, DELETE, UPDATE
		config.setAllowedHeaders(List.of("*"));//permitidos todos los headers (es importante porque en los headers llegan los tokens)
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		
		return source;
		
	}
	
	
	//DEJAMOS ESTE EJEMPLO COMO CONFIGURACIÓN UN POCO MÁS DETALLADA DE CORS
	/*@Bean
	CorsConfigurationSource corsConfigurationSource() {
		
		CorsConfiguration config = new CorsConfiguration();
		
		config.setAllowedOrigins(List.of("*"));//permitidos todos los orígenes
		config.setAllowedMethods(List.of("GET","POST"));//permitidos todos los métodos de http: GET Y POST
		config.setAllowedHeaders(List.of("Authorization","Cache-Control","Content-Type"));
		config.setAllowCredentials(true);
		config.setExposedHeaders(List.of("Authorization"));//De esta manera podemos acceder a la
		//información de "Authorization" en un interceptor de Angular
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		
		return source;
		
	}*/
	
	
	
	
	
	
	
	
	

}
