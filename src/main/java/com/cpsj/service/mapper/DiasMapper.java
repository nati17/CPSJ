package com.cpsj.service.mapper;

import com.cpsj.domain.*;
import com.cpsj.service.dto.DiasDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Dias and its DTO DiasDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DiasMapper extends EntityMapper<DiasDTO, Dias> {



    default Dias fromId(Long id) {
        if (id == null) {
            return null;
        }
        Dias dias = new Dias();
        dias.setId(id);
        return dias;
    }
}
