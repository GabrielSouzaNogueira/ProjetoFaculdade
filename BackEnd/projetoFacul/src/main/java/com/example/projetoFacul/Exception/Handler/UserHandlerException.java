package com.example.projetoFacul.Exception.Handler;

import com.example.projetoFacul.DTOS.ResponseDTO;
import com.example.projetoFacul.Exception.ExeptionUser.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class UserHandlerException {

    @ExceptionHandler(NameNotNull.class)
    public ResponseEntity<ResponseDTO> handlerNameNotNull(NameNotNull ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(false, ex.getMessage(), Instant.now().toString(), "NAME_NOT_NULL"));
    }

    @ExceptionHandler(PasswordNotNull.class)
    public ResponseEntity<ResponseDTO> handlerPasswordNotNull(PasswordNotNull ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO(false, ex.getMessage(), Instant.now().toString(), "PASSWORD_NOT_NULL"));
    }

    @ExceptionHandler(CargoNotNull.class)
    public ResponseEntity<ResponseDTO> handlerCargoNotNull(CargoNotNull ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(false, ex.getMessage(), Instant.now().toString(), "CARGO_NOT_NULL"));
    }

    @ExceptionHandler(CargoNotFound.class)
    public ResponseEntity<ResponseDTO> handlerCargoNotFound(CargoNotFound ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(false, ex.getMessage(), Instant.now().toString(), "CARGO_NOT_FOUND"));
    }

    @ExceptionHandler(NomeAlreadyExist.class)
    public ResponseEntity<ResponseDTO> handlerNomeAlreadyExist(NomeAlreadyExist ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(false, ex.getMessage(), Instant.now().toString(), "NAME_ALREADY_EXIST"));
    }

    @ExceptionHandler(NameNotFound.class)
    public ResponseEntity<ResponseDTO> handlerNameNotFound(NameNotFound ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(false, ex.getMessage(), Instant.now().toString(), "NAME_NOT_FOUND"));
    }

    @ExceptionHandler(PasswordInvalid.class)
    public ResponseEntity<ResponseDTO> handlerPasswordInvalid(PasswordInvalid ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(false, ex.getMessage(), Instant.now().toString(), "PASSWORD_INVALID"));
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ResponseDTO> handlerUserNotFound(UserNotFound ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(false, ex.getMessage(), Instant.now().toString(), "USER_NOT_FOUND"));
    }

    @ExceptionHandler(StatusNotFound.class)
    public ResponseEntity<ResponseDTO> handlerStatusNotFound(StatusNotFound ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(false, ex.getMessage(), Instant.now().toString(), "STATUS_NOT_FOUND"));
    }

    @ExceptionHandler(StatusNotNull.class)
    public ResponseEntity<ResponseDTO> handlerStatusNotFound(StatusNotNull ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(false, ex.getMessage(), Instant.now().toString(), "STATUS_NOT_NULL"));
    }

    @ExceptionHandler(StatusUserInative.class)
    public ResponseEntity<ResponseDTO> handlerStatusNotFound(StatusUserInative ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(false, ex.getMessage(), Instant.now().toString(), "STATUS_USER_INATIVE"));
    }

    @ExceptionHandler(UserIdInvalid.class)
    public ResponseEntity<ResponseDTO> handlerStatusNotFound(UserIdInvalid ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(false, ex.getMessage(), Instant.now().toString(), "STATUS_ID_INVALID"));
    }
}
