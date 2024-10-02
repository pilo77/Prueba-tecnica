package com.example.inventario_automotriz.controller;

import com.example.inventario_automotriz.dto.ProductoDTO;
import com.example.inventario_automotriz.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

    // Endpoint para crear un producto
    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto(@Validated @RequestBody ProductoDTO productoDTO) {
        ProductoDTO nuevoProducto = productoService.crearProducto(productoDTO);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }

    // Endpoint para obtener todos los productos
    @GetMapping
    public ResponseEntity<List<ProductoDTO>> obtenerTodosLosProductos() {
        List<ProductoDTO> productos = productoService.obtenerTodosLosProductos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    // Endpoint para obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerProductoPorId(@PathVariable Long id) {
        Optional<ProductoDTO> producto = productoService.obtenerProductoPorId(id);
        return producto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/buscar")
    public ResponseEntity<List<ProductoDTO>> buscarProductos(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Long usuarioId,
            @RequestParam(required = false) LocalDate fechaIngreso,
            @RequestParam(required = false) LocalDate fechaModificacion) {

        List<ProductoDTO> productos = productoService.buscarProductos(nombre, usuarioId, fechaIngreso, fechaModificacion);
        return ResponseEntity.ok(productos);
    }

    // Endpoint para actualizar un producto
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable Long id, @Validated @RequestBody ProductoDTO productoDTO) {
        productoDTO.setId(id);  // Asegurarse de que el ID coincida
        ProductoDTO productoActualizado = productoService.actualizarProducto(productoDTO);
        return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
    }

    // Eliminar un producto solo por el usuario que lo creó
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id, @RequestParam Long usuarioId) {
        productoService.eliminarProducto(id, usuarioId);
        return ResponseEntity.noContent().build();  // 204 No Content cuando se elimina correctamente
    }
}

