package com.marketplace.usuario.security.handler;
import com.marketplace.usuario.dto.ExceptionDTO; // Tu DTO de excepciones
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {

        response.setStatus(HttpStatus.FORBIDDEN.value()); // Error 403
        response.setContentType("application/json");

        // Usamos el ExceptionDTO para que el error sea consistente con el resto de la app
        ExceptionDTO error = new ExceptionDTO(HttpStatus.FORBIDDEN, "Acceso denegado: No tienes los permisos necesarios (Roles).");

        objectMapper.writeValue(response.getOutputStream(), error);
    }
}

