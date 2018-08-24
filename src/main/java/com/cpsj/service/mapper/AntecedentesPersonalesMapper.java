package com.cpsj.service.mapper;

import com.cpsj.domain.*;
import com.cpsj.service.dto.AntecedentesPersonalesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AntecedentesPersonales and its DTO AntecedentesPersonalesDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AntecedentesPersonalesMapper extends EntityMapper<AntecedentesPersonalesDTO, AntecedentesPersonales> {



    default AntecedentesPersonales fromId(Long id) {
        if (id == null) {
            return null;
        }
        AntecedentesPersonales antecedentesPersonales = new AntecedentesPersonales();
        antecedentesPersonales.setId(id);
        return antecedentesPersonales;
    }
}
