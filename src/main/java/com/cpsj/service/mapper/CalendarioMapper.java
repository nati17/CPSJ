package com.cpsj.service.mapper;

import com.cpsj.domain.*;
import com.cpsj.service.dto.CalendarioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Calendario and its DTO CalendarioDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CalendarioMapper extends EntityMapper<CalendarioDTO, Calendario> {



    default Calendario fromId(Long id) {
        if (id == null) {
            return null;
        }
        Calendario calendario = new Calendario();
        calendario.setId(id);
        return calendario;
    }
}
