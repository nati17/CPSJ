package com.cpsj.service;

import com.cpsj.service.dto.DigitalizacionDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Digitalizacion.
 */
public interface DigitalizacionService {

    /**
     * Save a digitalizacion.
     *
     * @param digitalizacionDTO the entity to save
     * @return the persisted entity
     */
    DigitalizacionDTO save(DigitalizacionDTO digitalizacionDTO);

    /**
     * Get all the digitalizacions.
     *
     * @return the list of entities
     */
    List<DigitalizacionDTO> findAll();


    /**
     * Get the "id" digitalizacion.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DigitalizacionDTO> findOne(Long id);

    /**
     * Delete the "id" digitalizacion.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
