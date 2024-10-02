package com.example.inventario_automotriz.service.impl;

import com.example.inventario_automotriz.dto.request.UsuarioRequestDTO;
import com.example.inventario_automotriz.dto.response.UsuarioResponseDTO;
import com.example.inventario_automotriz.exception.UsuarioNotFoundException;
import com.example.inventario_automotriz.model.Cargo;
import com.example.inventario_automotriz.model.Usuario;
import com.example.inventario_automotriz.repository.CargoRepository;
import com.example.inventario_automotriz.repository.UsuarioRepository;
import com.example.inventario_automotriz.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final CargoRepository cargoRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, CargoRepository cargoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.cargoRepository = cargoRepository;
    }

    @Override
    public UsuarioResponseDTO crearUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        Cargo cargo = cargoRepository.findById(usuarioRequestDTO.getCargoId())
                .orElseThrow(() -> new IllegalArgumentException("Cargo no encontrado"));

        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioRequestDTO.getNombre());
        usuario.setEmail(usuarioRequestDTO.getEmail());
        usuario.setEdad(usuarioRequestDTO.getEdad());
        usuario.setCargo(cargo);
        usuario.setFechaIngreso(usuarioRequestDTO.getFechaIngreso());

        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return new UsuarioResponseDTO(nuevoUsuario.getId(), nuevoUsuario.getNombre(), nuevoUsuario.getEmail(),
                nuevoUsuario.getEdad(), nuevoUsuario.getCargo().getNombre(), nuevoUsuario.getFechaIngreso());
    }

    @Override
    public List<UsuarioResponseDTO> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> new UsuarioResponseDTO(usuario.getId(), usuario.getNombre(), usuario.getEmail(),
                        usuario.getEdad(), usuario.getCargo().getNombre(), usuario.getFechaIngreso()))
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDTO obtenerUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));
        return new UsuarioResponseDTO(usuario.getId(), usuario.getNombre(), usuario.getEmail(),
                usuario.getEdad(), usuario.getCargo().getNombre(), usuario.getFechaIngreso());
    }

    @Override
    public UsuarioResponseDTO actualizarUsuario(Long id, UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));

        Cargo cargo = cargoRepository.findById(usuarioRequestDTO.getCargoId())
                .orElseThrow(() -> new IllegalArgumentException("Cargo no encontrado"));

        usuario.setNombre(usuarioRequestDTO.getNombre());
        usuario.setEmail(usuarioRequestDTO.getEmail());
        usuario.setEdad(usuarioRequestDTO.getEdad());
        usuario.setCargo(cargo);
        usuario.setFechaIngreso(usuarioRequestDTO.getFechaIngreso());

        Usuario usuarioActualizado = usuarioRepository.save(usuario);
        return new UsuarioResponseDTO(usuarioActualizado.getId(), usuarioActualizado.getNombre(),
                usuarioActualizado.getEmail(), usuarioActualizado.getEdad(), usuarioActualizado.getCargo().getNombre(),
                usuarioActualizado.getFechaIngreso());
    }

    @Override
    public void eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));

        usuarioRepository.delete(usuario);
    }
}
