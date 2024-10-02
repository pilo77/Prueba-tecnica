package com.example.inventario_automotriz.service;

import com.example.inventario_automotriz.dto.ProductoDTO;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    // Método para crear un producto
    ProductoDTO crearProducto(ProductoDTO productoDTO);

    // Método para obtener todos los productos
    List<ProductoDTO> obtenerTodosLosProductos();

    // Método para obtener un producto por su ID
    Optional<ProductoDTO> obtenerProductoPorId(Long id);

    // Método para actualizar un producto
    ProductoDTO actualizarProducto(ProductoDTO productoDTO);

    // Método para eliminar un producto por su ID y el usuario que lo creó
    void eliminarProducto(Long id, Long usuarioId);
}
