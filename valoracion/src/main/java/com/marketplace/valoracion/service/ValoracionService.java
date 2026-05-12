package com.marketplace.valoracion.service;

import com.marketplace.valoracion.DTO.ValoracionRequestDTO;
import com.marketplace.valoracion.DTO.ValoracionResponseDTO;
import com.marketplace.valoracion.model.Valoracion;
import com.marketplace.valoracion.repository.ValoracionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ValoracionService {

    private final ValoracionRepository valoracionRepository;

    private ValoracionResponseDTO makeToValoracionResponseDTO(Valoracion valoracion) {
        return new ValoracionResponseDTO(valoracion.getId(), valoracion.getNumEstrella(), valoracion.getRecomendacion());
    }

    private Valoracion obtenerOFallar(long id) {
        return valoracionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Valoracion no encontrada con id: " + id));
    }

    public List<ValoracionResponseDTO> findAllValoraciones() {
        log.info("Se listan todas las valoraciones");
        return valoracionRepository.findAll().stream()
                .map(this::makeToValoracionResponseDTO)
                .collect(Collectors.toList());
    }

    public ValoracionResponseDTO findValoracionById(long id) {
        log.info("Se busca valoracion con id: {}", id);
        return makeToValoracionResponseDTO(obtenerOFallar(id));
    }

    public ValoracionResponseDTO makeValoracion(ValoracionRequestDTO newValoracion) {
        log.info("Se inicia la creación de valoracion con {} estrellas", newValoracion.getNumEstrella());
        Valoracion valoracion = new Valoracion();
        valoracion.setNumEstrella(newValoracion.getNumEstrella());
        valoracion.setRecomendacion(newValoracion.getRecomendacion());
        valoracion = valoracionRepository.save(valoracion);
        return makeToValoracionResponseDTO(valoracion);
    }

    public ValoracionResponseDTO updateValoracion(long id, ValoracionRequestDTO updateValoracion) {
        log.info("Se actualiza valoracion con id: {}", id);
        Valoracion valoracion = obtenerOFallar(id);
        valoracion.setNumEstrella(updateValoracion.getNumEstrella());
        valoracion.setRecomendacion(updateValoracion.getRecomendacion());
        valoracion = valoracionRepository.save(valoracion);
        return makeToValoracionResponseDTO(valoracion);
    }

    public void deleteValoracion(long id) {
        log.info("Se elimina valoracion con id: {}", id);
        valoracionRepository.delete(obtenerOFallar(id));
    }
}
