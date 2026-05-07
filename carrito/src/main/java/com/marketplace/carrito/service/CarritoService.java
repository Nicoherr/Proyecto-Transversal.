package com.marketplace.carrito.service;
import com.marketplace.carrito.dto.CarritoRequestDTO;
import com.marketplace.carrito.dto.CarritoResponseDTO;
import com.marketplace.carrito.dto.CarritoProductoRequestDTO;
import com.marketplace.carrito.dto.CarritoProductoResponseDTO;
import com.marketplace.carrito.model.Carrito;
import com.marketplace.carrito.model.CarritoProducto;
import com.marketplace.carrito.repository.CarritoProductoRepository;
import com.marketplace.carrito.repository.CarritoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CarritoService {

    private final CarritoRepository carritoRepository;
    private final CarritoProductoRepository carritoProductoRepository;

    public CarritoService(CarritoRepository carritoRepository, CarritoProductoRepository carritoProductoRepository) {
        this.carritoRepository = carritoRepository;
        this.carritoProductoRepository = carritoProductoRepository;
    }

    public CarritoResponseDTO crear(CarritoRequestDTO dto) {
        log.info("Creando un nuevo carrito vacío para el Usuario ID: {}", dto.getUsuarioId());
        Carrito carrito = new Carrito();
        carrito.setUsuarioId(dto.getUsuarioId());
        Carrito guardado = carritoRepository.save(carrito);
        log.info("Carrito creado exitosamente con ID: {}", guardado.getId());
        return convertirAResponse(guardado);
    }

    public CarritoResponseDTO obtener(Long id) {
        log.info("Buscando carrito con ID: {}", id);
        Carrito c = carritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado con id: " + id));
        log.info("Carrito del Usuario ID {} encontrado correctamente", c.getUsuarioId());
        return convertirAResponse(c);
    }

    // MÉTODOS DE PRODUCTOS DEL CARRITO AJUSTADOS PARA EL CONTROLLER

    public CarritoProductoResponseDTO agregarProducto(CarritoProductoRequestDTO dto) {
        log.info("Intentando agregar {} unidades del Producto ID: {} al Carrito ID: {}",
                dto.getCantidad(), dto.getProductoId(), dto.getCarritoId());

        carritoRepository.findById(dto.getCarritoId())
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado con ID: " + dto.getCarritoId()));

        Optional<CarritoProducto> productoExistente = carritoProductoRepository.findByCarritoIdAndProductoId(dto.getCarritoId(), dto.getProductoId());

        CarritoProducto guardado;
        if (productoExistente.isPresent()) {
            CarritoProducto cp = productoExistente.get();
            cp.setCantidad(cp.getCantidad() + dto.getCantidad());
            guardado = carritoProductoRepository.save(cp);
            log.info("El producto ya estaba en el carrito. Nueva cantidad actualizada a: {}", cp.getCantidad());
        } else {
            CarritoProducto nuevoCp = new CarritoProducto();
            nuevoCp.setCarritoId(dto.getCarritoId());
            nuevoCp.setProductoId(dto.getProductoId());
            nuevoCp.setCantidad(dto.getCantidad());
            guardado = carritoProductoRepository.save(nuevoCp);
            log.info("Producto nuevo registrado en el carrito exitosamente.");
        }

        return convertirProductoAResponse(guardado);
    }

    public List<CarritoProductoResponseDTO> listarProductos(Long carritoId) {
        log.info("Listando todos los productos del Carrito ID: {}", carritoId);
        return carritoProductoRepository.findByCarritoId(carritoId).stream()
                .map(this::convertirProductoAResponse)
                .collect(Collectors.toList());
    }

    // MÉTODOS DE MAPEO

    private CarritoResponseDTO convertirAResponse(Carrito c) {
        CarritoResponseDTO res = new CarritoResponseDTO();
        res.setId(c.getId());
        res.setUsuarioId(c.getUsuarioId());
        return res;
    }

    private CarritoProductoResponseDTO convertirProductoAResponse(CarritoProducto cp) {
        CarritoProductoResponseDTO res = new CarritoProductoResponseDTO();
        res.setId(cp.getId());
        res.setCarritoId(cp.getCarritoId());
        res.setProductoId(cp.getProductoId());
        res.setCantidad(cp.getCantidad());
        return res;
    }
}