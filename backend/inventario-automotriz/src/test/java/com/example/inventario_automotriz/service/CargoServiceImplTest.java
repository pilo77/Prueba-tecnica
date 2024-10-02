package com.example.inventario_automotriz.service;

import com.example.inventario_automotriz.dto.request.CargoRequestDTO;
import com.example.inventario_automotriz.dto.response.CargoResponseDTO;
import com.example.inventario_automotriz.model.Cargo;
import com.example.inventario_automotriz.repository.CargoRepository;
import com.example.inventario_automotriz.service.CargoService;
import com.example.inventario_automotriz.service.impl.CargoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CargoServiceImplTest {

    @Mock
    private CargoRepository cargoRepository;

    @InjectMocks
    private CargoServiceImpl cargoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCrearCargo() {
        CargoRequestDTO cargoRequestDTO = new CargoRequestDTO("Administrador");
        Cargo cargo = new Cargo(null, "Administrador");
        Cargo savedCargo = new Cargo(1L, "Administrador");

        when(cargoRepository.save(any(Cargo.class))).thenReturn(savedCargo);

        CargoResponseDTO responseDTO = cargoService.crearCargo(cargoRequestDTO);

        assertNotNull(responseDTO);
        assertEquals("Administrador", responseDTO.getNombre());
        assertEquals(1L, responseDTO.getId());
        verify(cargoRepository, times(1)).save(any(Cargo.class));
    }

    @Test
    public void testObtenerTodosLosCargos() {
        List<Cargo> cargos = Arrays.asList(
                new Cargo(1L, "Administrador"),
                new Cargo(2L, "Soporte")
        );

        when(cargoRepository.findAll()).thenReturn(cargos);

        List<CargoResponseDTO> responseList = cargoService.obtenerTodosLosCargos();

        assertEquals(2, responseList.size());
        assertEquals("Administrador", responseList.get(0).getNombre());
        assertEquals("Soporte", responseList.get(1).getNombre());
        verify(cargoRepository, times(1)).findAll();
    }

    @Test
    public void testObtenerCargoPorId() {
        Cargo cargo = new Cargo(1L, "Administrador");
        when(cargoRepository.findById(1L)).thenReturn(Optional.of(cargo));

        CargoResponseDTO responseDTO = cargoService.obtenerCargoPorId(1L);

        assertNotNull(responseDTO);
        assertEquals(1L, responseDTO.getId());
        assertEquals("Administrador", responseDTO.getNombre());
        verify(cargoRepository, times(1)).findById(1L);
    }

    @Test
    public void testActualizarCargo() {
        Cargo cargo = new Cargo(1L, "Administrador");
        CargoRequestDTO cargoRequestDTO = new CargoRequestDTO("Nuevo Nombre");

        when(cargoRepository.findById(1L)).thenReturn(Optional.of(cargo));
        when(cargoRepository.save(any(Cargo.class))).thenReturn(new Cargo(1L, "Nuevo Nombre"));

        CargoResponseDTO responseDTO = cargoService.actualizarCargo(1L, cargoRequestDTO);

        assertNotNull(responseDTO);
        assertEquals(1L, responseDTO.getId());
        assertEquals("Nuevo Nombre", responseDTO.getNombre());
        verify(cargoRepository, times(1)).findById(1L);
        verify(cargoRepository, times(1)).save(any(Cargo.class));
    }

    @Test
    public void testEliminarCargo() {
        Cargo cargo = new Cargo(1L, "Administrador");
        when(cargoRepository.findById(1L)).thenReturn(Optional.of(cargo));

        cargoService.eliminarCargo(1L);

        verify(cargoRepository, times(1)).delete(cargo);
    }
}
