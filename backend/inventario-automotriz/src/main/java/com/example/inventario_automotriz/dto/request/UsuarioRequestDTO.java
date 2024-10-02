package com.example.inventario_automotriz.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class UsuarioRequestDTO {

    @NotNull(message = "El nombre no puede ser nulo")
    private String nombre;

    @Email(message = "El correo debe ser válido")
    @NotNull(message = "El correo es obligatorio")
    private String email;

    @NotNull(message = "La edad no puede ser nula")
    private int edad;

    @NotNull(message = "El cargo es obligatorio")
    private Long cargoId;

    @NotNull(message = "La fecha de ingreso no puede ser nula")
    private LocalDate fechaIngreso;

    // Getters y setters
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

    public Long getCargoId() {
        return cargoId;
    }

    public void setCargoId(Long cargoId) {
        this.cargoId = cargoId;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}
