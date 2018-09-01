package com.cpsj.service.impl;

import com.cpsj.service.MedicoCalendarioService;
import com.cpsj.domain.MedicoCalendario;
import com.cpsj.repository.MedicoCalendarioRepository;
import com.cpsj.service.dto.MedicoCalendarioDTO;
import com.cpsj.service.mapper.MedicoCalendarioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing MedicoCalendario.
 */
@Service
@Transactional
public class MedicoCalendarioServiceImpl implements MedicoCalendarioService {

    private final Logger log = LoggerFactory.getLogger(MedicoCalendarioServiceImpl.class);

    private final MedicoCalendarioRepository medicoCalendarioRepository;

    private final MedicoCalendarioMapper medicoCalendarioMapper;

    public MedicoCalendarioServiceImpl(MedicoCalendarioRepository medicoCalendarioRepository, MedicoCalendarioMapper medicoCalendarioMapper) {
        this.medicoCalendarioRepository = medicoCalendarioRepository;
        this.medicoCalendarioMapper = medicoCalendarioMapper;
    }

    /**
     * Save a medicoCalendario.
     *
     * @param medicoCalendarioDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MedicoCalendarioDTO save(MedicoCalendarioDTO medicoCalendarioDTO) {
        log.debug("Request to save MedicoCalendario : {}", medicoCalendarioDTO);
        MedicoCalendario medicoCalendario = medicoCalendarioMapper.toEntity(medicoCalendarioDTO);
        medicoCalendario = medicoCalendarioRepository.save(medicoCalendario);
        return medicoCalendarioMapper.toDto(medicoCalendario);
    }

    /**
     * Get all the medicoCalendarios.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<MedicoCalendarioDTO> findAll() {
        log.debug("Request to get all MedicoCalendarios");
        return medicoCalendarioRepository.findAll().stream()
            .map(medicoCalendarioMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one medicoCalendario by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MedicoCalendarioDTO> findOne(Long id) {
        log.debug("Request to get MedicoCalendario : {}", id);
        return medicoCalendarioRepository.findById(id)
            .map(medicoCalendarioMapper::toDto);
    }

    /**
     * Delete the medicoCalendario by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MedicoCalendario : {}", id);
        medicoCalendarioRepository.deleteById(id);
    }
}
