package com.example.inventario_automotriz.controller;

import com.example.inventario_automotriz.dto.request.ProductoRequestDTO;
import com.example.inventario_automotriz.dto.response.ProductoResponseDTO;
import com.example.inventario_automotriz.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Endpoint para crear un nuevo producto
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crearProducto(@RequestBody @Valid ProductoRequestDTO productoRequestDTO) {
        ProductoResponseDTO nuevoProducto = productoService.crearProducto(productoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    // Endpoint para obtener todos los productos
    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> obtenerTodosLosProductos() {
        List<ProductoResponseDTO> productos = productoService.obtenerTodosLosProductos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    // Endpoint para obtener un producto por su ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> obtenerProductoPorId(@PathVariable Long id) {
        Optional<ProductoResponseDTO> producto = productoService.obtenerProductoPorId(id);
        return producto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para actualizar un producto
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(
            @PathVariable Long id,
            @RequestBody @Valid ProductoRequestDTO productoRequestDTO) {

        ProductoResponseDTO productoActualizado = productoService.actualizarProducto(id, productoRequestDTO);
        return ResponseEntity.ok(productoActualizado);
    }

    // Endpoint para buscar productos con filtros (nombre, usuario, fecha de ingreso, fecha de modificación)
    @GetMapping("/buscar")
    public ResponseEntity<List<ProductoResponseDTO>> buscarProductos(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Long usuarioId,
            @RequestParam(required = false) LocalDate fechaIngreso,
            @RequestParam(required = false) LocalDate fechaModificacion) {

        List<ProductoResponseDTO> productos = productoService.buscarProductos(nombre, usuarioId, fechaIngreso, fechaModificacion);
        return ResponseEntity.ok(productos);
    }

    // Endpoint para eliminar un producto (solo el usuario que lo creó)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id, @RequestParam Long usuarioId) {
        productoService.eliminarProducto(id, usuarioId);
        return ResponseEntity.noContent().build();
    }
}
