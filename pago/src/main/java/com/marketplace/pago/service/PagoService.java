package com.marketplace.pago.service;

import com.marketplace.pago.DTO.PagoRequestDTO;
import com.marketplace.pago.DTO.PagoResponseDTO;
import com.marketplace.pago.model.Pago;
import com.marketplace.pago.repository.PagoRepository;
import jakarta.transaction.Transactional;
import org.apache.catalina.startup.ContextRuleSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    private PagoResponseDTO makeToPagoResponseDTO(Pago pago){
        return new PagoResponseDTO(pago.getId(), pago.getMetodoPago(), pago.getComprobante(), pago.getFecha());
    }

    //Listar
    public List<PagoResponseDTO> findAllPagos(){
        return pagoRepository.findAll().stream().map(this::makeToPagoResponseDTO).collect(Collectors.toList());
    }

    //Buscar
    public PagoRequestDTO findPagosById(long id) {
        Pago pago = pagoRepository.findById(id).get();
        return new PagoRequestDTO(pago.getMetodoPago());
    }

    //Crear
    public PagoResponseDTO createPago(PagoRequestDTO newPagoDTO){
        Pago pago = new Pago(0, newPagoDTO.getMetodoPago(), newPagoDTO.getComprobante(), newPagoDTO.getFecha());
        pago = pagoRepository.save(pago);
        PagoResponseDTO pagoDTO = new PagoResponseDTO(pago.getId(), pago.getMetodoPago(), pago.getComprobante(), pago.getFecha());
        return pagoDTO;
    }


}
