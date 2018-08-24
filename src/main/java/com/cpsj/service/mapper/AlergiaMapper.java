package com.cpsj.service.mapper;

import com.cpsj.domain.*;
import com.cpsj.service.dto.AlergiaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Alergia and its DTO AlergiaDTO.
 */
@Mapper(componentModel = "spring", uses = {AntecedentesPersonalesMapper.class})
public interface AlergiaMapper extends EntityMapper<AlergiaDTO, Alergia> {



    default Alergia fromId(Long id) {
        if (id == null) {
            return null;
        }
        Alergia alergia = new Alergia();
        alergia.setId(id);
        return alergia;
    }
}
