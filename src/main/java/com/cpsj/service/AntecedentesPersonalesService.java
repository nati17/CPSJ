package com.cpsj.service;

import com.cpsj.service.dto.AntecedentesPersonalesDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing AntecedentesPersonales.
 */
public interface AntecedentesPersonalesService {

    /**
     * Save a antecedentesPersonales.
     *
     * @param antecedentesPersonalesDTO the entity to save
     * @return the persisted entity
     */
    AntecedentesPersonalesDTO save(AntecedentesPersonalesDTO antecedentesPersonalesDTO);

    /**
     * Get all the antecedentesPersonales.
     *
     * @return the list of entities
     */
    List<AntecedentesPersonalesDTO> findAll();


    /**
     * Get the "id" antecedentesPersonales.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AntecedentesPersonalesDTO> findOne(Long id);

    /**
     * Delete the "id" antecedentesPersonales.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
