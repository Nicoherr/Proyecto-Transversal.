package com.marketplace.reporte.service;

import com.marketplace.reporte.DTO.ReporteRequestDTO;
import com.marketplace.reporte.DTO.ReporteResponseDTO;
import com.marketplace.reporte.model.Reporte;
import com.marketplace.reporte.repository.ReporteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReporteService {

    private final ReporteRepository reporteRepository;

    private ReporteResponseDTO makeToReporteResponseDTO(Reporte reporte) {
        return new ReporteResponseDTO(reporte.getId(), reporte.getTipo(), reporte.getDescripcion(), reporte.getFecha(), reporte.getEstado());
    }

    private Reporte obtenerOFallar(long id) {
        return reporteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reporte no encontrado con id: " + id));
    }

    public List<ReporteResponseDTO> findAllReportes() {
        log.info("Se listan todos los reportes");
        return reporteRepository.findAll().stream()
                .map(this::makeToReporteResponseDTO)
                .collect(Collectors.toList());
    }

    public ReporteResponseDTO findReportesById(long id) {
        log.info("Se busca reporte con id: {}", id);
        return makeToReporteResponseDTO(obtenerOFallar(id));
    }

    public ReporteResponseDTO makeReporte(ReporteRequestDTO newReporte) {
        log.info("Se inicia la creación de reporte tipo: {}", newReporte.getTipo());
        Reporte reporte = new Reporte();
        reporte.setTipo(newReporte.getTipo());
        reporte.setDescripcion(newReporte.getDescripcion());
        reporte.setFecha(new Date());
        reporte.setEstado(true);
        reporte = reporteRepository.save(reporte);
        return makeToReporteResponseDTO(reporte);
    }

    public void deleteReporte(long id) {
        log.info("Se elimina reporte con id: {}", id);
        reporteRepository.delete(obtenerOFallar(id));
    }
}
