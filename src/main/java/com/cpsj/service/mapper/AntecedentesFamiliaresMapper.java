package com.cpsj.service.mapper;

import com.cpsj.domain.*;
import com.cpsj.service.dto.AntecedentesFamiliaresDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AntecedentesFamiliares and its DTO AntecedentesFamiliaresDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AntecedentesFamiliaresMapper extends EntityMapper<AntecedentesFamiliaresDTO, AntecedentesFamiliares> {



    default AntecedentesFamiliares fromId(Long id) {
        if (id == null) {
            return null;
        }
        AntecedentesFamiliares antecedentesFamiliares = new AntecedentesFamiliares();
        antecedentesFamiliares.setId(id);
        return antecedentesFamiliares;
    }
}
