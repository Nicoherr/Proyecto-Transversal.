package com.marketplace.reporte.service;

import com.marketplace.reporte.repository.ReporteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    //CREACION DEL CRUD
    //CREATE /save(entity) cuando el objeto no tiene id
    //UPDATE /save(entity) cuando el objeto ya tiene id




    //READ /findById(), /findAll(), /findAllById(), /existById(), /count().




    //DELETE /deleteById(), /delete(), /deleteAll().
}
