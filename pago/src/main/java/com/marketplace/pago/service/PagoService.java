package com.marketplace.pago.service;

import com.marketplace.pago.DTO.PagoRequestDTO;
import com.marketplace.pago.DTO.PagoResponseDTO;
import com.marketplace.pago.model.Pago;
import com.marketplace.pago.repository.PagoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.startup.ContextRuleSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PagoService {

    private final PagoRepository pagoRepository;

    private PagoResponseDTO makeToPagoResponseDTO(Pago pago) {
        return new PagoResponseDTO(pago.getId(), pago.getMetodoPago(), pago.getComprobante(), pago.getFecha());
    }

    public List<PagoResponseDTO> findAllPagos() {
        log.info("Se listan todos los pagos");
        return pagoRepository.findAll().stream()
                .map(this::makeToPagoResponseDTO)
                .collect(Collectors.toList());
    }

    public PagoResponseDTO findPagosById(long id) {
        log.info("Se busca pago con id: {}", id);
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pago no encontrado con id: " + id));
        return makeToPagoResponseDTO(pago);
    }

    public PagoResponseDTO makePago(PagoRequestDTO newPago) {
        log.info("Se inicia la creación de pago con método: {}", newPago.getMetodoPago());
        String comprobante = "COMP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Pago pago = new Pago();
        pago.setMetodoPago(newPago.getMetodoPago());
        pago.setComprobante(comprobante);
        pago.setFecha(new Date());
        pago = pagoRepository.save(pago);
        return makeToPagoResponseDTO(pago);
    }

    public void deletePago(long id) {
        log.info("Se elimina pago con id: {}", id);
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pago no encontrado con id: " + id));
        pagoRepository.delete(pago);
    }

}
