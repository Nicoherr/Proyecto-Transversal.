package com.marketplace.pedido.service;

import com.marketplace.pedido.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    //CREACION DEL CRUD
    //CREATE /save(entity) cuando el objeto no tiene id
    //UPDATE /save(entity) cuando el objeto ya tiene id




    //READ /findById(), /findAll(), /findAllById(), /existById(), /count().




    //DELETE /deleteById(), /delete(), /deleteAll().
}
