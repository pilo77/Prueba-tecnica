package com.example.inventario_automotriz.repository;

import com.example.inventario_automotriz.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByNombre(String nombre);
    // Consulta dinámica para buscar productos por nombre, usuario y fecha de ingreso
    @Query("SELECT p FROM Producto p WHERE (:nombre IS NULL OR p.nombre LIKE %:nombre%) " +
            "AND (:usuarioId IS NULL OR p.usuario.id = :usuarioId) " +
            "AND (:fechaIngreso IS NULL OR p.fechaIngreso = :fechaIngreso) " +
            "AND (:fechaModificacion IS NULL OR p.fechaModificacion = :fechaModificacion)")
    List<Producto> buscarProductos(@Param("nombre") String nombre,
                                   @Param("usuarioId") Long usuarioId,
                                   @Param("fechaIngreso") LocalDate fechaIngreso,
                                   @Param("fechaModificacion") LocalDate fechaModificacion);

}