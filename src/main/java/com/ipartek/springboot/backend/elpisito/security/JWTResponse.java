package com.ipartek.springboot.backend.elpisito.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTResponse {

    //Esta clase se encarga de enviar los datos en formato JSON

    private String jwt;
    private String message;
    private String messageTitle;


}
