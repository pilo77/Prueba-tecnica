package com.example.inventario_automotriz.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private int edad;

    @ManyToOne
    @JoinColumn(name = "cargo_id", nullable = false)
    private Cargo cargo;

    @Column(nullable = false)
    private LocalDate fechaIngreso;

    @OneToMany(mappedBy = "usuario")
    private List<Producto> productos;


    public Usuario() {}


    public Usuario(Long id, String nombre, String email, int edad, Cargo cargo, LocalDate fechaIngreso) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.edad = edad;
        this.cargo = cargo;
        this.fechaIngreso = fechaIngreso;
    }


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

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
