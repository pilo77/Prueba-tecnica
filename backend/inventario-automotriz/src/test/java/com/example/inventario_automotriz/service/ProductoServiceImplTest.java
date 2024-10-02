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
        // Creando el request DTO con datos válidos
        ProductoRequestDTO request = new ProductoRequestDTO("Producto 1", 10, LocalDate.now(), 1L);

        // Creando el mock del usuario que será retornado por el repositorio
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        // Simulando la búsqueda del usuario por ID
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        // Creando el mock del producto que será retornado por el repositorio
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto 1");
        producto.setCantidad(10);
        producto.setFechaIngreso(LocalDate.now());
        producto.setUsuario(usuario);

        // Simulando la operación de guardar el producto
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        // Ejecutando el servicio
        ProductoResponseDTO response = productoService.crearProducto(request);

        // Verificando las aserciones
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Producto 1", response.getNombre());
        assertEquals(10, response.getCantidad());

        // Verificando que el repositorio guardó el producto una vez
        verify(productoRepository, times(1)).save(any(Producto.class));
    }

    @Test
    void testObtenerProductoPorId() {
        // Creando el mock del producto
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto 1");

        // Mock del usuario asociado al producto
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        producto.setUsuario(usuario);

        // Simulando la búsqueda del producto por ID
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        // Ejecutando el servicio
        Optional<ProductoResponseDTO> response = productoService.obtenerProductoPorId(1L);

        // Verificando las aserciones
        assertTrue(response.isPresent());
        assertEquals(1L, response.get().getId());
        assertEquals("Producto 1", response.get().getNombre());
        assertEquals(1L, response.get().getUsuarioId());

        // Verificando que el repositorio buscó el producto una vez
        verify(productoRepository, times(1)).findById(1L);
    }
    @Test
    void testActualizarProducto() {
        // Mock del producto existente
        Producto productoExistente = new Producto();
        productoExistente.setId(1L);
        productoExistente.setNombre("Producto Viejo");
        productoExistente.setCantidad(10);
        productoExistente.setFechaIngreso(LocalDate.now());

        // Mock del usuario
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        // Simulando la búsqueda del producto y del usuario
        when(productoRepository.findById(1L)).thenReturn(Optional.of(productoExistente));
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        // Creando el request DTO con los datos actualizados
        ProductoRequestDTO request = new ProductoRequestDTO("Producto Actualizado", 5, LocalDate.now(), 1L);

        // Mock del producto actualizado
        Producto productoActualizado = new Producto();
        productoActualizado.setId(1L);
        productoActualizado.setNombre("Producto Actualizado");
        productoActualizado.setCantidad(5);
        productoActualizado.setFechaIngreso(LocalDate.now());
        productoActualizado.setFechaModificacion(LocalDate.now());
        productoActualizado.setUsuario(usuario);

        // Simulando el guardado del producto actualizado
        when(productoRepository.save(any(Producto.class))).thenReturn(productoActualizado);

        // Ejecutando la actualización en el servicio
        ProductoResponseDTO response = productoService.actualizarProducto(1L, request);

        // Verificando las aserciones
        assertNotNull(response);
        assertEquals("Producto Actualizado", response.getNombre());
        assertEquals(5, response.getCantidad());
        assertEquals(1L, response.getUsuarioId());
        assertNotNull(response.getFechaModificacion()); // Verifica que la fecha de modificación no sea nula

        // Verificando que el repositorio guardó los cambios una vez
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
