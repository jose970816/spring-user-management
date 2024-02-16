package com.project.api.springusermanagement.exception;

import com.project.api.springusermanagement.dto.ApiErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerGenericException(HttpServletRequest request, Exception exception){
        ApiErrorDTO apiError = ApiErrorDTO.builder()
                .backendMessage(exception.getLocalizedMessage())
                .url(request.getRequestURL().toString())
                .method(request.getMethod())
                .message("Error interno en el servidor, vuelva a intentarlo")
                .timeStamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodArgumentNotValidException(HttpServletRequest request,
                                                                    MethodArgumentNotValidException exception){
        ApiErrorDTO apiError = ApiErrorDTO.builder()
                .backendMessage(exception.getLocalizedMessage())
                .url(request.getRequestURL().toString())
                .method(request.getMethod())
                .message("Error en la peticion enviada")
                .timeStamp(LocalDateTime.now())
                .subMessages(exception.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handlerAccessDeniedException(HttpServletRequest request, AccessDeniedException exception){
        ApiErrorDTO apiError = ApiErrorDTO.builder()
                .backendMessage(exception.getLocalizedMessage())
                .url(request.getRequestURL().toString())
                .method(request.getMethod())
                .message("Acceso denegado. No tienes los permisos necesarios para acceder a esta funcion. Por favor, contacta al administrador si crees que esto es un error")
                .timeStamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiError);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorizedException(UnauthorizedException exception) {
        ApiErrorDTO apiError = ApiErrorDTO.builder()
                .backendMessage(exception.getLocalizedMessage())
                .message("Acceso denegado. No tienes los permisos necesarios para acceder a esta funcion. Por favor, contacta al administrador si crees que esto es un error")
                .timeStamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
    }

}
