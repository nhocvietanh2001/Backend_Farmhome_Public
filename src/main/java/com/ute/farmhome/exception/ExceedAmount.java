package com.ute.farmhome.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExceedAmount extends RuntimeException{
    private Map<String, String> errors;
    @Override
    public String toString() {
        return String.format("Amount exceed");
    }
}
