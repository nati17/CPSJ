package com.cpsj.service.impl;

import com.cpsj.service.EspecialidadService;
import com.cpsj.domain.Especialidad;
import com.cpsj.repository.EspecialidadRepository;
import com.cpsj.service.dto.EspecialidadDTO;
import com.cpsj.service.mapper.EspecialidadMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Especialidad.
 */
@Service
@Transactional
public class EspecialidadServiceImpl implements EspecialidadService {

    private final Logger log = LoggerFactory.getLogger(EspecialidadServiceImpl.class);

    private final EspecialidadRepository especialidadRepository;

    private final EspecialidadMapper especialidadMapper;

    public EspecialidadServiceImpl(EspecialidadRepository especialidadRepository, EspecialidadMapper especialidadMapper) {
        this.especialidadRepository = especialidadRepository;
        this.especialidadMapper = especialidadMapper;
    }

    /**
     * Save a especialidad.
     *
     * @param especialidadDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EspecialidadDTO save(EspecialidadDTO especialidadDTO) {
        log.debug("Request to save Especialidad : {}", especialidadDTO);
        Especialidad especialidad = especialidadMapper.toEntity(especialidadDTO);
        especialidad = especialidadRepository.save(especialidad);
        return especialidadMapper.toDto(especialidad);
    }

    /**
     * Get all the especialidads.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EspecialidadDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Especialidads");
        return especialidadRepository.findAll(pageable)
            .map(especialidadMapper::toDto);
    }


    /**
     * Get one especialidad by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EspecialidadDTO> findOne(Long id) {
        log.debug("Request to get Especialidad : {}", id);
        return especialidadRepository.findById(id)
            .map(especialidadMapper::toDto);
    }

    /**
     * Delete the especialidad by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Especialidad : {}", id);
        especialidadRepository.deleteById(id);
    }
}
