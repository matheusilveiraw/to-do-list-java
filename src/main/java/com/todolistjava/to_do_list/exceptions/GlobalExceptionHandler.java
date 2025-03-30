package com.todolistjava.to_do_list.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.todolistjava.to_do_list.dtos.ErrorResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Captura exceção de formato inválido para UUID
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidUUID(IllegalArgumentException e) {
        String errorMessage = "Esse to do não existe";
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(errorMessage, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleEntityNotFound(EntityNotFoundException e) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(e.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
