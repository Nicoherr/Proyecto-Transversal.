package com.marketplace.pago.controller;

import com.marketplace.pago.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    //CREATE
    @PostMapping


    //READ
    //USAR TRY/CATCH PARA CUANDO NO HAY UN ID
    @GetMapping("/{id}")
    @GetMapping


    //UPDATE
    @PutMapping("/{id}")


    //DELETE
    @DeleteMapping("/{id}")
    @DeleteMapping
}
