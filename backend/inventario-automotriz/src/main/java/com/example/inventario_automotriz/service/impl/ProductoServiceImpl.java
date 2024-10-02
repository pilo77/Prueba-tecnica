package com.example.inventario_automotriz.service.impl;

import com.example.inventario_automotriz.dto.ProductoDTO;
import com.example.inventario_automotriz.exception.ProductoNotFoundException;
import com.example.inventario_automotriz.model.Producto;
import com.example.inventario_automotriz.repository.ProductoRepository;
import com.example.inventario_automotriz.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public ProductoDTO crearProducto(ProductoDTO productoDTO) {
        // Verificar que no haya un producto con el mismo nombre
        if (productoRepository.findByNombre(productoDTO.getNombre()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un producto con ese nombre.");
        }

        // Validar que la fecha de ingreso no sea futura
        if (productoDTO.getFechaIngreso().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de ingreso no puede ser futura.");
        }

        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setCantidad(productoDTO.getCantidad());
        producto.setFechaIngreso(productoDTO.getFechaIngreso());

        Producto nuevoProducto = productoRepository.save(producto);
        return new ProductoDTO(nuevoProducto.getId(), nuevoProducto.getNombre(), nuevoProducto.getCantidad(),
                nuevoProducto.getFechaIngreso(), nuevoProducto.getUsuario().getId());
    }

    @Override
    public List<ProductoDTO> obtenerTodosLosProductos() {
        return productoRepository.findAll().stream()
                .map(producto -> new ProductoDTO(producto.getId(), producto.getNombre(), producto.getCantidad(),
                        producto.getFechaIngreso(), producto.getUsuario().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductoDTO> obtenerProductoPorId(Long id) {
        return productoRepository.findById(id)
                .map(producto -> new ProductoDTO(producto.getId(), producto.getNombre(), producto.getCantidad(),
                        producto.getFechaIngreso(), producto.getUsuario().getId()));
    }

    @Override
    public ProductoDTO actualizarProducto(ProductoDTO productoDTO) {
        Producto producto = productoRepository.findById(productoDTO.getId())
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado"));

        producto.setNombre(productoDTO.getNombre());
        producto.setCantidad(productoDTO.getCantidad());
        producto.setFechaIngreso(productoDTO.getFechaIngreso());

        Producto productoActualizado = productoRepository.save(producto);
        return new ProductoDTO(productoActualizado.getId(), productoActualizado.getNombre(),
                productoActualizado.getCantidad(), productoActualizado.getFechaIngreso(), productoActualizado.getUsuario().getId());
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
