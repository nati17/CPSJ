package com.cpsj.service.impl;

import com.cpsj.service.PacienteTurnoService;
import com.cpsj.domain.PacienteTurno;
import com.cpsj.repository.PacienteTurnoRepository;
import com.cpsj.service.dto.PacienteTurnoDTO;
import com.cpsj.service.mapper.PacienteTurnoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing PacienteTurno.
 */
@Service
@Transactional
public class PacienteTurnoServiceImpl implements PacienteTurnoService {

    private final Logger log = LoggerFactory.getLogger(PacienteTurnoServiceImpl.class);

    private final PacienteTurnoRepository pacienteTurnoRepository;

    private final PacienteTurnoMapper pacienteTurnoMapper;

    public PacienteTurnoServiceImpl(PacienteTurnoRepository pacienteTurnoRepository, PacienteTurnoMapper pacienteTurnoMapper) {
        this.pacienteTurnoRepository = pacienteTurnoRepository;
        this.pacienteTurnoMapper = pacienteTurnoMapper;
    }

    /**
     * Save a pacienteTurno.
     *
     * @param pacienteTurnoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PacienteTurnoDTO save(PacienteTurnoDTO pacienteTurnoDTO) {
        log.debug("Request to save PacienteTurno : {}", pacienteTurnoDTO);
        PacienteTurno pacienteTurno = pacienteTurnoMapper.toEntity(pacienteTurnoDTO);
        pacienteTurno = pacienteTurnoRepository.save(pacienteTurno);
        return pacienteTurnoMapper.toDto(pacienteTurno);
    }

    /**
     * Get all the pacienteTurnos.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<PacienteTurnoDTO> findAll() {
        log.debug("Request to get all PacienteTurnos");
        return pacienteTurnoRepository.findAll().stream()
            .map(pacienteTurnoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one pacienteTurno by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PacienteTurnoDTO> findOne(Long id) {
        log.debug("Request to get PacienteTurno : {}", id);
        return pacienteTurnoRepository.findById(id)
            .map(pacienteTurnoMapper::toDto);
    }

    /**
     * Delete the pacienteTurno by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PacienteTurno : {}", id);
        pacienteTurnoRepository.deleteById(id);
    }
}
