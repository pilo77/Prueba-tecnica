package com.example.inventario_automotriz.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class UsuarioRequestDTO {

    @NotNull(message = "El nombre no puede ser nulo")
    private String nombre;

    @Email(message = "El correo debe ser válido")
    @NotNull(message = "El correo es obligatorio")
    private String email;

    @Min(value = 0, message = "La edad no puede ser menor que 0")
    @NotNull(message = "La edad es obligatoria")
    private Integer edad;

    @NotNull(message = "El cargo es obligatorio")
    private Long cargoId;

    @NotNull(message = "La fecha de ingreso no puede ser nula")
    private LocalDate fechaIngreso;


    public UsuarioRequestDTO() {}


    public UsuarioRequestDTO(String nombre, String email, int edad, Long cargoId, LocalDate fechaIngreso) {
        this.nombre = nombre;
        this.email = email;
        this.edad = edad;
        this.cargoId = cargoId;
        this.fechaIngreso = fechaIngreso;
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
