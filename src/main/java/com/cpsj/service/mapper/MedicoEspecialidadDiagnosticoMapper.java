package com.cpsj.service.mapper;

import com.cpsj.domain.*;
import com.cpsj.service.dto.MedicoEspecialidadDiagnosticoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MedicoEspecialidadDiagnostico and its DTO MedicoEspecialidadDiagnosticoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MedicoEspecialidadDiagnosticoMapper extends EntityMapper<MedicoEspecialidadDiagnosticoDTO, MedicoEspecialidadDiagnostico> {



    default MedicoEspecialidadDiagnostico fromId(Long id) {
        if (id == null) {
            return null;
        }
        MedicoEspecialidadDiagnostico medicoEspecialidadDiagnostico = new MedicoEspecialidadDiagnostico();
        medicoEspecialidadDiagnostico.setId(id);
        return medicoEspecialidadDiagnostico;
    }
}
