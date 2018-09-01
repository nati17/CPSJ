package com.cpsj.service.mapper;

import com.cpsj.domain.*;
import com.cpsj.service.dto.ConsultaPracticaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ConsultaPractica and its DTO ConsultaPracticaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ConsultaPracticaMapper extends EntityMapper<ConsultaPracticaDTO, ConsultaPractica> {



    default ConsultaPractica fromId(Long id) {
        if (id == null) {
            return null;
        }
        ConsultaPractica consultaPractica = new ConsultaPractica();
        consultaPractica.setId(id);
        return consultaPractica;
    }
}
