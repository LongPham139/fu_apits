package com.apits.apitssystembackend.exceptions.handler;

import com.apits.apitssystembackend.constant.response.ResponseStatusDTO;
import com.apits.apitssystembackend.exceptions.*;
import com.apits.apitssystembackend.response.ListResponseDTO;
import com.apits.apitssystembackend.response.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers extends RuntimeException{
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> notFoundException(NotFoundException exception) {
        ResponseDTO dto = new ResponseDTO();
        dto.setMessage(exception.getMessage());
        dto.setStatus(ResponseStatusDTO.FAILURE);
        return ResponseEntity.badRequest().body(dto);
    }
    @ExceptionHandler(value = ListEmptyException.class)
    public ResponseEntity<Object> listEmptyException(ListEmptyException exception) {
        ListResponseDTO dto = new ListResponseDTO();
        dto.setMessage(exception.getMessage());
        dto.setStatus(ResponseStatusDTO.FAILURE);
        return ResponseEntity.ok().body(dto);
    }
    @ExceptionHandler(value = ExistException.class)
    public ResponseEntity<Object> existException(ExistException exception) {
        ResponseDTO dto = new ResponseDTO();
        dto.setMessage(exception.getMessage());
        dto.setStatus(ResponseStatusDTO.FAILURE);
        return ResponseEntity.badRequest().body(dto);
    }
    @ExceptionHandler(value = NotNullException.class)
    public ResponseEntity<Object> notNullException(NotNullException exception) {
        ResponseDTO dto = new ResponseDTO();
        dto.setMessage(exception.getMessage());
        dto.setStatus(ResponseStatusDTO.FAILURE);
        return ResponseEntity.badRequest().body(dto);
    }@ExceptionHandler(value = EmptyException.class)
    public ResponseEntity<Object> emptyException(EmptyException exception) {
        ResponseDTO dto = new ResponseDTO();
        dto.setMessage(exception.getMessage());
        dto.setStatus(ResponseStatusDTO.FAILURE);
        return ResponseEntity.badRequest().body(dto);
    }
}
