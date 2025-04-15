package com.ipartek.springboot.backend.elpisito.security;

public class CustomError extends RuntimeException {
    public buildResponseStatusExeption(String message) {
        super(message);
    }
}
