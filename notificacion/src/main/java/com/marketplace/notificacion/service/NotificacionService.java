package com.marketplace.notificacion.service;

import com.marketplace.notificacion.DTO.NotificacionRequestDTO;
import com.marketplace.notificacion.DTO.NotificacionResponseDTO;
import com.marketplace.notificacion.model.Notificacion;
import com.marketplace.notificacion.repository.NotificacionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
@Transactional
public class NotificacionService{
    @Autowired
    private NotificacionRepository notificacionRepository;

    //Listar
    public List<Notificacion> getNotificaciones(){
        return notificacionRepository.findAll();
    }

    //Buscar
    public Notificacion getNotificacion(long id){
        return notificacionRepository.findById(id).get();
    }

    //Crear
    public NotificacionResponseDTO createNotificacion(NotificacionRequestDTO newNotificacionDTO){
        Notificacion notificacion = new Notificacion(0, newNotificacionDTO.getAsunto(), newNotificacionDTO.getMensaje(), new Date());
        notificacion = notificacionRepository.save(notificacion);
        NotificacionResponseDTO notificacionDTO = new NotificacionResponseDTO(notificacion.getId(), notificacion.getAsunto(), notificacion.getMensaje(), notificacion.getFecha());
        return notificacionDTO;
    }

    //Eliminar
    public void deleteNotificacion(long id) {
        Notificacion notificacion = notificacionRepository.findById(id).get();
        notificacionRepository.delete(notificacion);
    }
}
