package com.marketplace.pedido.controller;

import com.marketplace.pedido.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

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
