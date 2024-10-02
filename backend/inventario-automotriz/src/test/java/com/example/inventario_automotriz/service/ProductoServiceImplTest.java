package com.example.inventario_automotriz.service;

import com.example.inventario_automotriz.dto.request.ProductoRequestDTO;
import com.example.inventario_automotriz.dto.response.ProductoResponseDTO;
import com.example.inventario_automotriz.exception.ProductoNotFoundException;
import com.example.inventario_automotriz.model.Producto;
import com.example.inventario_automotriz.model.Usuario;
import com.example.inventario_automotriz.repository.ProductoRepository;
import com.example.inventario_automotriz.repository.UsuarioRepository;
import com.example.inventario_automotriz.service.impl.ProductoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductoServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearProducto() {

        ProductoRequestDTO request = new ProductoRequestDTO("Producto 1", 10, LocalDate.now(), 1L);


        Usuario usuario = new Usuario();
        usuario.setId(1L);


        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));


        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto 1");
        producto.setCantidad(10);
        producto.setFechaIngreso(LocalDate.now());
        producto.setUsuario(usuario);


        when(productoRepository.save(any(Producto.class))).thenReturn(producto);


        ProductoResponseDTO response = productoService.crearProducto(request);


        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Producto 1", response.getNombre());
        assertEquals(10, response.getCantidad());


        verify(productoRepository, times(1)).save(any(Producto.class));
    }

    @Test
    void testObtenerProductoPorId() {

        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto 1");


        Usuario usuario = new Usuario();
        usuario.setId(1L);
        producto.setUsuario(usuario);


        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));


        Optional<ProductoResponseDTO> response = productoService.obtenerProductoPorId(1L);


        assertTrue(response.isPresent());
        assertEquals(1L, response.get().getId());
        assertEquals("Producto 1", response.get().getNombre());
        assertEquals(1L, response.get().getUsuarioId());


        verify(productoRepository, times(1)).findById(1L);
    }
    @Test
    void testActualizarProducto() {
        Producto productoExistente = new Producto();
        productoExistente.setId(1L);
        productoExistente.setNombre("Producto Viejo");
        productoExistente.setCantidad(10);
        productoExistente.setFechaIngreso(LocalDate.now());


        Usuario usuario = new Usuario();
        usuario.setId(1L);


        when(productoRepository.findById(1L)).thenReturn(Optional.of(productoExistente));
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));


        ProductoRequestDTO request = new ProductoRequestDTO("Producto Actualizado", 5, LocalDate.now(), 1L);


        Producto productoActualizado = new Producto();
        productoActualizado.setId(1L);
        productoActualizado.setNombre("Producto Actualizado");
        productoActualizado.setCantidad(5);
        productoActualizado.setFechaIngreso(LocalDate.now());
        productoActualizado.setFechaModificacion(LocalDate.now());
        productoActualizado.setUsuario(usuario);


        when(productoRepository.save(any(Producto.class))).thenReturn(productoActualizado);


        ProductoResponseDTO response = productoService.actualizarProducto(1L, request);


        assertNotNull(response);
        assertEquals("Producto Actualizado", response.getNombre());
        assertEquals(5, response.getCantidad());
        assertEquals(1L, response.getUsuarioId());
        assertNotNull(response.getFechaModificacion());


        verify(productoRepository, times(1)).save(any(Producto.class));
    }

    @Test
    void testEliminarProducto() {
        Producto producto = new Producto();
        producto.setId(1L);
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        producto.setUsuario(usuario);

        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        productoService.eliminarProducto(1L, 1L);

        verify(productoRepository, times(1)).deleteById(1L);
    }
}
