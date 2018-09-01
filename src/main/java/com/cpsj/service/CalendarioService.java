package com.cpsj.service;

import com.cpsj.service.dto.CalendarioDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Calendario.
 */
public interface CalendarioService {

    /**
     * Save a calendario.
     *
     * @param calendarioDTO the entity to save
     * @return the persisted entity
     */
    CalendarioDTO save(CalendarioDTO calendarioDTO);

    /**
     * Get all the calendarios.
     *
     * @return the list of entities
     */
    List<CalendarioDTO> findAll();


    /**
     * Get the "id" calendario.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CalendarioDTO> findOne(Long id);

    /**
     * Delete the "id" calendario.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
