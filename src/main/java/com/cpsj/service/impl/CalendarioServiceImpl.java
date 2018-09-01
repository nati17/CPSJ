package com.cpsj.service.impl;

import com.cpsj.service.CalendarioService;
import com.cpsj.domain.Calendario;
import com.cpsj.repository.CalendarioRepository;
import com.cpsj.service.dto.CalendarioDTO;
import com.cpsj.service.mapper.CalendarioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Calendario.
 */
@Service
@Transactional
public class CalendarioServiceImpl implements CalendarioService {

    private final Logger log = LoggerFactory.getLogger(CalendarioServiceImpl.class);

    private final CalendarioRepository calendarioRepository;

    private final CalendarioMapper calendarioMapper;

    public CalendarioServiceImpl(CalendarioRepository calendarioRepository, CalendarioMapper calendarioMapper) {
        this.calendarioRepository = calendarioRepository;
        this.calendarioMapper = calendarioMapper;
    }

    /**
     * Save a calendario.
     *
     * @param calendarioDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CalendarioDTO save(CalendarioDTO calendarioDTO) {
        log.debug("Request to save Calendario : {}", calendarioDTO);
        Calendario calendario = calendarioMapper.toEntity(calendarioDTO);
        calendario = calendarioRepository.save(calendario);
        return calendarioMapper.toDto(calendario);
    }

    /**
     * Get all the calendarios.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CalendarioDTO> findAll() {
        log.debug("Request to get all Calendarios");
        return calendarioRepository.findAll().stream()
            .map(calendarioMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one calendario by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CalendarioDTO> findOne(Long id) {
        log.debug("Request to get Calendario : {}", id);
        return calendarioRepository.findById(id)
            .map(calendarioMapper::toDto);
    }

    /**
     * Delete the calendario by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Calendario : {}", id);
        calendarioRepository.deleteById(id);
    }
}
