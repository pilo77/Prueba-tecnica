package com.example.inventario_automotriz.service;

import com.example.inventario_automotriz.dto.ProductoDTO;
import java.util.List;
import java.util.Optional;

public interface ProductoService {
    ProductoDTO crearProducto(ProductoDTO productoDTO);
    List<ProductoDTO> obtenerTodosLosProductos();
    Optional<ProductoDTO> obtenerProductoPorId(Long id);
    ProductoDTO actualizarProducto(ProductoDTO productoDTO);
    void eliminarProducto(Long id);
}