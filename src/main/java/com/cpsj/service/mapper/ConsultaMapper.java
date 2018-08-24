package com.cpsj.service.mapper;

import com.cpsj.domain.*;
import com.cpsj.service.dto.ConsultaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Consulta and its DTO ConsultaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ConsultaMapper extends EntityMapper<ConsultaDTO, Consulta> {



    default Consulta fromId(Long id) {
        if (id == null) {
            return null;
        }
        Consulta consulta = new Consulta();
        consulta.setId(id);
        return consulta;
    }
}
