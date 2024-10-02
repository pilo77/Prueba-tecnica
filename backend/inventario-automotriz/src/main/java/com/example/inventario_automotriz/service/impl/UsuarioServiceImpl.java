package com.example.inventario_automotriz.service.impl;

import com.example.inventario_automotriz.dto.UsuarioDTO;
import com.example.inventario_automotriz.model.Usuario;
import com.example.inventario_automotriz.repository.UsuarioRepository;
import com.example.inventario_automotriz.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setEdad(usuarioDTO.getEdad());
        usuario.setCargo(usuarioDTO.getCargo());
        usuario.setFechaIngreso(usuarioDTO.getFechaIngreso());

        Usuario nuevoUsuario = usuarioRepository.save(usuario);

        return new UsuarioDTO(nuevoUsuario.getId(), nuevoUsuario.getNombre(), nuevoUsuario.getEmail(),
                nuevoUsuario.getEdad(), nuevoUsuario.getCargo(), nuevoUsuario.getFechaIngreso());
    }

    @Override
    public List<UsuarioDTO> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> new UsuarioDTO(usuario.getId(), usuario.getNombre(), usuario.getEmail(),
                        usuario.getEdad(), usuario.getCargo(), usuario.getFechaIngreso()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UsuarioDTO> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(usuario -> new UsuarioDTO(usuario.getId(), usuario.getNombre(), usuario.getEmail(),
                        usuario.getEdad(), usuario.getCargo(), usuario.getFechaIngreso()));
    }

    @Override
    public UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(usuarioDTO.getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setEdad(usuarioDTO.getEdad());
        usuario.setCargo(usuarioDTO.getCargo());
        usuario.setFechaIngreso(usuarioDTO.getFechaIngreso());

        Usuario usuarioActualizado = usuarioRepository.save(usuario);

        return new UsuarioDTO(usuarioActualizado.getId(), usuarioActualizado.getNombre(), usuarioActualizado.getEmail(),
                usuarioActualizado.getEdad(), usuarioActualizado.getCargo(), usuarioActualizado.getFechaIngreso());
    }

    @Override
    public void eliminarUsuario(Long id, String cargoUsuarioSolicitante) {
        // Solo los administradores pueden eliminar usuarios
        if (!"Administrador".equals(cargoUsuarioSolicitante)) {
            throw new RuntimeException("Solo los administradores pueden eliminar usuarios.");
        }

        usuarioRepository.deleteById(id);
    }
}
