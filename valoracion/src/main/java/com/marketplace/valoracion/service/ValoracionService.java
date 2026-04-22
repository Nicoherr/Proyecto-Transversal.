package com.marketplace.valoracion.service;

import com.marketplace.valoracion.model.Valoracion;
import com.marketplace.valoracion.repository.ValoracionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ValoracionService {

    @Autowired
    private ValoracionRepository valoracionRepository;
    //CREAR
    public Valoracion save(Valoracion valoracion){ return valoracionRepository.save(valoracion);
    }
    //BUSCAR
    public Valoracion findById(Long id){ return valoracionRepository.findById(id).get();
    }
    //ACTUALIZAR

    //LISTAR
    public List<Valoracion> findAll(){
        return valoracionRepository.findAll();
    }
    //DELETE
    public void deleteById(Long id){
        valoracionRepository.deleteById(id);
    }

}
