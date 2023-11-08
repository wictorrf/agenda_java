package com.teste.agenda.Services.Validations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseApi<T> {

    private boolean success;
    private String message;
    private T data;

    public static <T> ResponseEntity<ResponseApi<T>> success(String message, T data) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseApi<>(true, message, data));
    }

    public static <T> ResponseEntity<ResponseApi<T>> error(String message) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseApi<>(false, message, null));
    }

    public boolean success() {
        return success;
    }
}
