package com.marketplace.usuario.security.handler;
import com.marketplace.usuario.dto.ExceptionDTO; // el DTO de excepciones
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

        // Configuramos la respuesta como 401 (No autorizado)
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");

        // Creamos el mensaje de error usando tu estructura ExceptionDTO
        ExceptionDTO error = new ExceptionDTO(
                HttpStatus.UNAUTHORIZED,
                "Acceso denegado: No se encontró un token válido o no has iniciado sesión."
        );

        // Convertimos el objeto Java a JSON y lo enviamos al cliente
        objectMapper.writeValue(response.getOutputStream(), error);
    }
}

