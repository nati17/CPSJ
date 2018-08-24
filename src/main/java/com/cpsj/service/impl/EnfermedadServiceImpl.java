package com.cpsj.service.impl;

import com.cpsj.service.EnfermedadService;
import com.cpsj.domain.Enfermedad;
import com.cpsj.repository.EnfermedadRepository;
import com.cpsj.service.dto.EnfermedadDTO;
import com.cpsj.service.mapper.EnfermedadMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Enfermedad.
 */
@Service
@Transactional
public class EnfermedadServiceImpl implements EnfermedadService {

    private final Logger log = LoggerFactory.getLogger(EnfermedadServiceImpl.class);

    private final EnfermedadRepository enfermedadRepository;

    private final EnfermedadMapper enfermedadMapper;

    public EnfermedadServiceImpl(EnfermedadRepository enfermedadRepository, EnfermedadMapper enfermedadMapper) {
        this.enfermedadRepository = enfermedadRepository;
        this.enfermedadMapper = enfermedadMapper;
    }

    /**
     * Save a enfermedad.
     *
     * @param enfermedadDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EnfermedadDTO save(EnfermedadDTO enfermedadDTO) {
        log.debug("Request to save Enfermedad : {}", enfermedadDTO);
        Enfermedad enfermedad = enfermedadMapper.toEntity(enfermedadDTO);
        enfermedad = enfermedadRepository.save(enfermedad);
        return enfermedadMapper.toDto(enfermedad);
    }

    /**
     * Get all the enfermedads.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<EnfermedadDTO> findAll() {
        log.debug("Request to get all Enfermedads");
        return enfermedadRepository.findAllWithEagerRelationships().stream()
            .map(enfermedadMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the Enfermedad with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<EnfermedadDTO> findAllWithEagerRelationships(Pageable pageable) {
        return enfermedadRepository.findAllWithEagerRelationships(pageable).map(enfermedadMapper::toDto);
    }
    

    /**
     * Get one enfermedad by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnfermedadDTO> findOne(Long id) {
        log.debug("Request to get Enfermedad : {}", id);
        return enfermedadRepository.findOneWithEagerRelationships(id)
            .map(enfermedadMapper::toDto);
    }

    /**
     * Delete the enfermedad by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Enfermedad : {}", id);
        enfermedadRepository.deleteById(id);
    }
}
