package com.ipartek.springboot.backend.elpisito.security;

public class CustomError extends RuntimeException {
    public CustomError(String message) {
        super(message);
    }
}
