package com.cpsj.service;

import com.cpsj.service.dto.SucursalDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Sucursal.
 */
public interface SucursalService {

    /**
     * Save a sucursal.
     *
     * @param sucursalDTO the entity to save
     * @return the persisted entity
     */
    SucursalDTO save(SucursalDTO sucursalDTO);

    /**
     * Get all the sucursals.
     *
     * @return the list of entities
     */
    List<SucursalDTO> findAll();


    /**
     * Get the "id" sucursal.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SucursalDTO> findOne(Long id);

    /**
     * Delete the "id" sucursal.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
