package com.cpsj.service.mapper;

import com.cpsj.domain.*;
import com.cpsj.service.dto.PersonalDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Personal and its DTO PersonalDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PersonalMapper extends EntityMapper<PersonalDTO, Personal> {



    default Personal fromId(Long id) {
        if (id == null) {
            return null;
        }
        Personal personal = new Personal();
        personal.setId(id);
        return personal;
    }
}
