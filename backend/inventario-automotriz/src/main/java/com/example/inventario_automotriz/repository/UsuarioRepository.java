package com.example.inventario_automotriz.repository;

import com.example.inventario_automotriz.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Puedes agregar métodos personalizados si lo necesitas, por ejemplo, búsqueda por nombre
}