package com.marketplace.pago.controller;

import com.marketplace.pago.DTO.PagoRequestDTO;
import com.marketplace.pago.DTO.PagoResponseDTO;
import com.marketplace.pago.model.Pago;
import com.marketplace.pago.service.PagoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    //CREATE
    @PostMapping
    public ResponseEntity<PagoResponseDTO> guardar(@Valid @RequestBody PagoRequestDTO pagoDTO){
        PagoResponseDTO nuevo = pagoService.createPago(pagoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }
    //READ
    //BUSCAR
    @GetMapping("/{id}")
    public ResponseEntity<PagoRequestDTO> buscar(@PathVariable Long id){
        try{
            PagoRequestDTO pago = pagoService.findPagosById(id);
            return ResponseEntity.ok(pago);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }
    //LISTAR
    @GetMapping
    public ResponseEntity<List<PagoResponseDTO>> listar() {
        List<PagoResponseDTO> pagos = pagoService.findAllPagos();
        if (pagos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pagos);
    }

}
