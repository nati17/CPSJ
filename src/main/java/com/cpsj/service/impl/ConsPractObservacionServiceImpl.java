package com.cpsj.service.impl;

import com.cpsj.service.ConsPractObservacionService;
import com.cpsj.domain.ConsPractObservacion;
import com.cpsj.repository.ConsPractObservacionRepository;
import com.cpsj.service.dto.ConsPractObservacionDTO;
import com.cpsj.service.mapper.ConsPractObservacionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing ConsPractObservacion.
 */
@Service
@Transactional
public class ConsPractObservacionServiceImpl implements ConsPractObservacionService {

    private final Logger log = LoggerFactory.getLogger(ConsPractObservacionServiceImpl.class);

    private final ConsPractObservacionRepository consPractObservacionRepository;

    private final ConsPractObservacionMapper consPractObservacionMapper;

    public ConsPractObservacionServiceImpl(ConsPractObservacionRepository consPractObservacionRepository, ConsPractObservacionMapper consPractObservacionMapper) {
        this.consPractObservacionRepository = consPractObservacionRepository;
        this.consPractObservacionMapper = consPractObservacionMapper;
    }

    /**
     * Save a consPractObservacion.
     *
     * @param consPractObservacionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ConsPractObservacionDTO save(ConsPractObservacionDTO consPractObservacionDTO) {
        log.debug("Request to save ConsPractObservacion : {}", consPractObservacionDTO);
        ConsPractObservacion consPractObservacion = consPractObservacionMapper.toEntity(consPractObservacionDTO);
        consPractObservacion = consPractObservacionRepository.save(consPractObservacion);
        return consPractObservacionMapper.toDto(consPractObservacion);
    }

    /**
     * Get all the consPractObservacions.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ConsPractObservacionDTO> findAll() {
        log.debug("Request to get all ConsPractObservacions");
        return consPractObservacionRepository.findAll().stream()
            .map(consPractObservacionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one consPractObservacion by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ConsPractObservacionDTO> findOne(Long id) {
        log.debug("Request to get ConsPractObservacion : {}", id);
        return consPractObservacionRepository.findById(id)
            .map(consPractObservacionMapper::toDto);
    }

    /**
     * Delete the consPractObservacion by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ConsPractObservacion : {}", id);
        consPractObservacionRepository.deleteById(id);
    }
}
