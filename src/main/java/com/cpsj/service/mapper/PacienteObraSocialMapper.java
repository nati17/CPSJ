package com.cpsj.service.mapper;

import com.cpsj.domain.*;
import com.cpsj.service.dto.PacienteObraSocialDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PacienteObraSocial and its DTO PacienteObraSocialDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PacienteObraSocialMapper extends EntityMapper<PacienteObraSocialDTO, PacienteObraSocial> {



    default PacienteObraSocial fromId(Long id) {
        if (id == null) {
            return null;
        }
        PacienteObraSocial pacienteObraSocial = new PacienteObraSocial();
        pacienteObraSocial.setId(id);
        return pacienteObraSocial;
    }
}
