package com.ipartek.springboot.backend.elpisito.security;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@AllArgsConstructor
public class JWTValidationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final JWTUserDetailsService jwtUserDetailsService;
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTHORIZATION_HEADER_BEARER = "Bearer ";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader(AUTHORIZATION_HEADER);
        String username = null;
        String jwt = null;

        //En el caso de que requestTokenHeader ( que nos llega de la petición request )
        //no sea null y empiece por "Bearer "...
        if(Objects.nonNull(requestTokenHeader) && requestTokenHeader.startsWith(AUTHORIZATION_HEADER_BEARER)){
            jwt = requestTokenHeader.substring(7); //Obtenemos el token que nos llega sin el prefijo "Bearer "

            try {
                username = jwtService.getTokenUserName(jwt);
            } catch (IllegalArgumentException e){
                throw new RuntimeException("Error: Argumentos ilegales en el token.");
            } catch (ExpiredJwtException e){
                throw new RuntimeException("Token expirado");
            }
        }

        //Si el usuario no es null pero la autentificación no es correcta
        if(Objects.nonNull(username) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())){
            final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
            if (jwtService.isTokenValid(jwt, userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }


}
