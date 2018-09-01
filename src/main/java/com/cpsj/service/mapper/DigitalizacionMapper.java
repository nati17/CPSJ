package com.cpsj.service.mapper;

import com.cpsj.domain.*;
import com.cpsj.service.dto.DigitalizacionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Digitalizacion and its DTO DigitalizacionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DigitalizacionMapper extends EntityMapper<DigitalizacionDTO, Digitalizacion> {



    default Digitalizacion fromId(Long id) {
        if (id == null) {
            return null;
        }
        Digitalizacion digitalizacion = new Digitalizacion();
        digitalizacion.setId(id);
        return digitalizacion;
    }
}
