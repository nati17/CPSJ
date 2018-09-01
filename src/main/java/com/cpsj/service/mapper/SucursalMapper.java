package com.cpsj.service.mapper;

import com.cpsj.domain.*;
import com.cpsj.service.dto.SucursalDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Sucursal and its DTO SucursalDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SucursalMapper extends EntityMapper<SucursalDTO, Sucursal> {



    default Sucursal fromId(Long id) {
        if (id == null) {
            return null;
        }
        Sucursal sucursal = new Sucursal();
        sucursal.setId(id);
        return sucursal;
    }
}
