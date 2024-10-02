package com.example.inventario_automotriz.dto.response;

import java.time.LocalDate;

public class ProductoResponseDTO {

    private Long id;
    private String nombre;
    private int cantidad;
    private LocalDate fechaIngreso;
    private Long usuarioId;
    private LocalDate fechaModificacion;  // Incluye la fecha de modificación

    // Constructor vacío
    public ProductoResponseDTO() {}

    // Constructor completo
    public ProductoResponseDTO(Long id, String nombre, int cantidad, LocalDate fechaIngreso, Long usuarioId, LocalDate fechaModificacion) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.fechaIngreso = fechaIngreso;
        this.usuarioId = usuarioId;
        this.fechaModificacion = fechaModificacion;
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

    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
}
