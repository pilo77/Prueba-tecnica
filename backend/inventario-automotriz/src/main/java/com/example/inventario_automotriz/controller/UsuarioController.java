package com.example.inventario_automotriz.controller;

import com.example.inventario_automotriz.dto.request.UsuarioRequestDTO;
import com.example.inventario_automotriz.dto.response.UsuarioResponseDTO;
import com.example.inventario_automotriz.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Crear un nuevo usuario
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> crearUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        UsuarioResponseDTO nuevoUsuario = usuarioService.crearUsuario(usuarioRequestDTO);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> obtenerTodosLosUsuarios() {
        List<UsuarioResponseDTO> usuarios = usuarioService.obtenerTodosLosUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerUsuarioPorId(@PathVariable Long id) {
        UsuarioResponseDTO usuario = usuarioService.obtenerUsuarioPorId(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    // Actualizar un usuario
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        UsuarioResponseDTO usuarioActualizado = usuarioService.actualizarUsuario(id, usuarioRequestDTO);
        return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
    }

    // Eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
