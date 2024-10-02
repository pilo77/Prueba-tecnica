package com.example.inventario_automotriz.service.impl;

import com.example.inventario_automotriz.dto.request.CargoRequestDTO;
import com.example.inventario_automotriz.dto.response.CargoResponseDTO;
import com.example.inventario_automotriz.exception.CargoNotFoundException;
import com.example.inventario_automotriz.model.Cargo;
import com.example.inventario_automotriz.repository.CargoRepository;
import com.example.inventario_automotriz.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CargoServiceImpl implements CargoService {

    private final CargoRepository cargoRepository;

    @Autowired
    public CargoServiceImpl(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    @Override
    public CargoResponseDTO crearCargo(CargoRequestDTO cargoRequestDTO) {
        Cargo cargo = new Cargo();
        cargo.setNombre(cargoRequestDTO.getNombre());

        Cargo nuevoCargo = cargoRepository.save(cargo);
        return new CargoResponseDTO(nuevoCargo.getId(), nuevoCargo.getNombre());
    }

    @Override
    public List<CargoResponseDTO> obtenerTodosLosCargos() {
        return cargoRepository.findAll().stream()
                .map(cargo -> new CargoResponseDTO(cargo.getId(), cargo.getNombre()))
                .collect(Collectors.toList());
    }

    @Override
    public CargoResponseDTO obtenerCargoPorId(Long id) {
        Cargo cargo = cargoRepository.findById(id)
                .orElseThrow(() -> new CargoNotFoundException("Cargo no encontrado"));
        return new CargoResponseDTO(cargo.getId(), cargo.getNombre());
    }


    @Override
    public CargoResponseDTO actualizarCargo(Long id, CargoRequestDTO cargoRequestDTO) {
        Cargo cargo = cargoRepository.findById(id)
                .orElseThrow(() -> new CargoNotFoundException("Cargo no encontrado"));

        cargo.setNombre(cargoRequestDTO.getNombre());
        Cargo cargoActualizado = cargoRepository.save(cargo);
        return new CargoResponseDTO(cargoActualizado.getId(), cargoActualizado.getNombre());
    }

    @Override
    public void eliminarCargo(Long id) {
        Cargo cargo = cargoRepository.findById(id)
                .orElseThrow(() -> new CargoNotFoundException("Cargo no encontrado"));
        cargoRepository.delete(cargo);
    }
}
