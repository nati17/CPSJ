package com.cpsj.service;

import com.cpsj.service.dto.PacienteObraSocialDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing PacienteObraSocial.
 */
public interface PacienteObraSocialService {

    /**
     * Save a pacienteObraSocial.
     *
     * @param pacienteObraSocialDTO the entity to save
     * @return the persisted entity
     */
    PacienteObraSocialDTO save(PacienteObraSocialDTO pacienteObraSocialDTO);

    /**
     * Get all the pacienteObraSocials.
     *
     * @return the list of entities
     */
    List<PacienteObraSocialDTO> findAll();


    /**
     * Get the "id" pacienteObraSocial.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PacienteObraSocialDTO> findOne(Long id);

    /**
     * Delete the "id" pacienteObraSocial.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
