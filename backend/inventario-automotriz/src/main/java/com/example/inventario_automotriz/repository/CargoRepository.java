package com.example.inventario_automotriz.repository;

import com.example.inventario_automotriz.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
}