package com.marketplace.valoracion.service;

import com.marketplace.valoracion.repository.ValoracionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ValoracionService {

    @Autowired
    private ValoracionRepository valoracionRepository;

    //CREACION DEL CRUD
    //CREATE /save(entity) cuando el objeto no tiene id
    //UPDATE /save(entity) cuando el objeto ya tiene id




    //READ /findById(), /findAll(), /findAllById(), /existById(), /count().




    //DELETE /deleteById(), /delete(), /deleteAll().
}
