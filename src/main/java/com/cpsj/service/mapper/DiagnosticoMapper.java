package com.cpsj.service.mapper;

import com.cpsj.domain.*;
import com.cpsj.service.dto.DiagnosticoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Diagnostico and its DTO DiagnosticoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DiagnosticoMapper extends EntityMapper<DiagnosticoDTO, Diagnostico> {



    default Diagnostico fromId(Long id) {
        if (id == null) {
            return null;
        }
        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setId(id);
        return diagnostico;
    }
}
