package com.cpsj.service;

import com.cpsj.service.dto.ConsPractObservacionDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ConsPractObservacion.
 */
public interface ConsPractObservacionService {

    /**
     * Save a consPractObservacion.
     *
     * @param consPractObservacionDTO the entity to save
     * @return the persisted entity
     */
    ConsPractObservacionDTO save(ConsPractObservacionDTO consPractObservacionDTO);

    /**
     * Get all the consPractObservacions.
     *
     * @return the list of entities
     */
    List<ConsPractObservacionDTO> findAll();


    /**
     * Get the "id" consPractObservacion.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ConsPractObservacionDTO> findOne(Long id);

    /**
     * Delete the "id" consPractObservacion.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
