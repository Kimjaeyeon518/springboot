package com.shop.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotExistProductException extends RuntimeException {

    public NotExistProductException(String msg) {
        super(msg);
    }

}