package com.cpsj.service;

import com.cpsj.service.dto.ConsultaPracticaDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ConsultaPractica.
 */
public interface ConsultaPracticaService {

    /**
     * Save a consultaPractica.
     *
     * @param consultaPracticaDTO the entity to save
     * @return the persisted entity
     */
    ConsultaPracticaDTO save(ConsultaPracticaDTO consultaPracticaDTO);

    /**
     * Get all the consultaPracticas.
     *
     * @return the list of entities
     */
    List<ConsultaPracticaDTO> findAll();


    /**
     * Get the "id" consultaPractica.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ConsultaPracticaDTO> findOne(Long id);

    /**
     * Delete the "id" consultaPractica.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
