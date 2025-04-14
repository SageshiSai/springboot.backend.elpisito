package com.ipartek.springboot.backend.elpisito.security;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {


    private final JWTUserDetailsService jwtUserDetailsService;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public ResponseEntity<?> postToken(@RequestBody JWTRequest request){

    }

    private void authenticate(JWTRequest request){
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        } catch (BadCredentialsException e) {
            throw BuildResponseStatusExection(HttpStatus.UNAUTHORIZED, "Error de autentificación!", "Credenciales incorrectas. Pro favor verifica tu email y contraseñas");
        } catch (DisabledException e){
           throw new DisabledException("");
        } catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }
}
