package com.br.avaliador.domain.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AvaliacaoDto {
    
    private Long codigoAvaliacao;
    private Integer nota;
    private String comentario;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
}
