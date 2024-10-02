package com.example.inventario_automotriz.service.impl;

import com.example.inventario_automotriz.dto.request.ProductoRequestDTO;
import com.example.inventario_automotriz.dto.response.ProductoResponseDTO;
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
    public ProductoResponseDTO crearProducto(ProductoRequestDTO productoRequestDTO) {
        // Verificar que no haya un producto con el mismo nombre
        if (productoRepository.findByNombre(productoRequestDTO.getNombre()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un producto con ese nombre.");
        }

        // Validar que la cantidad sea mayor o igual a 1
        if (productoRequestDTO.getCantidad() < 1) {
            throw new IllegalArgumentException("La cantidad debe ser mayor o igual a 1.");
        }

        // Validar que la fecha de ingreso no sea futura
        if (productoRequestDTO.getFechaIngreso().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de ingreso no puede ser mayor a la fecha actual.");
        }

        // Verificar si el usuario existe
        Usuario usuario = usuarioRepository.findById(productoRequestDTO.getUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // Crear el producto
        Producto producto = new Producto();
        producto.setNombre(productoRequestDTO.getNombre());
        producto.setCantidad(productoRequestDTO.getCantidad());
        producto.setFechaIngreso(productoRequestDTO.getFechaIngreso());
        producto.setUsuario(usuario);

        // Guardar el producto en la base de datos
        Producto nuevoProducto = productoRepository.save(producto);
        return new ProductoResponseDTO(nuevoProducto.getId(), nuevoProducto.getNombre(), nuevoProducto.getCantidad(),
                nuevoProducto.getFechaIngreso(), nuevoProducto.getUsuario().getId(), nuevoProducto.getFechaModificacion());
    }

    @Override
    public List<ProductoResponseDTO> obtenerTodosLosProductos() {
        return productoRepository.findAll().stream()
                .map(producto -> new ProductoResponseDTO(producto.getId(), producto.getNombre(), producto.getCantidad(),
                        producto.getFechaIngreso(), producto.getUsuario().getId(), producto.getFechaModificacion()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductoResponseDTO> obtenerProductoPorId(Long id) {
        return productoRepository.findById(id)
                .map(producto -> new ProductoResponseDTO(producto.getId(), producto.getNombre(), producto.getCantidad(),
                        producto.getFechaIngreso(), producto.getUsuario().getId(), producto.getFechaModificacion()));
    }

    @Override
    public ProductoResponseDTO actualizarProducto(Long id, ProductoRequestDTO productoRequestDTO) {
        // Verificar si el producto existe
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado"));

        // Validar que la cantidad sea mayor o igual a 1
        if (productoRequestDTO.getCantidad() < 1) {
            throw new IllegalArgumentException("La cantidad debe ser mayor o igual a 1.");
        }

        // Validar que la fecha de ingreso no sea futura
        if (productoRequestDTO.getFechaIngreso().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de ingreso no puede ser mayor a la fecha actual.");
        }

        // Verificar si el usuario que modifica existe
        Usuario usuario = usuarioRepository.findById(productoRequestDTO.getUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // Actualizar los datos del producto
        producto.setNombre(productoRequestDTO.getNombre());
        producto.setCantidad(productoRequestDTO.getCantidad());
        producto.setFechaIngreso(productoRequestDTO.getFechaIngreso());
        producto.setUsuario(usuario);

        // Registrar la fecha de modificación
        producto.setFechaModificacion(LocalDate.now());

        // Guardar los cambios
        Producto productoActualizado = productoRepository.save(producto);

        return new ProductoResponseDTO(productoActualizado.getId(), productoActualizado.getNombre(),
                productoActualizado.getCantidad(), productoActualizado.getFechaIngreso(),
                productoActualizado.getUsuario().getId(), productoActualizado.getFechaModificacion());
    }

    @Override
    public List<ProductoResponseDTO> buscarProductos(String nombre, Long usuarioId, LocalDate fechaIngreso, LocalDate fechaModificacion) {
        List<Producto> productos = productoRepository.buscarProductos(nombre, usuarioId, fechaIngreso, fechaModificacion);

        return productos.stream()
                .map(producto -> new ProductoResponseDTO(producto.getId(), producto.getNombre(), producto.getCantidad(),
                        producto.getFechaIngreso(), producto.getUsuario().getId(), producto.getFechaModificacion()))
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarProducto(Long id, Long usuarioId) {
        // Obtener el producto por ID
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado"));

        // Verificar que el usuario que intenta eliminar sea el mismo que creó el producto
        Long usuarioCreadorId = producto.getUsuario().getId();

        // Comparar correctamente los IDs
        if (!usuarioCreadorId.equals(usuarioId)) {
            throw new IllegalArgumentException("No tienes permiso para eliminar este producto.");
        }

        // Si todo está bien, eliminar el producto
        productoRepository.deleteById(id);
    }

}
