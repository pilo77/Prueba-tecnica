package com.example.inventario_automotriz.exception;

public class CargoNotFoundException extends RuntimeException {
    public CargoNotFoundException(String message) {
        super(message);
    }
}
