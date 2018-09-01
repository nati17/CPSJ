package com.cpsj.service;

import com.cpsj.service.dto.MedicoObraSocialDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing MedicoObraSocial.
 */
public interface MedicoObraSocialService {

    /**
     * Save a medicoObraSocial.
     *
     * @param medicoObraSocialDTO the entity to save
     * @return the persisted entity
     */
    MedicoObraSocialDTO save(MedicoObraSocialDTO medicoObraSocialDTO);

    /**
     * Get all the medicoObraSocials.
     *
     * @return the list of entities
     */
    List<MedicoObraSocialDTO> findAll();


    /**
     * Get the "id" medicoObraSocial.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MedicoObraSocialDTO> findOne(Long id);

    /**
     * Delete the "id" medicoObraSocial.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
