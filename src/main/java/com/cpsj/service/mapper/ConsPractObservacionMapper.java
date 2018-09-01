package com.cpsj.service.mapper;

import com.cpsj.domain.*;
import com.cpsj.service.dto.ConsPractObservacionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ConsPractObservacion and its DTO ConsPractObservacionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ConsPractObservacionMapper extends EntityMapper<ConsPractObservacionDTO, ConsPractObservacion> {



    default ConsPractObservacion fromId(Long id) {
        if (id == null) {
            return null;
        }
        ConsPractObservacion consPractObservacion = new ConsPractObservacion();
        consPractObservacion.setId(id);
        return consPractObservacion;
    }
}
