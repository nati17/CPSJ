package com.cpsj.service;

import com.cpsj.service.dto.PacienteTurnoDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing PacienteTurno.
 */
public interface PacienteTurnoService {

    /**
     * Save a pacienteTurno.
     *
     * @param pacienteTurnoDTO the entity to save
     * @return the persisted entity
     */
    PacienteTurnoDTO save(PacienteTurnoDTO pacienteTurnoDTO);

    /**
     * Get all the pacienteTurnos.
     *
     * @return the list of entities
     */
    List<PacienteTurnoDTO> findAll();


    /**
     * Get the "id" pacienteTurno.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PacienteTurnoDTO> findOne(Long id);

    /**
     * Delete the "id" pacienteTurno.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
