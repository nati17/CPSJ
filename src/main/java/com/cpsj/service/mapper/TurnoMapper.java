package com.cpsj.service.mapper;

import com.cpsj.domain.*;
import com.cpsj.service.dto.TurnoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Turno and its DTO TurnoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TurnoMapper extends EntityMapper<TurnoDTO, Turno> {



    default Turno fromId(Long id) {
        if (id == null) {
            return null;
        }
        Turno turno = new Turno();
        turno.setId(id);
        return turno;
    }
}
