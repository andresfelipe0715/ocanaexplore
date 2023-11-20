package com.proyecto_turismo_ufpso.turismo.Exception.exceptions;

import org.springframework.http.HttpStatus;

public class MethodArgumentNotValidException extends RuntimeException{
    private String code;
    private HttpStatus status;

    public MethodArgumentNotValidException(String message, String code, HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
