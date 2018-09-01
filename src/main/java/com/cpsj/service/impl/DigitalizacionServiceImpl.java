package com.cpsj.service.impl;

import com.cpsj.service.DigitalizacionService;
import com.cpsj.domain.Digitalizacion;
import com.cpsj.repository.DigitalizacionRepository;
import com.cpsj.service.dto.DigitalizacionDTO;
import com.cpsj.service.mapper.DigitalizacionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Digitalizacion.
 */
@Service
@Transactional
public class DigitalizacionServiceImpl implements DigitalizacionService {

    private final Logger log = LoggerFactory.getLogger(DigitalizacionServiceImpl.class);

    private final DigitalizacionRepository digitalizacionRepository;

    private final DigitalizacionMapper digitalizacionMapper;

    public DigitalizacionServiceImpl(DigitalizacionRepository digitalizacionRepository, DigitalizacionMapper digitalizacionMapper) {
        this.digitalizacionRepository = digitalizacionRepository;
        this.digitalizacionMapper = digitalizacionMapper;
    }

    /**
     * Save a digitalizacion.
     *
     * @param digitalizacionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DigitalizacionDTO save(DigitalizacionDTO digitalizacionDTO) {
        log.debug("Request to save Digitalizacion : {}", digitalizacionDTO);
        Digitalizacion digitalizacion = digitalizacionMapper.toEntity(digitalizacionDTO);
        digitalizacion = digitalizacionRepository.save(digitalizacion);
        return digitalizacionMapper.toDto(digitalizacion);
    }

    /**
     * Get all the digitalizacions.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<DigitalizacionDTO> findAll() {
        log.debug("Request to get all Digitalizacions");
        return digitalizacionRepository.findAll().stream()
            .map(digitalizacionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one digitalizacion by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DigitalizacionDTO> findOne(Long id) {
        log.debug("Request to get Digitalizacion : {}", id);
        return digitalizacionRepository.findById(id)
            .map(digitalizacionMapper::toDto);
    }

    /**
     * Delete the digitalizacion by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Digitalizacion : {}", id);
        digitalizacionRepository.deleteById(id);
    }
}
