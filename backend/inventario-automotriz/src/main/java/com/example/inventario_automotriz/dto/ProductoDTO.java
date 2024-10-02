package com.example.inventario_automotriz.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public class ProductoDTO {

    private Long id;

    @NotNull(message = "El nombre no puede ser nulo")
    private String nombre;

    @Min(value = 1, message = "La cantidad debe ser mayor o igual a 1")
    private int cantidad;

    @PastOrPresent(message = "La fecha de ingreso no puede ser futura")
    private LocalDate fechaIngreso;

    @NotNull(message = "El usuario es obligatorio")
    private Long usuarioId;

    // Constructor vacío
    public ProductoDTO() {
    }

    // Constructor con parámetros
    public ProductoDTO(Long id, String nombre, int cantidad, LocalDate fechaIngreso, Long usuarioId) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.fechaIngreso = fechaIngreso;
        this.usuarioId = usuarioId;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
