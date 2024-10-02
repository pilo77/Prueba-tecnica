package com.example.inventario_automotriz.dto.response;

import java.time.LocalDate;

public class UsuarioResponseDTO {

    private Long id;
    private String nombre;
    private String email;
    private int edad;
    private String cargo;
    private LocalDate fechaIngreso;

    // Constructor vacío
    public UsuarioResponseDTO() {
    }

    // Constructor con parámetros
    public UsuarioResponseDTO(Long id, String nombre, String email, int edad, String cargo, LocalDate fechaIngreso) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.edad = edad;
        this.cargo = cargo;
        this.fechaIngreso = fechaIngreso;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}
