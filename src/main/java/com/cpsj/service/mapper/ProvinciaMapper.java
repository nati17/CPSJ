package com.cpsj.service.mapper;

import com.cpsj.domain.*;
import com.cpsj.service.dto.ProvinciaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Provincia and its DTO ProvinciaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProvinciaMapper extends EntityMapper<ProvinciaDTO, Provincia> {


    @Mapping(target = "codigopostals", ignore = true)
    Provincia toEntity(ProvinciaDTO provinciaDTO);

    default Provincia fromId(Long id) {
        if (id == null) {
            return null;
        }
        Provincia provincia = new Provincia();
        provincia.setId(id);
        return provincia;
    }
}
