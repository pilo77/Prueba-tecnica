package com.example.inventario_automotriz.service;

import com.example.inventario_automotriz.dto.request.UsuarioRequestDTO;
import com.example.inventario_automotriz.dto.response.UsuarioResponseDTO;
import com.example.inventario_automotriz.exception.UsuarioNotFoundException;
import com.example.inventario_automotriz.model.Cargo;
import com.example.inventario_automotriz.model.Usuario;
import com.example.inventario_automotriz.repository.CargoRepository;
import com.example.inventario_automotriz.repository.UsuarioRepository;
import com.example.inventario_automotriz.service.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private CargoRepository cargoRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearUsuario() {
        // Creando el DTO de solicitud con datos de usuario
        UsuarioRequestDTO request = new UsuarioRequestDTO("John Doe", "john.doe@example.com", 30, 1L, null);

        // Simulando la búsqueda del cargo
        Cargo cargo = new Cargo();
        cargo.setId(1L);
        cargo.setNombre("Administrador");

        when(cargoRepository.findById(1L)).thenReturn(Optional.of(cargo));

        // Creando el mock del usuario
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("John Doe");
        usuario.setEmail("john.doe@example.com");
        usuario.setEdad(30);
        usuario.setCargo(cargo);

        // Simulando la operación de guardar el usuario
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        // Ejecutando el servicio para crear el usuario
        UsuarioResponseDTO response = usuarioService.crearUsuario(request);

        // Verificando las aserciones
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("John Doe", response.getNombre());

        // Verificando que el repositorio guardó el usuario una vez
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }


    @Test
    void testObtenerUsuarioPorId() {
        // Creando el mock del usuario
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("John Doe");
        usuario.setEmail("john.doe@example.com");

        Cargo cargo = new Cargo();
        cargo.setId(1L);
        cargo.setNombre("Administrador");

        usuario.setCargo(cargo);

        // Simulando la búsqueda del usuario por ID
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        // Ejecutando el servicio para obtener el usuario
        UsuarioResponseDTO response = usuarioService.obtenerUsuarioPorId(1L);

        // Verificando las aserciones
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("John Doe", response.getNombre());
        assertEquals("Administrador", response.getCargo());

        // Verificando que el repositorio buscó al usuario una vez
        verify(usuarioRepository, times(1)).findById(1L);
    }
    @Test
    void testActualizarUsuario() {
        // Creando el mock del usuario existente
        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setId(1L);
        usuarioExistente.setNombre("John Doe");
        usuarioExistente.setEmail("john.doe@example.com");
        usuarioExistente.setEdad(30);

        // Simulando la búsqueda del cargo
        Cargo cargo = new Cargo();
        cargo.setId(1L);
        cargo.setNombre("Administrador");

        // Simulando las búsquedas del usuario y del cargo por ID
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioExistente));
        when(cargoRepository.findById(1L)).thenReturn(Optional.of(cargo));

        // Creando el DTO de solicitud con los nuevos datos
        UsuarioRequestDTO request = new UsuarioRequestDTO("Jane Doe", "jane.doe@example.com", 25, 1L, null);

        // Mock del usuario actualizado
        Usuario usuarioActualizado = new Usuario();
        usuarioActualizado.setId(1L);
        usuarioActualizado.setNombre("Jane Doe");
        usuarioActualizado.setEmail("jane.doe@example.com");
        usuarioActualizado.setEdad(25);
        usuarioActualizado.setCargo(cargo);

        // Simulando el guardado del usuario actualizado
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioActualizado);

        // Ejecutando el servicio para actualizar el usuario
        UsuarioResponseDTO response = usuarioService.actualizarUsuario(1L, request);

        // Verificando las aserciones
        assertNotNull(response);
        assertEquals("Jane Doe", response.getNombre());
        assertEquals("jane.doe@example.com", response.getEmail());
        assertEquals(25, response.getEdad());

        // Verificando que el repositorio guardó los cambios una vez
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void testEliminarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        usuarioService.eliminarUsuario(1L);

        verify(usuarioRepository, times(1)).delete(usuario);
    }
}
