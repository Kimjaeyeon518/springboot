package com.shop.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoValidProductSortException extends RuntimeException {
    public NoValidProductSortException(String msg) {
        super(msg);
    }
}