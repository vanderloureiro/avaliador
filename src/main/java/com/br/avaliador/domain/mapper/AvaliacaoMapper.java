package com.br.avaliador.domain.mapper;

import java.util.List;

import com.br.avaliador.domain.dto.AvaliacaoDto;
import com.br.avaliador.domain.entity.Avaliacao;
import com.br.avaliador.domain.form.AvaliacaoForm;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AvaliacaoMapper {
 
    @Mapping(source = "id", target = "codigoAvaliacao")
    AvaliacaoDto toDto(Avaliacao entidade);
    
    List<AvaliacaoDto> toDto(List<Avaliacao> entidades);
    
    Avaliacao toEntity(AvaliacaoForm form);
}
