package com.ipartek.springboot.backend.elpisito.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomError {

    private String titulo;
    private String mensaje;


}
