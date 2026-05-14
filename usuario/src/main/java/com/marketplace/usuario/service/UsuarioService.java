package com.marketplace.usuario.service;

import com.marketplace.usuario.dto.UsuarioRequestDTO;
import com.marketplace.usuario.dto.UsuarioResponseDTO;
import com.marketplace.usuario.model.Usuario;
import com.marketplace.usuario.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j // Anotación para los logs
@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public UsuarioResponseDTO crear(UsuarioRequestDTO dto) {
        // Log de información indicando qué estamos haciendo
        log.info("Intentando crear un nuevo usuario con email: {}", dto.getEmail());

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(dto.getPassword());
        usuario.setRol(dto.getRol());
        // El campo 'activo' no hace falta setearlo porque en el modelo ya le pusimos"= true"

        Usuario guardado = repository.save(usuario);

        // Log de éxito con el ID generado
        log.info("Usuario creado exitosamente con ID: {}", guardado.getId());

        return convertirAResponse(guardado);
    }

    public List<UsuarioResponseDTO> listar() {
        log.info("Listando todos los usuarios de la base de datos");

        return repository.findAll().stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO obtener(Long id) {
        log.info("Buscando usuario con ID: {}", id);

        Usuario u = repository.findById(id)
                .orElseThrow(() -> {
                    // Si no se encuentra, este error será capturado y logueado por el ExceptionHandler
                    return new RuntimeException("Usuario no encontrado con id: " + id);
                });

        log.info("Usuario encontrado correctamente con ID: {}", id);
        return convertirAResponse(u);
    }

    // Método para mapear el modelo al DTO de respuesta
    private UsuarioResponseDTO convertirAResponse(Usuario u) {
        UsuarioResponseDTO res = new UsuarioResponseDTO();
        res.setId(u.getId());
        res.setNombre(u.getNombre());
        res.setEmail(u.getEmail());
        res.setRol(u.getRol());
        res.setActivo(u.isActivo());
        // No mapeamos el password aquí por seguridad
        return res;
    }

    public void eliminar(Long id) {
    }

    public @Nullable UsuarioResponseDTO actualizar(Long id, UsuarioRequestDTO dto) {
        return null;
    }
}