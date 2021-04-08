package com.br.avaliador.domain.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvaliacaoForm {
    
    private Integer nota;
    private String comentario;
}
