package com.cpsj.service.impl;

import com.cpsj.service.PacienteObraSocialService;
import com.cpsj.domain.PacienteObraSocial;
import com.cpsj.repository.PacienteObraSocialRepository;
import com.cpsj.service.dto.PacienteObraSocialDTO;
import com.cpsj.service.mapper.PacienteObraSocialMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing PacienteObraSocial.
 */
@Service
@Transactional
public class PacienteObraSocialServiceImpl implements PacienteObraSocialService {

    private final Logger log = LoggerFactory.getLogger(PacienteObraSocialServiceImpl.class);

    private final PacienteObraSocialRepository pacienteObraSocialRepository;

    private final PacienteObraSocialMapper pacienteObraSocialMapper;

    public PacienteObraSocialServiceImpl(PacienteObraSocialRepository pacienteObraSocialRepository, PacienteObraSocialMapper pacienteObraSocialMapper) {
        this.pacienteObraSocialRepository = pacienteObraSocialRepository;
        this.pacienteObraSocialMapper = pacienteObraSocialMapper;
    }

    /**
     * Save a pacienteObraSocial.
     *
     * @param pacienteObraSocialDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PacienteObraSocialDTO save(PacienteObraSocialDTO pacienteObraSocialDTO) {
        log.debug("Request to save PacienteObraSocial : {}", pacienteObraSocialDTO);
        PacienteObraSocial pacienteObraSocial = pacienteObraSocialMapper.toEntity(pacienteObraSocialDTO);
        pacienteObraSocial = pacienteObraSocialRepository.save(pacienteObraSocial);
        return pacienteObraSocialMapper.toDto(pacienteObraSocial);
    }

    /**
     * Get all the pacienteObraSocials.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<PacienteObraSocialDTO> findAll() {
        log.debug("Request to get all PacienteObraSocials");
        return pacienteObraSocialRepository.findAll().stream()
            .map(pacienteObraSocialMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one pacienteObraSocial by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PacienteObraSocialDTO> findOne(Long id) {
        log.debug("Request to get PacienteObraSocial : {}", id);
        return pacienteObraSocialRepository.findById(id)
            .map(pacienteObraSocialMapper::toDto);
    }

    /**
     * Delete the pacienteObraSocial by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PacienteObraSocial : {}", id);
        pacienteObraSocialRepository.deleteById(id);
    }
}
