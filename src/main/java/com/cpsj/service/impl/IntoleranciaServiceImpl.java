package com.cpsj.service.impl;

import com.cpsj.service.IntoleranciaService;
import com.cpsj.domain.Intolerancia;
import com.cpsj.repository.IntoleranciaRepository;
import com.cpsj.service.dto.IntoleranciaDTO;
import com.cpsj.service.mapper.IntoleranciaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Intolerancia.
 */
@Service
@Transactional
public class IntoleranciaServiceImpl implements IntoleranciaService {

    private final Logger log = LoggerFactory.getLogger(IntoleranciaServiceImpl.class);

    private final IntoleranciaRepository intoleranciaRepository;

    private final IntoleranciaMapper intoleranciaMapper;

    public IntoleranciaServiceImpl(IntoleranciaRepository intoleranciaRepository, IntoleranciaMapper intoleranciaMapper) {
        this.intoleranciaRepository = intoleranciaRepository;
        this.intoleranciaMapper = intoleranciaMapper;
    }

    /**
     * Save a intolerancia.
     *
     * @param intoleranciaDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public IntoleranciaDTO save(IntoleranciaDTO intoleranciaDTO) {
        log.debug("Request to save Intolerancia : {}", intoleranciaDTO);
        Intolerancia intolerancia = intoleranciaMapper.toEntity(intoleranciaDTO);
        intolerancia = intoleranciaRepository.save(intolerancia);
        return intoleranciaMapper.toDto(intolerancia);
    }

    /**
     * Get all the intolerancias.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<IntoleranciaDTO> findAll() {
        log.debug("Request to get all Intolerancias");
        return intoleranciaRepository.findAllWithEagerRelationships().stream()
            .map(intoleranciaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the Intolerancia with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<IntoleranciaDTO> findAllWithEagerRelationships(Pageable pageable) {
        return intoleranciaRepository.findAllWithEagerRelationships(pageable).map(intoleranciaMapper::toDto);
    }
    

    /**
     * Get one intolerancia by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<IntoleranciaDTO> findOne(Long id) {
        log.debug("Request to get Intolerancia : {}", id);
        return intoleranciaRepository.findOneWithEagerRelationships(id)
            .map(intoleranciaMapper::toDto);
    }

    /**
     * Delete the intolerancia by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Intolerancia : {}", id);
        intoleranciaRepository.deleteById(id);
    }
}
