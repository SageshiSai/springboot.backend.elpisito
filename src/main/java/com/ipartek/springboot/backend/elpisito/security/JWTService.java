package com.ipartek.springboot.backend.elpisito.security;

import com.ipartek.springboot.backend.elpisito.models.dao.IUsuarioDAO;
import com.ipartek.springboot.backend.elpisito.models.entity.Usuarios;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    //En esta clase vamos a definir (parametrizar) varios parámetros como, por ejemplo,
    //tiempo de validez del token, el nombre secreto, crear un token, verificar si es válido...

    public static final long JWT_TOKEN_VALIDITY = 1200; //Segundos  (20mins)
    public static final String JWT_SECRET = "utitiltiopjjpjjpjppjjp";

    @Autowired
    private IUsuarioDAO usuarioDAO;

    /// /////////////////////////////////////////////////////////
    /// MÉTODO PARA COMPROBAR Y SACAR INFORMACIÓN DEL TOKEN /////
    /// /////////////////////////////////////////////////////////

    //Este método devolverá los claims del token
    //Recibirmos una cosa parecida a esta "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
    //que es un token...

    private Claims getAllClaimsFromToken(String token){

        final Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJwt(token)
                .getBody();
    }

    //Este método recíbe los claims de un objeto Claims y devuelve objetos del tipo claims ("String"
    public <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver){
        final Claims claims = this.getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //Este método devuelve la fecha de expiración del token...
    private Date getTokenExpirationDate(String token){
        return this.getClaimsFromToken(token, Claims::getExpiration );
    }

    //Este método compruba si el token está expirado

    private boolean isTokenExpired(String token) {
        final Date expirationDate = this.getTokenExpirationDate(token);
        return expirationDate.before(new Date());
    }

    public String getTokenUserName(String token){
        return this.getClaimsFromToken(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String userNameFromUserDetail = userDetails.getUsername(); //Le mandamos el email
        final String userNameFromJWT = this.getTokenUserName(token);

        return userNameFromUserDetail.equals(userNameFromJWT) && !this.isTokenExpired(token);
    }

    /// /////////////////////////////////
    /// MÉTODOS PARA CREAR EL TOKEN /////
    /// /////////////////////////////////


    //Método para generar "internamente" el token...
    private String getToken(Map<String, Object>claims, String subject) {
        final Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
        return Jwts
                .builder()
                .setClaims(claims) //ES un map con todos tipo de datos. Aquí lo seteamos en le token
                .setSubject(subject) //Es el username que ne nuestro caso es el email
                .setIssuedAt(new Date(System.currentTimeMillis())) //ahora
                .setExpiration(new Date(System.currentTimeMillis()+ (JWT_TOKEN_VALIDITY * 1000)))
                .signWith(key) //Cerramos el proceso con el key que contiene la llave secreta
                .compact(); //Convierte en String el objeto JWTS Builder
    }

    //Este método "pasa" el token generado allá donde nos haga falta
    //Recordemos que en el objeto userDetails va la información del usuario: email, rol, password
    public String generateToken(UserDetails userDetails){
        Usuarios elUsuario = usuarioDAO.findOneByEmail(userDetails.getUsername())
                .map(u -> u)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado en la base de datos"));
        //Es aquí donde podríamos pasar datos en el objeto CLaims del token que estamos creando
        final Map<String, Object> claims = new HashMap<>();
        claims.put("ROLES", userDetails.getAuthorities().toString());//ROLE_ADMIN, ROLE_USER
        claims.put("usuario", elUsuario.getUser());
        claims.put("id", elUsuario.getId());

        //Retornamos el token
        return this.getToken(claims, userDetails.getUsername());
    }
}

