package com.marketplace.reporte.service;

import com.marketplace.reporte.model.Reporte;
import com.marketplace.reporte.repository.ReporteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    public Reporte save(Reporte reporte){ return reporteRepository.save(reporte);
    }

    public Reporte findById(Long id){ return reporteRepository.findById(id).get();
    }

    public List<Reporte> findAll(){ return reporteRepository.findAll();
    }

    public void deleteById(Long id){ reporteRepository.deleteById(id);

    }
}
