package com.cpsj.service;

import com.cpsj.service.dto.PacienteDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Paciente.
 */
public interface PacienteService {

    /**
     * Save a paciente.
     *
     * @param pacienteDTO the entity to save
     * @return the persisted entity
     */
    PacienteDTO save(PacienteDTO pacienteDTO);

    /**
     * Get all the pacientes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PacienteDTO> findAll(Pageable pageable);


    /**
     * Get the "id" paciente.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PacienteDTO> findOne(Long id);

    /**
     * Delete the "id" paciente.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
