package com.ipartek.springboot.backend.apirest.securiy;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/*Este servicio (anotado como @Component) es muy importante ya que vamos a decirle a Spring Security
 * que vamos a tener un end point de Autenticación. Vamos a obtener el token a través de
 * una petición POST. Este servicio implementa la interface AuthenticationEntryPoint */


@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(
			HttpServletRequest request, 
			HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"No autorizado");
		
	}

}
