package com.cpsj.service;

import com.cpsj.service.dto.DiasDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Dias.
 */
public interface DiasService {

    /**
     * Save a dias.
     *
     * @param diasDTO the entity to save
     * @return the persisted entity
     */
    DiasDTO save(DiasDTO diasDTO);

    /**
     * Get all the dias.
     *
     * @return the list of entities
     */
    List<DiasDTO> findAll();


    /**
     * Get the "id" dias.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DiasDTO> findOne(Long id);

    /**
     * Delete the "id" dias.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
