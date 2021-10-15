package com.shop.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotExistCartException extends RuntimeException {

    public NotExistCartException(String msg) {
        super(msg);
    }

}