package com.cpsj.service.mapper;

import com.cpsj.domain.*;
import com.cpsj.service.dto.MedicoCalendarioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MedicoCalendario and its DTO MedicoCalendarioDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MedicoCalendarioMapper extends EntityMapper<MedicoCalendarioDTO, MedicoCalendario> {



    default MedicoCalendario fromId(Long id) {
        if (id == null) {
            return null;
        }
        MedicoCalendario medicoCalendario = new MedicoCalendario();
        medicoCalendario.setId(id);
        return medicoCalendario;
    }
}
