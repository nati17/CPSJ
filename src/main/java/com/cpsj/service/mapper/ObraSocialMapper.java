package com.cpsj.service.mapper;

import com.cpsj.domain.*;
import com.cpsj.service.dto.ObraSocialDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ObraSocial and its DTO ObraSocialDTO.
 */
@Mapper(componentModel = "spring", uses = {PacienteObraSocialMapper.class})
public interface ObraSocialMapper extends EntityMapper<ObraSocialDTO, ObraSocial> {

    @Mapping(source = "pacienteObraSocial.id", target = "pacienteObraSocialId")
    ObraSocialDTO toDto(ObraSocial obraSocial);

    @Mapping(source = "pacienteObraSocialId", target = "pacienteObraSocial")
    @Mapping(target = "medicos", ignore = true)
    ObraSocial toEntity(ObraSocialDTO obraSocialDTO);

    default ObraSocial fromId(Long id) {
        if (id == null) {
            return null;
        }
        ObraSocial obraSocial = new ObraSocial();
        obraSocial.setId(id);
        return obraSocial;
    }
}
