package com.example.inventario_automotriz.service;

import com.example.inventario_automotriz.dto.request.CargoRequestDTO;
import com.example.inventario_automotriz.dto.response.CargoResponseDTO;

import java.util.List;

public interface CargoService {
    CargoResponseDTO crearCargo(CargoRequestDTO cargoRequestDTO);
    List<CargoResponseDTO> obtenerTodosLosCargos();
    CargoResponseDTO obtenerCargoPorId(Long id);
    CargoResponseDTO actualizarCargo(Long id, CargoRequestDTO cargoRequestDTO);
    void eliminarCargo(Long id);
}
