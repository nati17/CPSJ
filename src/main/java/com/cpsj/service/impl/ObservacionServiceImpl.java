package com.cpsj.service.impl;

import com.cpsj.service.ObservacionService;
import com.cpsj.domain.Observacion;
import com.cpsj.repository.ObservacionRepository;
import com.cpsj.service.dto.ObservacionDTO;
import com.cpsj.service.mapper.ObservacionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Observacion.
 */
@Service
@Transactional
public class ObservacionServiceImpl implements ObservacionService {

    private final Logger log = LoggerFactory.getLogger(ObservacionServiceImpl.class);

    private final ObservacionRepository observacionRepository;

    private final ObservacionMapper observacionMapper;

    public ObservacionServiceImpl(ObservacionRepository observacionRepository, ObservacionMapper observacionMapper) {
        this.observacionRepository = observacionRepository;
        this.observacionMapper = observacionMapper;
    }

    /**
     * Save a observacion.
     *
     * @param observacionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ObservacionDTO save(ObservacionDTO observacionDTO) {
        log.debug("Request to save Observacion : {}", observacionDTO);
        Observacion observacion = observacionMapper.toEntity(observacionDTO);
        observacion = observacionRepository.save(observacion);
        return observacionMapper.toDto(observacion);
    }

    /**
     * Get all the observacions.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ObservacionDTO> findAll() {
        log.debug("Request to get all Observacions");
        return observacionRepository.findAll().stream()
            .map(observacionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one observacion by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ObservacionDTO> findOne(Long id) {
        log.debug("Request to get Observacion : {}", id);
        return observacionRepository.findById(id)
            .map(observacionMapper::toDto);
    }

    /**
     * Delete the observacion by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Observacion : {}", id);
        observacionRepository.deleteById(id);
    }
}
