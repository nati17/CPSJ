package com.cpsj.service.mapper;

import com.cpsj.domain.*;
import com.cpsj.service.dto.EmpresaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Empresa and its DTO EmpresaDTO.
 */
@Mapper(componentModel = "spring", uses = {SucursalMapper.class})
public interface EmpresaMapper extends EntityMapper<EmpresaDTO, Empresa> {

    @Mapping(source = "sucursal.id", target = "sucursalId")
    @Mapping(source = "sucursal.nombreSucursal", target = "sucursalNombreSucursal")
    EmpresaDTO toDto(Empresa empresa);

    @Mapping(source = "sucursalId", target = "sucursal")
    Empresa toEntity(EmpresaDTO empresaDTO);

    default Empresa fromId(Long id) {
        if (id == null) {
            return null;
        }
        Empresa empresa = new Empresa();
        empresa.setId(id);
        return empresa;
    }
}
