package com.cpsj.service;

import com.cpsj.service.dto.ObservacionDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Observacion.
 */
public interface ObservacionService {

    /**
     * Save a observacion.
     *
     * @param observacionDTO the entity to save
     * @return the persisted entity
     */
    ObservacionDTO save(ObservacionDTO observacionDTO);

    /**
     * Get all the observacions.
     *
     * @return the list of entities
     */
    List<ObservacionDTO> findAll();


    /**
     * Get the "id" observacion.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ObservacionDTO> findOne(Long id);

    /**
     * Delete the "id" observacion.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
