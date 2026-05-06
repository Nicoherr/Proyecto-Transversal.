package com.marketplace.vendedor.service;
import com.marketplace.vendedor.dto.VendedorRequestDTO;
import com.marketplace.vendedor.dto.VendedorResponseDTO;
import com.marketplace.vendedor.model.Vendedor;
import com.marketplace.vendedor.repository.VendedorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j // logs
@Service
public class VendedorService {

    private final VendedorRepository repository;

    public VendedorService(VendedorRepository repository) {
        this.repository = repository;
    }

    public VendedorResponseDTO crear(VendedorRequestDTO dto) {
        log.info("Intentando registrar un nuevo vendedor (Tienda: {}) para el usuario ID: {}", dto.getNombreTienda(), dto.getUsuarioId());

        Vendedor vendedor = new Vendedor();
        vendedor.setNombreTienda(dto.getNombreTienda());
        vendedor.setDescripcion(dto.getDescripcion());
        vendedor.setUsuarioId(dto.getUsuarioId());
        // reputacion (0.0), cantidadValoraciones (0) y activo (true) ya se asignan solos por el modelo

        Vendedor guardado = repository.save(vendedor);

        log.info("Vendedor creado exitosamente con ID: {}", guardado.getId());
        return convertirAResponse(guardado);
    }

    public List<VendedorResponseDTO> listar() {
        log.info("Listando todos los vendedores registrados");

        return repository.findAll().stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public VendedorResponseDTO obtener(Long id) {
        log.info("Buscando vendedor con ID: {}", id);

        Vendedor v = repository.findById(id)
                .orElseThrow(() -> {
                    return new RuntimeException("Vendedor no encontrado con id: " + id);
                });

        log.info("Vendedor '{}' encontrado correctamente", v.getNombreTienda());
        return convertirAResponse(v);
    }

    // Método de mapeo
    private VendedorResponseDTO convertirAResponse(Vendedor v) {
        VendedorResponseDTO res = new VendedorResponseDTO();
        res.setId(v.getId());
        res.setNombreTienda(v.getNombreTienda());
        res.setDescripcion(v.getDescripcion());
        res.setReputacion(v.getReputacion());
        res.setCantidadValoraciones(v.getCantidadValoraciones());
        res.setUsuarioId(v.getUsuarioId());
        res.setActivo(v.isActivo());
        return res;
    }
}