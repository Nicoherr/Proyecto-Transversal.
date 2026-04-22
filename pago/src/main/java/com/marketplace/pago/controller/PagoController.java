package com.marketplace.pago.controller;

import com.marketplace.pago.model.Pago;
import com.marketplace.pago.service.PagoService;
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
    public ResponseEntity<Pago> guardar(@RequestBody Pago pago){
        Pago nuevo = pagoService.save(pago);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    //READ
    //USAR TRY/CATCH PARA CUANDO NO HAY UN ID
    //LISTAR
    @GetMapping
    public ResponseEntity<List<Pago>> listar(){
        List<Pago> pagos = pagoService.findAll();
        if(pagos.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(pagos);
    }

    //BUSCAR
    @GetMapping
    public Pago buscarPorId(Long id){
        return pagoService.findById(id);
    }




}
