package com.cpsj.service.mapper;

import com.cpsj.domain.*;
import com.cpsj.service.dto.BebidaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Bebida and its DTO BebidaDTO.
 */
@Mapper(componentModel = "spring", uses = {AntecedentesPersonalesMapper.class})
public interface BebidaMapper extends EntityMapper<BebidaDTO, Bebida> {



    default Bebida fromId(Long id) {
        if (id == null) {
            return null;
        }
        Bebida bebida = new Bebida();
        bebida.setId(id);
        return bebida;
    }
}
