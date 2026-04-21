package com.marketplace.valoracion.controller;

import com.marketplace.valoracion.service.ValoracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/valoracion")
public class ValoracionController {

    @Autowired
    private ValoracionService valoracionService;

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
