package com.cpsj.service;

import com.cpsj.service.dto.AlergiaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Alergia.
 */
public interface AlergiaService {

    /**
     * Save a alergia.
     *
     * @param alergiaDTO the entity to save
     * @return the persisted entity
     */
    AlergiaDTO save(AlergiaDTO alergiaDTO);

    /**
     * Get all the alergias.
     *
     * @return the list of entities
     */
    List<AlergiaDTO> findAll();

    /**
     * Get all the Alergia with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<AlergiaDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" alergia.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AlergiaDTO> findOne(Long id);

    /**
     * Delete the "id" alergia.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
