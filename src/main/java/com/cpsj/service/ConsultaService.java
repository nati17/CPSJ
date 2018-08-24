package com.cpsj.service;

import com.cpsj.service.dto.ConsultaDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Consulta.
 */
public interface ConsultaService {

    /**
     * Save a consulta.
     *
     * @param consultaDTO the entity to save
     * @return the persisted entity
     */
    ConsultaDTO save(ConsultaDTO consultaDTO);

    /**
     * Get all the consultas.
     *
     * @return the list of entities
     */
    List<ConsultaDTO> findAll();


    /**
     * Get the "id" consulta.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ConsultaDTO> findOne(Long id);

    /**
     * Delete the "id" consulta.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
