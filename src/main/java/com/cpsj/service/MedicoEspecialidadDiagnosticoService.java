package com.cpsj.service;

import com.cpsj.service.dto.MedicoEspecialidadDiagnosticoDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing MedicoEspecialidadDiagnostico.
 */
public interface MedicoEspecialidadDiagnosticoService {

    /**
     * Save a medicoEspecialidadDiagnostico.
     *
     * @param medicoEspecialidadDiagnosticoDTO the entity to save
     * @return the persisted entity
     */
    MedicoEspecialidadDiagnosticoDTO save(MedicoEspecialidadDiagnosticoDTO medicoEspecialidadDiagnosticoDTO);

    /**
     * Get all the medicoEspecialidadDiagnosticos.
     *
     * @return the list of entities
     */
    List<MedicoEspecialidadDiagnosticoDTO> findAll();


    /**
     * Get the "id" medicoEspecialidadDiagnostico.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MedicoEspecialidadDiagnosticoDTO> findOne(Long id);

    /**
     * Delete the "id" medicoEspecialidadDiagnostico.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
