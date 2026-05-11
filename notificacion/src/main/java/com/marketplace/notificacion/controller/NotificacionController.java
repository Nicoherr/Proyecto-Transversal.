package com.marketplace.notificacion.controller;
import com.marketplace.notificacion.DTO.NotificacionRequestDTO;
import com.marketplace.notificacion.DTO.NotificacionResponseDTO;
import com.marketplace.notificacion.model.Notificacion;
import com.marketplace.notificacion.service.NotificacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {

    private final NotificacionService notificacionService;

    @GetMapping
    public ResponseEntity<List<NotificacionResponseDTO>> getNotificaciones() {
        return ResponseEntity.ok(notificacionService.findAllNotificaciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificacionResponseDTO> getNotificacion(@PathVariable long id) {
        return ResponseEntity.ok(notificacionService.findNotificacionesById(id));
    }

    @PostMapping
    public ResponseEntity<NotificacionResponseDTO> postNotificacion(@Valid @RequestBody NotificacionRequestDTO notificacionDTO) {
        NotificacionResponseDTO nuevo = notificacionService.makeNotificacion(notificacionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotificacion(@PathVariable long id) {
        notificacionService.deleteNotificacion(id);
        return ResponseEntity.noContent().build();
    }
}
