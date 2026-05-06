package com.marketplace.inventario.service;

import com.marketplace.inventario.dto.InventarioRequestDTO;
import com.marketplace.inventario.dto.InventarioResponseDTO;
import com.marketplace.inventario.model.Inventario;
import com.marketplace.inventario.repository.InventarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InventarioService {

    private final InventarioRepository repository;

    public InventarioService(InventarioRepository repository) {
        this.repository = repository;
    }

    public InventarioResponseDTO crear(InventarioRequestDTO dto) {
        log.info("Creando registro de inventario para el Producto ID: {} con stock inicial de: {}",
                dto.getProductoId(), dto.getStock());

        Inventario inventario = new Inventario();
        inventario.setProductoId(dto.getProductoId());
        inventario.setStock(dto.getStock());
        // Si el DTO trae stockMinimo lo asignamos, si no, tomará el 5 por defecto del modelo
        if (dto.getStockMinimo() > 0) {
            inventario.setStockMinimo(dto.getStockMinimo());
        }

        Inventario guardado = repository.save(inventario);

        log.info("Inventario creado exitosamente con ID: {}", guardado.getId());
        return convertirAResponse(guardado);
    }

    public List<InventarioResponseDTO> listar() {
        log.info("Listando todos los registros de inventario");

        return repository.findAll().stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public InventarioResponseDTO obtener(Long id) {
        log.info("Buscando inventario con ID: {}", id);

        Inventario i = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro de inventario no encontrado con id: " + id));

        log.info("Inventario del Producto ID {} encontrado correctamente", i.getProductoId());
        return convertirAResponse(i);
    }

    // Método de mapeo
    private InventarioResponseDTO convertirAResponse(Inventario i) {
        InventarioResponseDTO res = new InventarioResponseDTO();
        res.setId(i.getId());
        res.setProductoId(i.getProductoId());
        res.setStock(i.getStock());
        res.setStockMinimo(i.getStockMinimo());
        return res;
    }
}
