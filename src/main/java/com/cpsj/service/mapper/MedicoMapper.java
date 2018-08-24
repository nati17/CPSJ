package com.cpsj.service.mapper;

import com.cpsj.domain.*;
import com.cpsj.service.dto.MedicoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Medico and its DTO MedicoDTO.
 */
@Mapper(componentModel = "spring", uses = {ObraSocialMapper.class, EspecialidadMapper.class})
public interface MedicoMapper extends EntityMapper<MedicoDTO, Medico> {


    @Mapping(target = "fichas", ignore = true)
    Medico toEntity(MedicoDTO medicoDTO);

    default Medico fromId(Long id) {
        if (id == null) {
            return null;
        }
        Medico medico = new Medico();
        medico.setId(id);
        return medico;
    }
}
