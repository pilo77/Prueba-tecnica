package com.example.inventario_automotriz.service;

import com.example.inventario_automotriz.dto.request.UsuarioRequestDTO;
import com.example.inventario_automotriz.dto.response.UsuarioResponseDTO;

import java.util.List;

public interface UsuarioService {
    UsuarioResponseDTO crearUsuario(UsuarioRequestDTO usuarioRequestDTO);
    List<UsuarioResponseDTO> obtenerTodosLosUsuarios();
    UsuarioResponseDTO obtenerUsuarioPorId(Long id);
    UsuarioResponseDTO actualizarUsuario(Long id, UsuarioRequestDTO usuarioRequestDTO);
    void eliminarUsuario(Long id);
}
