package com.ipartek.springboot.backend.elpisito.security;


import com.fasterxml.jackson.databind.ObjectMapper;
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

        //Al método jwtUserDetailsService le mandamos el username (email)
        //y nos devuelve un objeto UserDetails (rol,password,email)
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(request.getUsername());

        final String token = jwtService.generateToken(userDetails);

        final String messageTitle = "Login realizado con éxito!!!";
        final String message = userDetails.getUsername() + ", te has logueado correctamente";

        //Siempre y cuando necesitemos o decidamos enviar algo en los headers del response
        //tenemos que crear un objeto de tipo HttpHeaders que incluiremos en el ResponseEntity

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);//Esta línea tiene que ser exacta

        //De esta forma incluímos en la response info a través del header

        return ResponseEntity
                .ok()//devuelve un objeto BodyBuilder
                .headers(headers)//incluimos los headers y devuelve un objeto BodyBuilder
                .body(new JWTResponse(token,message,messageTitle));//Devuelve un ResponseEntity



    }



    private void authenticate(JWTRequest request) {

        try {

            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));


        }catch(BadCredentialsException e) {

            throw buildResponseStatusException(HttpStatus.UNAUTHORIZED, "Error de autenticación!","Credenciales incorrectas. Por favor verifica tu email y contraseña");

        }catch(DisabledException e) {

            throw buildResponseStatusException(HttpStatus.FORBIDDEN, "Cuenta deshabilitada!","Tu cuenta esta deshabilitada. Contacta con soporte");

        }catch(RuntimeException e) {

            throw buildResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error del sistema!",e.getMessage());
        }


    }



    private ResponseStatusException buildResponseStatusException(HttpStatus status, String titulo, String mensaje) {

        try {
            //Construímos un objeto CustomError y lo serializamos a JSON
            CustomError error = new CustomError(titulo, mensaje);
            ObjectMapper mapper = new ObjectMapper();
            String motivo = mapper.writeValueAsString(error);//Convertimos CustomError en un JSON String
            //Devolvemos un ResponseStatusException con el JSON en el campo motivo
            return new ResponseStatusException(status,motivo);

        }catch(Exception e) {
            //Si se produce algún problema en la serialización devolvemos una excepción genérica
            return new ResponseStatusException(status,mensaje);

        }



    }






}
