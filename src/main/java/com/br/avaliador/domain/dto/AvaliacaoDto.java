package com.br.avaliador.domain.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AvaliacaoDto {
    
    private Long codigoAvaliacao;
    private Integer nota;
    private String comentario;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
}
