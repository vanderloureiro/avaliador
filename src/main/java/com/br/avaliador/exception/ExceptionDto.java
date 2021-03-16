package com.br.avaliador.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionDto {
    
    private HttpStatus status;
    private String mensagem;
}
