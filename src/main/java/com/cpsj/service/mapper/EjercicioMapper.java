package com.cpsj.service.mapper;

import com.cpsj.domain.*;
import com.cpsj.service.dto.EjercicioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Ejercicio and its DTO EjercicioDTO.
 */
@Mapper(componentModel = "spring", uses = {AntecedentesPersonalesMapper.class})
public interface EjercicioMapper extends EntityMapper<EjercicioDTO, Ejercicio> {



    default Ejercicio fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setId(id);
        return ejercicio;
    }
}
