package com.cpsj.service;

import com.cpsj.service.dto.MontoConsultaPracticaDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing MontoConsultaPractica.
 */
public interface MontoConsultaPracticaService {

    /**
     * Save a montoConsultaPractica.
     *
     * @param montoConsultaPracticaDTO the entity to save
     * @return the persisted entity
     */
    MontoConsultaPracticaDTO save(MontoConsultaPracticaDTO montoConsultaPracticaDTO);

    /**
     * Get all the montoConsultaPracticas.
     *
     * @return the list of entities
     */
    List<MontoConsultaPracticaDTO> findAll();


    /**
     * Get the "id" montoConsultaPractica.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MontoConsultaPracticaDTO> findOne(Long id);

    /**
     * Delete the "id" montoConsultaPractica.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
