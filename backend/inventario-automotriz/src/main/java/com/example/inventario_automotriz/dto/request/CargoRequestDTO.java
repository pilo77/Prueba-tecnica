package com.example.inventario_automotriz.dto.request;

import jakarta.validation.constraints.NotNull;

public class CargoRequestDTO {

    @NotNull(message = "El nombre del cargo no puede ser nulo")
    private String nombre;

    // Constructor vacío
    public CargoRequestDTO() {
    }

    // Constructor con parámetros
    public CargoRequestDTO(String nombre) {
        this.nombre = nombre;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
