package com.example.inventario_automotriz.controller;

import com.example.inventario_automotriz.dto.request.CargoRequestDTO;
import com.example.inventario_automotriz.dto.response.CargoResponseDTO;
import com.example.inventario_automotriz.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/cargos")
public class CargoController {

    private final CargoService cargoService;

    @Autowired
    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @PostMapping
    public ResponseEntity<CargoResponseDTO> crearCargo(@RequestBody CargoRequestDTO cargoRequestDTO) {
        CargoResponseDTO nuevoCargo = cargoService.crearCargo(cargoRequestDTO);
        return new ResponseEntity<>(nuevoCargo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CargoResponseDTO>> obtenerTodosLosCargos() {
        List<CargoResponseDTO> cargos = cargoService.obtenerTodosLosCargos();
        return new ResponseEntity<>(cargos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargoResponseDTO> obtenerCargoPorId(@PathVariable Long id) {
        CargoResponseDTO cargo = cargoService.obtenerCargoPorId(id);
        return new ResponseEntity<>(cargo, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CargoResponseDTO> actualizarCargo(@PathVariable Long id, @RequestBody CargoRequestDTO cargoRequestDTO) {
        CargoResponseDTO cargoActualizado = cargoService.actualizarCargo(id, cargoRequestDTO);
        return new ResponseEntity<>(cargoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCargo(@PathVariable Long id) {
        cargoService.eliminarCargo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
