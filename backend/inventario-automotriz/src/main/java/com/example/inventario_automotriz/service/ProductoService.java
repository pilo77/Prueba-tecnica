package com.example.inventario_automotriz.service;

import com.example.inventario_automotriz.dto.request.ProductoRequestDTO;
import com.example.inventario_automotriz.dto.response.ProductoResponseDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProductoService {


    ProductoResponseDTO crearProducto(ProductoRequestDTO productoRequestDTO);


    List<ProductoResponseDTO> obtenerTodosLosProductos();


    Optional<ProductoResponseDTO> obtenerProductoPorId(Long id);


    ProductoResponseDTO actualizarProducto(Long id, ProductoRequestDTO productoRequestDTO);


    List<ProductoResponseDTO> buscarProductos(String nombre, Long usuarioId, LocalDate fechaIngreso, LocalDate fechaModificacion);


    void eliminarProducto(Long id, Long usuarioId);
}
