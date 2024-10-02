package com.example.inventario_automotriz.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class UsuarioDTO {

    private Long id;

    @NotNull(message = "El nombre no puede ser nulo")
    private String nombre;

    @Email(message = "El correo debe ser válido")
    @NotNull(message = "El correo es obligatorio")
    private String email;

    @NotNull(message = "La edad es obligatoria")
    private Integer edad;

    @NotNull(message = "El cargo es obligatorio")
    private String cargo;

    @NotNull(message = "La fecha de ingreso es obligatoria")
    private LocalDate fechaIngreso;

    // Constructor vacío
    public UsuarioDTO() {
    }

    // Constructor con parámetros
    public UsuarioDTO(Long id, String nombre, String email, Integer edad, String cargo, LocalDate fechaIngreso) {
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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
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
