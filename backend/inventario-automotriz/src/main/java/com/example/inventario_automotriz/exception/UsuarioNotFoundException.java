package com.example.inventario_automotriz.exception;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(String message) {

        super(message);
    }
}
