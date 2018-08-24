package com.cpsj.service;

import com.cpsj.service.dto.BebidaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Bebida.
 */
public interface BebidaService {

    /**
     * Save a bebida.
     *
     * @param bebidaDTO the entity to save
     * @return the persisted entity
     */
    BebidaDTO save(BebidaDTO bebidaDTO);

    /**
     * Get all the bebidas.
     *
     * @return the list of entities
     */
    List<BebidaDTO> findAll();

    /**
     * Get all the Bebida with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<BebidaDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" bebida.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BebidaDTO> findOne(Long id);

    /**
     * Delete the "id" bebida.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
