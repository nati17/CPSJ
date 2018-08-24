package com.cpsj.service.impl;

import com.cpsj.service.MedicoService;
import com.cpsj.domain.Medico;
import com.cpsj.repository.MedicoRepository;
import com.cpsj.service.dto.MedicoDTO;
import com.cpsj.service.mapper.MedicoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Medico.
 */
@Service
@Transactional
public class MedicoServiceImpl implements MedicoService {

    private final Logger log = LoggerFactory.getLogger(MedicoServiceImpl.class);

    private final MedicoRepository medicoRepository;

    private final MedicoMapper medicoMapper;

    public MedicoServiceImpl(MedicoRepository medicoRepository, MedicoMapper medicoMapper) {
        this.medicoRepository = medicoRepository;
        this.medicoMapper = medicoMapper;
    }

    /**
     * Save a medico.
     *
     * @param medicoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MedicoDTO save(MedicoDTO medicoDTO) {
        log.debug("Request to save Medico : {}", medicoDTO);
        Medico medico = medicoMapper.toEntity(medicoDTO);
        medico = medicoRepository.save(medico);
        return medicoMapper.toDto(medico);
    }

    /**
     * Get all the medicos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MedicoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Medicos");
        return medicoRepository.findAll(pageable)
            .map(medicoMapper::toDto);
    }

    /**
     * Get all the Medico with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<MedicoDTO> findAllWithEagerRelationships(Pageable pageable) {
        return medicoRepository.findAllWithEagerRelationships(pageable).map(medicoMapper::toDto);
    }
    

    /**
     * Get one medico by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MedicoDTO> findOne(Long id) {
        log.debug("Request to get Medico : {}", id);
        return medicoRepository.findOneWithEagerRelationships(id)
            .map(medicoMapper::toDto);
    }

    /**
     * Delete the medico by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Medico : {}", id);
        medicoRepository.deleteById(id);
    }
}
