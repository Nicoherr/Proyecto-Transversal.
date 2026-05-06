package com.marketplace.producto.service;
import com.marketplace.producto.dto.ProductoRequestDTO;
import com.marketplace.producto.dto.ProductoResponseDTO;
import com.marketplace.producto.model.Producto;
import com.marketplace.producto.repository.ProductoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductoService {

    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public ProductoResponseDTO crear(ProductoRequestDTO dto) {
        log.info("Intentando registrar un nuevo producto '{}' (Precio: {}) para el vendedor ID: {}",
                dto.getNombre(), dto.getPrecio(), dto.getVendedorId());

        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setVendedorId(dto.getVendedorId());
        // El campo 'activo' ya se asigna a true por defecto en el modelo

        Producto guardado = repository.save(producto);

        log.info("Producto creado exitosamente con ID: {}", guardado.getId());
        return convertirAResponse(guardado);
    }

    public List<ProductoResponseDTO> listar() {
        log.info("Listando todos los productos del catálogo");

        return repository.findAll().stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public ProductoResponseDTO obtener(Long id) {
        log.info("Buscando producto con ID: {}", id);

        Producto p = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        log.info("Producto '{}' encontrado correctamente", p.getNombre());
        return convertirAResponse(p);
    }

    // Método de mapeo
    private ProductoResponseDTO convertirAResponse(Producto p) {
        ProductoResponseDTO res = new ProductoResponseDTO();
        res.setId(p.getId());
        res.setNombre(p.getNombre());
        res.setDescripcion(p.getDescripcion());
        res.setPrecio(p.getPrecio());
        res.setStock(p.getStock());
        res.setVendedorId(p.getVendedorId());
        res.setActivo(p.isActivo());
        return res;
    }
}