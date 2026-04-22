package com.marketplace.pedido.service;

import com.marketplace.pedido.model.Pedido;
import com.marketplace.pedido.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    //CREACION DEL CRUD
    public Pedido save(Pedido pedido){ return pedidoRepository.save(pedido);
    }
    //LISTAR
    public List<Pedido> findAll(){ return pedidoRepository.findAll();
    }
    //BUSCAR
    public Pedido findById(Long id){ return pedidoRepository.findById(id).get();
    }
    //DELETE
    public void deleteById(Long id){
        pedidoRepository.deleteById(id);
    }
}
