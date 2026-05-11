package com.marketplace.pedido.service;

import com.marketplace.pedido.DTO.PedidoRequestDTO;
import com.marketplace.pedido.DTO.PedidoResponseDTO;
import com.marketplace.pedido.model.Pedido;
import com.marketplace.pedido.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    private PedidoResponseDTO makeToPedidoResponseDTO(Pedido pedido) {
        return new PedidoResponseDTO(pedido.getId(), pedido.getNomProducto(), pedido.getTipoProducto(), pedido.getPrecio());
    }

    public List<PedidoResponseDTO> findAllPedidos() {
        log.info("Se listan todos los pedidos");
        return pedidoRepository.findAll().stream()
                .map(this::makeToPedidoResponseDTO)
                .collect(Collectors.toList());
    }

    public PedidoResponseDTO findPedidoById(long id) {
        log.info("Se busca pedido con id: {}", id);
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado con id: " + id));
        return makeToPedidoResponseDTO(pedido);
    }

    public PedidoResponseDTO makePedido(PedidoRequestDTO newPedido) {
        log.info("Se inicia la creación de pedido: {}", newPedido.getNomProducto());
        Pedido pedido = new Pedido();
        pedido.setNomProducto(newPedido.getNomProducto());
        pedido.setTipoProducto(newPedido.getTipoProducto());
        pedido.setPrecio(newPedido.getPrecio());
        pedido = pedidoRepository.save(pedido);
        return makeToPedidoResponseDTO(pedido);
    }

    public void deletePedido(long id) {
        log.info("Se elimina pedido con id: {}", id);
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado con id: " + id));
        pedidoRepository.delete(pedido);
    }
}
