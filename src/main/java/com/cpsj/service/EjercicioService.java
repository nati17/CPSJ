package com.cpsj.service;

import com.cpsj.service.dto.EjercicioDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Ejercicio.
 */
public interface EjercicioService {

    /**
     * Save a ejercicio.
     *
     * @param ejercicioDTO the entity to save
     * @return the persisted entity
     */
    EjercicioDTO save(EjercicioDTO ejercicioDTO);

    /**
     * Get all the ejercicios.
     *
     * @return the list of entities
     */
    List<EjercicioDTO> findAll();

    /**
     * Get all the Ejercicio with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<EjercicioDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" ejercicio.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EjercicioDTO> findOne(Long id);

    /**
     * Delete the "id" ejercicio.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
