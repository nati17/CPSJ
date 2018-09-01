package com.cpsj.service.impl;

import com.cpsj.service.MontoConsultaPracticaService;
import com.cpsj.domain.MontoConsultaPractica;
import com.cpsj.repository.MontoConsultaPracticaRepository;
import com.cpsj.service.dto.MontoConsultaPracticaDTO;
import com.cpsj.service.mapper.MontoConsultaPracticaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing MontoConsultaPractica.
 */
@Service
@Transactional
public class MontoConsultaPracticaServiceImpl implements MontoConsultaPracticaService {

    private final Logger log = LoggerFactory.getLogger(MontoConsultaPracticaServiceImpl.class);

    private final MontoConsultaPracticaRepository montoConsultaPracticaRepository;

    private final MontoConsultaPracticaMapper montoConsultaPracticaMapper;

    public MontoConsultaPracticaServiceImpl(MontoConsultaPracticaRepository montoConsultaPracticaRepository, MontoConsultaPracticaMapper montoConsultaPracticaMapper) {
        this.montoConsultaPracticaRepository = montoConsultaPracticaRepository;
        this.montoConsultaPracticaMapper = montoConsultaPracticaMapper;
    }

    /**
     * Save a montoConsultaPractica.
     *
     * @param montoConsultaPracticaDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MontoConsultaPracticaDTO save(MontoConsultaPracticaDTO montoConsultaPracticaDTO) {
        log.debug("Request to save MontoConsultaPractica : {}", montoConsultaPracticaDTO);
        MontoConsultaPractica montoConsultaPractica = montoConsultaPracticaMapper.toEntity(montoConsultaPracticaDTO);
        montoConsultaPractica = montoConsultaPracticaRepository.save(montoConsultaPractica);
        return montoConsultaPracticaMapper.toDto(montoConsultaPractica);
    }

    /**
     * Get all the montoConsultaPracticas.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<MontoConsultaPracticaDTO> findAll() {
        log.debug("Request to get all MontoConsultaPracticas");
        return montoConsultaPracticaRepository.findAll().stream()
            .map(montoConsultaPracticaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one montoConsultaPractica by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MontoConsultaPracticaDTO> findOne(Long id) {
        log.debug("Request to get MontoConsultaPractica : {}", id);
        return montoConsultaPracticaRepository.findById(id)
            .map(montoConsultaPracticaMapper::toDto);
    }

    /**
     * Delete the montoConsultaPractica by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MontoConsultaPractica : {}", id);
        montoConsultaPracticaRepository.deleteById(id);
    }
}
