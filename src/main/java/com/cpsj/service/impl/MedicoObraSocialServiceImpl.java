package com.cpsj.service.impl;

import com.cpsj.service.MedicoObraSocialService;
import com.cpsj.domain.MedicoObraSocial;
import com.cpsj.repository.MedicoObraSocialRepository;
import com.cpsj.service.dto.MedicoObraSocialDTO;
import com.cpsj.service.mapper.MedicoObraSocialMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing MedicoObraSocial.
 */
@Service
@Transactional
public class MedicoObraSocialServiceImpl implements MedicoObraSocialService {

    private final Logger log = LoggerFactory.getLogger(MedicoObraSocialServiceImpl.class);

    private final MedicoObraSocialRepository medicoObraSocialRepository;

    private final MedicoObraSocialMapper medicoObraSocialMapper;

    public MedicoObraSocialServiceImpl(MedicoObraSocialRepository medicoObraSocialRepository, MedicoObraSocialMapper medicoObraSocialMapper) {
        this.medicoObraSocialRepository = medicoObraSocialRepository;
        this.medicoObraSocialMapper = medicoObraSocialMapper;
    }

    /**
     * Save a medicoObraSocial.
     *
     * @param medicoObraSocialDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MedicoObraSocialDTO save(MedicoObraSocialDTO medicoObraSocialDTO) {
        log.debug("Request to save MedicoObraSocial : {}", medicoObraSocialDTO);
        MedicoObraSocial medicoObraSocial = medicoObraSocialMapper.toEntity(medicoObraSocialDTO);
        medicoObraSocial = medicoObraSocialRepository.save(medicoObraSocial);
        return medicoObraSocialMapper.toDto(medicoObraSocial);
    }

    /**
     * Get all the medicoObraSocials.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<MedicoObraSocialDTO> findAll() {
        log.debug("Request to get all MedicoObraSocials");
        return medicoObraSocialRepository.findAll().stream()
            .map(medicoObraSocialMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one medicoObraSocial by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MedicoObraSocialDTO> findOne(Long id) {
        log.debug("Request to get MedicoObraSocial : {}", id);
        return medicoObraSocialRepository.findById(id)
            .map(medicoObraSocialMapper::toDto);
    }

    /**
     * Delete the medicoObraSocial by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MedicoObraSocial : {}", id);
        medicoObraSocialRepository.deleteById(id);
    }
}
