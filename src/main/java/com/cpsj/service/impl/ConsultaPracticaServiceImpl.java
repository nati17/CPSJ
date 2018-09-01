package com.cpsj.service.impl;

import com.cpsj.service.ConsultaPracticaService;
import com.cpsj.domain.ConsultaPractica;
import com.cpsj.repository.ConsultaPracticaRepository;
import com.cpsj.service.dto.ConsultaPracticaDTO;
import com.cpsj.service.mapper.ConsultaPracticaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing ConsultaPractica.
 */
@Service
@Transactional
public class ConsultaPracticaServiceImpl implements ConsultaPracticaService {

    private final Logger log = LoggerFactory.getLogger(ConsultaPracticaServiceImpl.class);

    private final ConsultaPracticaRepository consultaPracticaRepository;

    private final ConsultaPracticaMapper consultaPracticaMapper;

    public ConsultaPracticaServiceImpl(ConsultaPracticaRepository consultaPracticaRepository, ConsultaPracticaMapper consultaPracticaMapper) {
        this.consultaPracticaRepository = consultaPracticaRepository;
        this.consultaPracticaMapper = consultaPracticaMapper;
    }

    /**
     * Save a consultaPractica.
     *
     * @param consultaPracticaDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ConsultaPracticaDTO save(ConsultaPracticaDTO consultaPracticaDTO) {
        log.debug("Request to save ConsultaPractica : {}", consultaPracticaDTO);
        ConsultaPractica consultaPractica = consultaPracticaMapper.toEntity(consultaPracticaDTO);
        consultaPractica = consultaPracticaRepository.save(consultaPractica);
        return consultaPracticaMapper.toDto(consultaPractica);
    }

    /**
     * Get all the consultaPracticas.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ConsultaPracticaDTO> findAll() {
        log.debug("Request to get all ConsultaPracticas");
        return consultaPracticaRepository.findAll().stream()
            .map(consultaPracticaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one consultaPractica by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ConsultaPracticaDTO> findOne(Long id) {
        log.debug("Request to get ConsultaPractica : {}", id);
        return consultaPracticaRepository.findById(id)
            .map(consultaPracticaMapper::toDto);
    }

    /**
     * Delete the consultaPractica by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ConsultaPractica : {}", id);
        consultaPracticaRepository.deleteById(id);
    }
}
