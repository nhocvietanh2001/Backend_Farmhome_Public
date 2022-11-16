package com.ute.farmhome.exception;

import lombok.Getter;

import java.util.Map;
@Getter
public class ValidationException extends RuntimeException {
    private Map<String, String> errors;
    public ValidationException(String msg)
    {
        super(msg);
    }
    public ValidationException(Map<String, String> params)
    {
        this.errors = params;
    }
}
