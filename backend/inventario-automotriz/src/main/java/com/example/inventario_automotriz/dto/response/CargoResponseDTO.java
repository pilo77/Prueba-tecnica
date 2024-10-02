package com.example.inventario_automotriz.dto.response;

public class CargoResponseDTO {

    private Long id;
    private String nombre;

    // Constructor vacío
    public CargoResponseDTO() {
    }

    // Constructor con parámetros
    public CargoResponseDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
