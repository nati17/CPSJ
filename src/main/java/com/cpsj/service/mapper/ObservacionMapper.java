package com.cpsj.service.mapper;

import com.cpsj.domain.*;
import com.cpsj.service.dto.ObservacionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Observacion and its DTO ObservacionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ObservacionMapper extends EntityMapper<ObservacionDTO, Observacion> {



    default Observacion fromId(Long id) {
        if (id == null) {
            return null;
        }
        Observacion observacion = new Observacion();
        observacion.setId(id);
        return observacion;
    }
}
