package com.example.inventario_automotriz.service;

import com.example.inventario_automotriz.dto.request.ProductoRequestDTO;
import com.example.inventario_automotriz.dto.response.ProductoResponseDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProductoService {

    // Método para crear un producto
    ProductoResponseDTO crearProducto(ProductoRequestDTO productoRequestDTO);

    // Método para obtener todos los productos
    List<ProductoResponseDTO> obtenerTodosLosProductos();

    // Método para obtener un producto por su ID
    Optional<ProductoResponseDTO> obtenerProductoPorId(Long id);

    // Método para actualizar un producto
    ProductoResponseDTO actualizarProducto(Long id, ProductoRequestDTO productoRequestDTO);

    // Método para buscar productos con filtros
    List<ProductoResponseDTO> buscarProductos(String nombre, Long usuarioId, LocalDate fechaIngreso, LocalDate fechaModificacion);

    // Método para eliminar un producto por su ID y verificando que el usuario que lo creó sea quien lo elimine
    void eliminarProducto(Long id, Long usuarioId);
}
