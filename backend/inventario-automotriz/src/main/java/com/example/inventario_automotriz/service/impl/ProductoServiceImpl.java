package com.example.inventario_automotriz.service.impl;

import com.example.inventario_automotriz.dto.ProductoDTO;
import com.example.inventario_automotriz.exception.ProductoNotFoundException;
import com.example.inventario_automotriz.model.Producto;
import com.example.inventario_automotriz.model.Usuario;
import com.example.inventario_automotriz.repository.ProductoRepository;
import com.example.inventario_automotriz.repository.UsuarioRepository;
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
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository, UsuarioRepository usuarioRepository) {
        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
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

        // Verificar si el usuario existe
        Usuario usuario = usuarioRepository.findById(productoDTO.getUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // Crear el producto
        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setCantidad(productoDTO.getCantidad());
        producto.setFechaIngreso(productoDTO.getFechaIngreso());
        producto.setUsuario(usuario);

        // Guardar el producto en la base de datos
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

        // Verificar si el usuario que está modificando existe
        Usuario usuario = usuarioRepository.findById(productoDTO.getUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        producto.setNombre(productoDTO.getNombre());
        producto.setCantidad(productoDTO.getCantidad());
        producto.setFechaIngreso(productoDTO.getFechaIngreso());
        producto.setUsuario(usuario);

        Producto productoActualizado = productoRepository.save(producto);
        return new ProductoDTO(productoActualizado.getId(), productoActualizado.getNombre(),
                productoActualizado.getCantidad(), productoActualizado.getFechaIngreso(), productoActualizado.getUsuario().getId());
    }
    @Override
    public void eliminarProducto(Long id, Long usuarioId) {
        // Obtener el producto por ID
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado"));

        // Verificar que el usuario que intenta eliminar sea el mismo que creó el producto
        Long usuarioCreadorId = producto.getUsuario().getId();

        // Si el usuario que intenta eliminar no es el creador, lanzar excepción
        if (!usuarioCreadorId.equals(usuarioId)) {
            throw new IllegalArgumentException("No tienes permiso para eliminar este producto.");
        }

        // Si todo está bien, eliminar el producto
        productoRepository.deleteById(id);
    }
}
