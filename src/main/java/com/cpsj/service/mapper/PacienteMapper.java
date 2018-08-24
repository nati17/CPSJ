package com.cpsj.service.mapper;

import com.cpsj.domain.*;
import com.cpsj.service.dto.PacienteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Paciente and its DTO PacienteDTO.
 */
@Mapper(componentModel = "spring", uses = {ObraSocialMapper.class})
public interface PacienteMapper extends EntityMapper<PacienteDTO, Paciente> {

    @Mapping(source = "pacienteObraSocial.id", target = "pacienteObraSocialId")
    PacienteDTO toDto(Paciente paciente);

    @Mapping(source = "pacienteObraSocialId", target = "pacienteObraSocial")
    Paciente toEntity(PacienteDTO pacienteDTO);

    default Paciente fromId(Long id) {
        if (id == null) {
            return null;
        }
        Paciente paciente = new Paciente();
        paciente.setId(id);
        return paciente;
    }
}
