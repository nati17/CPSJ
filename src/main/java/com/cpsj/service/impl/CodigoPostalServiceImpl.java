package com.cpsj.service.impl;

import com.cpsj.service.CodigoPostalService;
import com.cpsj.domain.CodigoPostal;
import com.cpsj.repository.CodigoPostalRepository;
import com.cpsj.service.dto.CodigoPostalDTO;
import com.cpsj.service.mapper.CodigoPostalMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing CodigoPostal.
 */
@Service
@Transactional
public class CodigoPostalServiceImpl implements CodigoPostalService {

    private final Logger log = LoggerFactory.getLogger(CodigoPostalServiceImpl.class);

    private final CodigoPostalRepository codigoPostalRepository;

    private final CodigoPostalMapper codigoPostalMapper;

    public CodigoPostalServiceImpl(CodigoPostalRepository codigoPostalRepository, CodigoPostalMapper codigoPostalMapper) {
        this.codigoPostalRepository = codigoPostalRepository;
        this.codigoPostalMapper = codigoPostalMapper;
    }

    /**
     * Save a codigoPostal.
     *
     * @param codigoPostalDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CodigoPostalDTO save(CodigoPostalDTO codigoPostalDTO) {
        log.debug("Request to save CodigoPostal : {}", codigoPostalDTO);
        CodigoPostal codigoPostal = codigoPostalMapper.toEntity(codigoPostalDTO);
        codigoPostal = codigoPostalRepository.save(codigoPostal);
        return codigoPostalMapper.toDto(codigoPostal);
    }

    /**
     * Get all the codigoPostals.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CodigoPostalDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CodigoPostals");
        return codigoPostalRepository.findAll(pageable)
            .map(codigoPostalMapper::toDto);
    }


    /**
     * Get one codigoPostal by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CodigoPostalDTO> findOne(Long id) {
        log.debug("Request to get CodigoPostal : {}", id);
        return codigoPostalRepository.findById(id)
            .map(codigoPostalMapper::toDto);
    }

    /**
     * Delete the codigoPostal by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CodigoPostal : {}", id);
        codigoPostalRepository.deleteById(id);
    }
}
