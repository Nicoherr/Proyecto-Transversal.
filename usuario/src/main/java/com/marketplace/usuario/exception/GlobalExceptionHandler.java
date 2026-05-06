package com.marketplace.usuario.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j // Anotación para habilitar los logs
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeExceptions(RuntimeException ex) {

        // Aquí registramos el error en la consola para nosotros (en rojo)
        log.error("Se detectó un error en el microservicio de Usuario: {}", ex.getMessage(), ex);

        // Aquí armamos la respuesta limpia para el cliente/Postman
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}