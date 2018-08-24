package com.cpsj.service.mapper;

import com.cpsj.domain.*;
import com.cpsj.service.dto.IntoleranciaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Intolerancia and its DTO IntoleranciaDTO.
 */
@Mapper(componentModel = "spring", uses = {AntecedentesPersonalesMapper.class})
public interface IntoleranciaMapper extends EntityMapper<IntoleranciaDTO, Intolerancia> {



    default Intolerancia fromId(Long id) {
        if (id == null) {
            return null;
        }
        Intolerancia intolerancia = new Intolerancia();
        intolerancia.setId(id);
        return intolerancia;
    }
}
