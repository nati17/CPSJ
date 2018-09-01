package com.cpsj.service.mapper;

import com.cpsj.domain.*;
import com.cpsj.service.dto.MedicoObraSocialDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MedicoObraSocial and its DTO MedicoObraSocialDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MedicoObraSocialMapper extends EntityMapper<MedicoObraSocialDTO, MedicoObraSocial> {



    default MedicoObraSocial fromId(Long id) {
        if (id == null) {
            return null;
        }
        MedicoObraSocial medicoObraSocial = new MedicoObraSocial();
        medicoObraSocial.setId(id);
        return medicoObraSocial;
    }
}
