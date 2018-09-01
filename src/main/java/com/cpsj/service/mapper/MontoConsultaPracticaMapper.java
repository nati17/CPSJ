package com.cpsj.service.mapper;

import com.cpsj.domain.*;
import com.cpsj.service.dto.MontoConsultaPracticaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MontoConsultaPractica and its DTO MontoConsultaPracticaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MontoConsultaPracticaMapper extends EntityMapper<MontoConsultaPracticaDTO, MontoConsultaPractica> {



    default MontoConsultaPractica fromId(Long id) {
        if (id == null) {
            return null;
        }
        MontoConsultaPractica montoConsultaPractica = new MontoConsultaPractica();
        montoConsultaPractica.setId(id);
        return montoConsultaPractica;
    }
}
