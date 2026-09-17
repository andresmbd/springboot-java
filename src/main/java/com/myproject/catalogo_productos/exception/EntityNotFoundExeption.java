package com.myproject.catalogo_productos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntityNotFoundExeption extends RuntimeException{
    public EntityNotFoundExeption(String msm){
        super(msm);
    }
}
