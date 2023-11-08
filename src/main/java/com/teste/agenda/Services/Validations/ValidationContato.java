package com.teste.agenda.Services.Validations;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.teste.agenda.Dtos.ContatoDto;

@Service
public class ValidationContato {

    public <T> ResponseEntity<ResponseApi<T>> validationContatoDto(ContatoDto data) {

        if (data.name().length() < 5) {
            return ResponseApi.error("O campo name deve ter no minimo 5 caracteres!");
        }
        if (data.email().isEmpty() || data.email() == null) {
            return ResponseApi.error("O campo email é obrigatorio!");
        }
        if (data.phone().isEmpty() || data.phone() == null) {
            return ResponseApi.error("O campo phone é obrigatoro");
        }
        if (data.phone().length() < 11 || data.phone().length() > 11) {
            return ResponseApi.error("O campo phone deve ter 11 caracteres");
        }

        return ResponseApi.success("Dados validados com sucesso!", null);
    }
}
