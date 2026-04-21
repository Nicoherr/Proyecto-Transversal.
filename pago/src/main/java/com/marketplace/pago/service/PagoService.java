package com.marketplace.pago.service;

import com.marketplace.pago.repository.PagoRepository;
import jakarta.transaction.Transactional;
import org.apache.catalina.startup.ContextRuleSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    //CREACION DEL CRUD
    //CREATE /save(entity) cuando el objeto no tiene id
    //UPDATE /save(entity) cuando el objeto ya tiene id




    //READ /findById(), /findAll(), /findAllById(), /existById(), /count().




    //DELETE /deleteById(), /delete(), /deleteAll().
}
