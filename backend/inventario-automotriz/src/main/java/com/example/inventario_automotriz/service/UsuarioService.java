package com.example.inventario_automotriz.service;

import com.example.inventario_automotriz.dto.UsuarioDTO;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO);
    List<UsuarioDTO> obtenerTodosLosUsuarios();
    Optional<UsuarioDTO> obtenerUsuarioPorId(Long id);
    UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO);


    void eliminarUsuario(Long id, String cargoUsuarioSolicitante);
}