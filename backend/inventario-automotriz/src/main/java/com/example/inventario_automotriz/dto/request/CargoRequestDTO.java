package com.example.inventario_automotriz.dto.request;

import jakarta.validation.constraints.NotNull;

public class CargoRequestDTO {

    @NotNull(message = "El nombre del cargo no puede ser nulo")
    private String nombre;


    public CargoRequestDTO() {
    }

    public CargoRequestDTO(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
