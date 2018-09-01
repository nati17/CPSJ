package com.cpsj.service;

import com.cpsj.service.dto.MedicoCalendarioDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing MedicoCalendario.
 */
public interface MedicoCalendarioService {

    /**
     * Save a medicoCalendario.
     *
     * @param medicoCalendarioDTO the entity to save
     * @return the persisted entity
     */
    MedicoCalendarioDTO save(MedicoCalendarioDTO medicoCalendarioDTO);

    /**
     * Get all the medicoCalendarios.
     *
     * @return the list of entities
     */
    List<MedicoCalendarioDTO> findAll();


    /**
     * Get the "id" medicoCalendario.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MedicoCalendarioDTO> findOne(Long id);

    /**
     * Delete the "id" medicoCalendario.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
