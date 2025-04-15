package com.ipartek.springboot.backend.elpisito.security;

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

import java.util.List;

@Configuration
public class SecurityConfig {


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, JWTValidationFilter jwtValidationFilter) throws Exception{
        //En una API REST no existen sesiones. No existen los estados.
        return http
                .authorizeHttpRequests(auth -> {
                    //Podemos hacer infinitas combinaciones utilizando el SELECTOR y el AUTORIZADOR
                    //Podemos jugat con el SELECTOR : anyRequest(), requestMatcher()...
                    //Podemos jugar con el AUTORIZADOR: permitAll(), authenticated(), hasRole()
                    //Haciendo combinaciones entre SELECTOR y un AUTORIZADOR crearemos autorizaciones a la carta
                    auth.requestMatchers(HttpMethod.GET, "/", "/save").permitAll();
                    auth.anyRequest().authenticated();
                })
                .addFilterAfter(jwtValidationFilter, BasicAuthenticationFilter.class)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();


        //El csrf del inglés: Cross Site Request Forgery o falsificación en petición de sitios cruzados
        //Es un tipo de EXPLOIT malisioso de un sitio web en el que comandos no autorizados son
        //transmitidos por un usuario (sin que el lo sepa) en el cual el sitio web confía.
    }



    //Una aplicación de Spring Security, solo puede tener un passwordEncoder y además es
    //Es OBLIGATORIO que lo tenga.
    //Este password encoder tiene que estar anotado como @Bean. Al estar anotado como bean, esta permanentemente cargado
    //en el contexto de la aplicación, y por lo tanto, está constantemente activo.
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{

        return configuration.getAuthenticationManager();

    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("*"));//Permite todos los origenes
        config.setAllowedMethods(List.of("*"));//Permitidos todos los metodos http
        config.setAllowedHeaders(List.of("*"));//Permitidos todos los headers (Es importante porque ne los headers llegan los tokens)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    /*@Bean
    CorsConfigurationSource corsConfigurationSource(){

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("*"));//Permite todos los origenes
        config.setAllowedMethods(List.of("GET", "POST"));//Permitidos todos los metodos http
        config.setAllowedHeaders(List.of("Content-Type", "Authorization"));//Permitidos todos los headers (Es importante porque ne los headers llegan los tokens)
        config.setAllowCredentials(true);
        config.setExposedHeaders(List.of("Authorization")); //De esta manera podemos acceder a la informacion desde un interprete como angular


        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }*/
}
