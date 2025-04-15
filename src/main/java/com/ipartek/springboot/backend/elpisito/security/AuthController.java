package com.ipartek.springboot.backend.elpisito.security;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@AllArgsConstructor
public class AuthController {


    private final JWTUserDetailsService jwtUserDetailsService;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public ResponseEntity<?> postToken(@RequestBody JWTRequest request){
        this.authenticate(request);
        //Al método JWTUserService le mandamos el username (email)
        //Y nos devuelve un objeto UserDetails (rol, password, email)
        UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(request.getUsername());

        final String token = jwtService.generateToken(userDetails);
        final String messageTitle = "Login realizado con éxito!";
        final String message = userDetails.getUsername() + ", te has logueado correctamente.";

        //Siempre y cuando necesitemos o decidamos enviar algo en los headers del 'response', tenemos que crear un objeto
        //de tipo HttpHeaders que incluiremos en un ResponseEntity

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+token); //Esta linea tiene que ser exacto
        //De esta forma incluímos en la response info a través del header
        return ResponseEntity
                .ok() //devuelve un objeto BodyBuilder
                .headers(headers) //incluso los headers y devuelve un objeto BodyBuilder
                .body(new JWTResponse(token, message, messageTitle));//Devuelve un ResponseEntity

    }

    private void authenticate(JWTRequest request){
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        } catch (BadCredentialsException e) {
            throw buildResponseStatusExeption(HttpStatus.UNAUTHORIZED, "Error de autentificación!", "Credenciales incorrectas. Pro favor verifica tu email y contraseñas");
        } catch (DisabledException e){
            throw buildResponseStatusExeption("");
        } catch (RuntimeException e){
            throw buildResponseStatusExeption(e);
        }
    }

    private ResponseStatusException buildResponseStatusExeption(HttpStatus status, String titulo){
        CustomError error = new CustomError(titulo)
    }
}
