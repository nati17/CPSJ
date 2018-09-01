package com.cpsj.service.mapper;

import com.cpsj.domain.*;
import com.cpsj.service.dto.PacienteTurnoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PacienteTurno and its DTO PacienteTurnoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PacienteTurnoMapper extends EntityMapper<PacienteTurnoDTO, PacienteTurno> {



    default PacienteTurno fromId(Long id) {
        if (id == null) {
            return null;
        }
        PacienteTurno pacienteTurno = new PacienteTurno();
        pacienteTurno.setId(id);
        return pacienteTurno;
    }
}
