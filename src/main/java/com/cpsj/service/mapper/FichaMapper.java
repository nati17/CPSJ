package com.cpsj.service.mapper;

import com.cpsj.domain.*;
import com.cpsj.service.dto.FichaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Ficha and its DTO FichaDTO.
 */
@Mapper(componentModel = "spring", uses = {MedicoMapper.class})
public interface FichaMapper extends EntityMapper<FichaDTO, Ficha> {

    @Mapping(source = "medico.id", target = "medicoId")
    @Mapping(source = "medico.nombreMedico", target = "medicoNombreMedico")
    FichaDTO toDto(Ficha ficha);

    @Mapping(source = "medicoId", target = "medico")
    Ficha toEntity(FichaDTO fichaDTO);

    default Ficha fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ficha ficha = new Ficha();
        ficha.setId(id);
        return ficha;
    }
}
